#include <string>
#include <iostream>

#include "Site.h"

using namespace std;

int main(int argc, char* argv[]) {
	
	if(argc < 3)
	{
		cerr << "Usage : " << argv[0] << " ID, QUORUM [, IP:PORT...] " << endl;
		return 1;
	}
	else
	{
		int siteId = stoi(argv[1]);
		int quorumId = stoi(argv[2]);
		
		Quorum quorum(quorumId);
		
		for(int i = 3; i < argc; i++)
		{
			string site(argv[i]);
			size_t separatorPos = site.find(":");
			
			string ip = site.substr(0, separatorPos);
			unsigned short port = stoi(site.substr(separatorPos+1, site.length()));
			SiteAddress address(ip, port);
			
			quorum.addSite(address);
		}
		
		Site site(siteId, quorum);
	}
	
	while(true){}
	
    return 0;
}
