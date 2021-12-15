#ifndef __SITE_H__
#define __SITE_H__

#include "Socket.h"
#include "Quorum.h"
#include "Request.h"

class Site {
    private:
        Socket socketServer;
        Quorum quorum;
		vector<SiteAddress> waitingList;

    public:
        Site(unsigned short port, Quorum quorum);
	
		void subscribeToQuorum();
		void send(SiteAddress address, Request request);
		void* connectClient(void* data);
		void receive(Request request, string address);
	
        bool equals(Site site);
	
		friend void startListening(Site* site);
		friend void* connectClient(void* context, void* data);
};

#endif
