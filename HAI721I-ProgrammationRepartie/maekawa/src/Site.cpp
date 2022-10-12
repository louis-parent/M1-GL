#include "Site.h"
#include <thread>
#include <iostream>
#include <unistd.h>
#include <ctime>

void* connectClient(void* context, void* data) {	
	Socket* client = (Socket*) data;
	Site* site = (Site*) context;
	
	Request request;
	client->receiveData(&request, sizeof(Request));
	
	client->closeConnection();
	

	site->receive(request, client->getAddress());
	
	delete client;

	return nullptr;
}

void startListening(Site* site) {
	site->socketServer.startListening(connectClient, site);
}

Site::Site(unsigned short port, Quorum quorum)
: socketServer("localhost", port),
quorum(quorum), 
agreementReceived(0), 
hasReceivedFail(false), 
isCriticalAccessInUse(false) {
	
	this->subscribeToQuorum();
	thread(startListening, this).detach();
}

void Site::subscribeToQuorum() {
	for(unsigned int i = 0; i < this->quorum.size(); i++)
	{
		SiteAddress address = this->quorum.getSite(i);
		Request request = newRequest(RequestType::ADD, this->getPort());
		
		this->send(address, request);
	}
}

void Site::sendToQuorum(Request request) {
	for(unsigned int i = 0; i < this->quorum.size(); i++)
	{
		this->send(this->quorum.getSite(i), request);
	}
}

void Site::send(SiteAddress address, Request request) {
	Socket client(address.first, address.second);
	request.port = this->getPort();
	client.tryConnect();
	client.sendData(&request, sizeof(Request));

	client.closeConnection();
}

void Site::receive(Request request, string address) {
	SiteAddress siteAddress(address, request.port);
	
	switch(request.type)
	{
		case RequestType::ADD:
			this->receiveAddRequest(siteAddress);
			cout << "Adding site " << siteAddress.first << ":" << siteAddress.second <<  " to quorum" << endl;
			break;
			
		case RequestType::SYNC:
			this->receiveSyncRequest(request.args.critical.isCriticalAccessInUse, request.args.critical.address, request.args.critical.port, request.args.critical.timeStamp);
			cout << "Syncing crital access usage to " << (request.args.critical.isCriticalAccessInUse ? "true" : "false") << " from " << siteAddress.first << ":" << siteAddress.second << endl;
			break;
		
		case RequestType::DEMAND:
			this->receiveDemandRequest(siteAddress, request.args.timeStamp);
			cout << "Demand received from " << siteAddress.first << ":" << siteAddress.second << endl;
			break;
		
		case RequestType::AGREEMENT:
			this->receiveAgreementRequest();
			cout << "Receive agreement from " << siteAddress.first << ":" << siteAddress.second << endl;
			break;
			
		case RequestType::FAIL:
			this->receiveFailRequest();
			cout << "Receive fail from " << siteAddress.first << ":" << siteAddress.second << endl;
			break;
		
		case RequestType::POLL:
			this->receivePollRequest(siteAddress);
			cout << "Receive poll from " << siteAddress.first << ":" << siteAddress.second << endl;
			break;
			
		case RequestType::RELEASE:
			this->receiveReleaseRequest(siteAddress);
			cout << "Receive release from " << siteAddress.first << ":" << siteAddress.second << endl;
			break;
		case RequestType::RESTITUTION:
			this->receiveRestitutionRequest();
			cout << "Receive restiturion from " << siteAddress.first << ":" << siteAddress.second << endl;
			break;
		
		default:
			cout << "Unknown request received from " << siteAddress.first << ":" << siteAddress.second << endl;

		
	}
}

void Site::receiveAddRequest(SiteAddress address) {	
	this->quorum.addSite(address);

	Request request = newRequest(RequestType::SYNC, this->getPort());
	request.args.critical = (RequestCriticalArg) {
		this->isCriticalAccessInUse,
		{0},
		this->siteWithCriticalAccess.first.second,
		this->siteWithCriticalAccess.second
	};

	for(unsigned int i = 0; i < this->siteWithCriticalAccess.first.first.length(); i++)
	{
		request.args.critical.address[i] = this->siteWithCriticalAccess.first.first[i];
	}
	
	this->send(address, request);
}

