#include "Site.h"
#include <thread>
#include <iostream>
#include <unistd.h>

void* connectClient(void* context, void* data) {	
	Socket* client = (Socket*) data;
	Site* site = (Site*) context;
	
	Request request;
	client->receiveData(&request, sizeof(Request));
	
	site->receive(request, client->getAddress());
	
	delete client;	
	return nullptr;
}

void startListening(Site* site) {
	site->socketServer.startListening(connectClient, site);
}

Site::Site(unsigned short port, Quorum quorum, int priority)
: socketServer("localhost", port), 
priority(priority), 
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
	client.tryConnect();
	client.sendData(&request, sizeof(Request));
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
			this->receiveSyncRequest(request.args.critical.isCriticalAccessInUse, request.args.critical.address, request.args.critical.port, request.args.critical.priority);
			cout << "Syncing crital access usage to " << (request.args.critical.isCriticalAccessInUse ? "true" : "false") << " from " << siteAddress.first << ":" << siteAddress.second << endl;
			break;
		
		case RequestType::DEMAND:
			this->receiveDemandRequest(siteAddress, request.args.priority);
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
			break;
			
		case RequestType::RELEASE:
			this->receiveReleaseRequest(siteAddress);
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

void Site::receiveSyncRequest(bool isCriticalAccessInUse, string address, unsigned short port, int priority) {
	this->isCriticalAccessInUse = isCriticalAccessInUse;
	this->siteWithCriticalAccess = SiteWithPriority(SiteAddress(address, port), priority);
}

void Site::receiveDemandRequest(SiteAddress address, int priority) {
	if(!this->isCriticalAccessInUse)
	{
		Request request = newRequest(RequestType::AGREEMENT, this->getPort());
		this->send(address, request);
		
		this->isCriticalAccessInUse = true;
		this->siteWithCriticalAccess = pair(address, priority);
	}
	else
	{
		if(this->siteWithCriticalAccess.second > priority)
		{
			Request request = newRequest(RequestType::FAIL, this->getPort());
			this->send(address, request);
			this->waitingLine.push(SiteWithPriority(address, priority));
		}
		else
		{
			Request request = newRequest(RequestType::POLL, this->getPort());
			this->send(this->siteWithCriticalAccess.first, request);
		}
	}
}

void Site:receiveAgreementRequest() {
	this->agreementReceived++;
	
	if(this->agreementReceived == this->quorum.size())
	{
		sleep(rand() % 8 - 3);
	}
	
	Request request = newRequest(RequestType::RELEASE, this->getPort());
	this->sendToQuorum(request);
}

void Site::receiveFailRequest() {
	this->hasReceivedFail = true;
}

void Site::receiveReleaseRequest(SiteAddress address) {
	this->waitingLine
}

void Site::receivePollRequest() {
	
}

void Site::demandCriticalAccess()
{
	this->agreementReceived = 0;
	this->hasReceivedFail = false;
	
	Request request = newRequest(RequestType::DEMAND, this->getPort());
	request.args.priority = this->priority;
	
	this->sendToQuorum(request);
}

unsigned short Site::getPort() const {
	return this->socketServer.getPort();
}

bool Site::equals(Site site){
    return this->socketServer.equals(site.socketServer) &&
    this->quorum.equals(site.quorum);
}
