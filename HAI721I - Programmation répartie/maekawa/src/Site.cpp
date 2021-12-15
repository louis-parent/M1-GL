#include "Site.h"
#include <thread>
#include <iostream>

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

Site::Site(unsigned short port, Quorum quorum) : socketServer("localhost", port), quorum(quorum) {
	this->subscribeToQuorum();
	thread(startListening, this).detach();
}

void Site::subscribeToQuorum() {
	for(unsigned int i = 0; i < this->quorum.size(); i++)
	{
		SiteAddress address = this->quorum.getSite(i);
		this->send(address, Request::ADD);
	}
}

void Site::send(SiteAddress address, Request request) {
	Socket client(address.first, address.second);
	client.tryConnect();
	client.sendData(&request, sizeof(Request));
}

void Site::receive(Request request, string address) {
	switch(request)
	{
		case Request::ADD:
			cout << "Receiving ADD from " << address << endl;
			break;
			
		default:
			cout << "Unknown request received from " << address << endl;
	}
}

bool Site::equals(Site site){
    return this->socketServer.equals(site.socketServer) &&
    this->quorum.equals(site.quorum);
}
