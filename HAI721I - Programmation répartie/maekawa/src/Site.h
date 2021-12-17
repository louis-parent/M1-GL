#ifndef __SITE_H__
#define __SITE_H__

#include "Socket.h"
#include "Quorum.h"
#include "Request.h"
#include <utility>
#include <queue>

typedef pair<SiteAddress, int> SiteWithPriority;

class SitePriorityComparison {
	public:
	bool operator()(const SiteWithPriority& left, const SiteWithPriority& right) {
		return left.second < right.second;
	}
}; 

class Site {
    private:
        Socket socketServer;
		int priority;

		Quorum quorum;
		priority_queue<SiteWithPriority, vector<SiteWithPriority>, SitePriorityComparison> waitingLine;
	
		unsigned int agreementReceived;
		bool hasReceivedFail;
	
		bool isCriticalAccessInUse;
		SiteWithPriority siteWithCriticalAccess;
	

		void sendToQuorum(Request request);
		void send(SiteAddress address, Request request);
		void receive(Request request, string address);
	
		void subscribeToQuorum();
	
		void receiveAddRequest(SiteAddress address);
		void receiveSyncRequest(bool isCriticalAccessInUse, string address, unsigned short port, int priority);
		void receiveDemandRequest(SiteAddress address, int priority);
	
    public:
        Site(unsigned short port, Quorum quorum, int priority);

		void demandCriticalAccess();
	
		unsigned short getPort() const;
        bool equals(Site site);
	
		friend void startListening(Site* site);
		friend void* connectClient(void* context, void* data);
};

#endif
