#include <string>
#include <iostream>

#include "Site.h"

using namespace std;

int main(int argc, char* argv[]) {
	
	if(argc < 4)
	{
		cerr << "Usage : " << argv[0] << " SITE PORT, PRIORITY, QUORUM [, IP:PORT...] " << endl;
		return 1;
	}
	else
	{
		int sitePort = stoi(argv[1]);
		int priority = stoi(argv[2]);
		int quorumId = stoi(argv[3]);
		
		Quorum quorum(quorumId);
		
		for(int i = 4; i < argc; i++)
		{
			string site(argv[i]);
			size_t separatorPos = site.find(":");
			
			string ip = site.substr(0, separatorPos);
			unsigned short port = stoi(site.substr(separatorPos+1, site.length()));
			SiteAddress address(ip, port);
			
			quorum.addSite(address);
		}
		
		Site site(sitePort, quorum, priority);
	}
	
	while(true){}
	
    return 0;
}