void Site::receiveSyncRequest(bool isCriticalAccessInUse, string address, unsigned short port, long timestamp) {
	this->isCriticalAccessInUse = isCriticalAccessInUse;
	this->siteWithCriticalAccess = SiteWithTimeStamp(SiteAddress(address, port), timestamp);
}

void Site::receiveDemandRequest(SiteAddress address, long timestamp) {
	if(!this->isCriticalAccessInUse)
	{
		Request request = newRequest(RequestType::AGREEMENT, this->getPort());
		this->send(address, request);
		
		this->isCriticalAccessInUse = true;
		this->siteWithCriticalAccess = pair(address, timestamp);
	}
	else
	{
		this->waitingLine.push(SiteWithTimeStamp(address, timestamp));

		if(this->siteWithCriticalAccess.second > timestamp)
		{
			Request request = newRequest(RequestType::FAIL, this->getPort());
			this->send(address, request);
		}
		else
		{
			Request request = newRequest(RequestType::POLL, this->getPort());
			this->send(this->siteWithCriticalAccess.first, request);
		}
	}
}

void Site::receiveAgreementRequest() {
	this->agreementReceived++;

	if(this->agreementReceived == this->quorum.size())
	{
		this->isCriticalAccessInUse = true;
		this->siteWithCriticalAccess = SiteWithTimeStamp(SiteAddress(this->socketServer.getAddress(), this->socketServer.getPort()), 0);
		this->useCriticalAccess();
	}
}

void Site::receiveFailRequest() {
	this->hasReceivedFail = true;
}

void Site::receiveReleaseRequest(SiteAddress address) {
	vector<SiteWithTimeStamp> tempArray;
	bool find = false;

	while(!find && this->waitingLine.size() > 0){
		SiteWithTimeStamp temp = this->waitingLine.top();
		this->waitingLine.pop();

		if(temp.first.first == address.first && temp.first.second == address.second){
			find = true;
		}else{
			tempArray.push_back(temp);
		}
	}

	for(unsigned int i = 0; i < tempArray.size(); i++){
		this->waitingLine.push(tempArray[i]);
	}

	this->isCriticalAccessInUse = false;

	if(this->waitingLine.size() > 0){
		Request request = newRequest(RequestType::AGREEMENT, this->getPort());
		this->send(this->waitingLine.top().first, request);

		this->isCriticalAccessInUse = true;
		this->siteWithCriticalAccess = this->waitingLine.top();
	}
}

void Site::receiveRestitutionRequest(){
	this->waitingLine.push(this->siteWithCriticalAccess);

	SiteWithTimeStamp newSiteWithCriticalAccess = this->waitingLine.top();
	this->siteWithCriticalAccess = newSiteWithCriticalAccess;

	Request request = newRequest(RequestType::AGREEMENT, this->getPort());
	this->send(newSiteWithCriticalAccess.first, request);
}

void Site::receivePollRequest(SiteAddress address) {
	if(this->hasReceivedFail){
		Request request = newRequest(RequestType::RESTITUTION, this->getPort());

		this->send(address, request);
	}
}

void Site::demandCriticalAccess()
{
	this->agreementReceived = 0;
	this->hasReceivedFail = false;
	
	if(this->quorum.size() > 0){
		Request request = newRequest(RequestType::DEMAND, this->getPort());
		request.args.timeStamp = time(NULL);
	
		this->sendToQuorum(request);
	}else{
		this->useCriticalAccess();
	}
}

unsigned short Site::getPort() const {
	return this->socketServer.getPort();
}

void Site::useCriticalAccess(){
	this->hasReceivedFail = false;

	cout << "En utilisation du critical access" << endl;
	sleep(rand() % 8 - 3);

	Request request = newRequest(RequestType::RELEASE, this->getPort());
	this->sendToQuorum(request);

	this->isCriticalAccessInUse = false;
}	
