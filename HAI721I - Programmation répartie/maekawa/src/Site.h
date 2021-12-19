#ifndef __SITE_H__
#define __SITE_H__

#include "Socket.h"
#include "Quorum.h"
#include "Request.h"
#include <utility>
#include <queue>

typedef pair<SiteAddress, long> SiteWithTimeStamp;

class SitePriorityComparison {
	public:
	bool operator()(const SiteWithTimeStamp& left, const SiteWithTimeStamp& right) {
		return left.second < right.second;
	}
}; 

class Site {
    private:
        Socket socketServer;

		Quorum quorum;
		priority_queue<SiteWithTimeStamp, vector<SiteWithTimeStamp>, SitePriorityComparison> waitingLine;
	
		unsigned int agreementReceived;
		bool hasReceivedFail;
	
		bool isCriticalAccessInUse;
		SiteWithTimeStamp siteWithCriticalAccess;
	

		void sendToQuorum(Request request);
		void send(SiteAddress address, Request request);
		void receive(Request request, string address);
	
		void subscribeToQuorum();
	
		void receiveAddRequest(SiteAddress address);
		void receiveSyncRequest(bool isCriticalAccessInUse, string address, unsigned short port, long timeStamp);
		void receiveDemandRequest(SiteAddress address, long timeStamp);
		void receiveAgreementRequest();
		void receiveFailRequest();
		void receiveRestitutionRequest();
		void receiveReleaseRequest(SiteAddress address);
		void receivePollRequest(SiteAddress address);

		void useCriticalAccess();
	
    public:
        Site(unsigned short port, Quorum quorum);

		void demandCriticalAccess();
	
		unsigned short getPort() const;
	
		friend void startListening(Site* site);
		friend void* connectClient(void* context, void* data);
};

#endif
