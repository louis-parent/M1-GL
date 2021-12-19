#include <string>
#include <iostream>

#include "Site.h"

using namespace std;

int main(int argc, char* argv[]) {
	
	if(argc < 2)
	{
		cerr << "Usage : " << argv[0] << " SITE PORT, [, IP:PORT...] " << endl;
		return 1;
	}
	else
	{
		int sitePort = stoi(argv[1]);
		
		Quorum quorum;
		
		for(int i = 2; i < argc; i++)
		{
			string site(argv[i]);
			size_t separatorPos = site.find(":");
			
			string ip = site.substr(0, separatorPos);
			unsigned short port = stoi(site.substr(separatorPos+1, site.length()));
			SiteAddress address(ip, port);
			
			quorum.addSite(address);
		}
		
		Site site(sitePort, quorum);

		char a;
		while(true){
			cout << "faire une demande: " << endl;
			cin >> a;

			site.demandCriticalAccess();
		}
	}
	
    return 0;
}

/**
 * Lancement du projet
 * 
 * Terminal 1: ./maekawa 3001
 * Terminal 2: ./maekawa 3002 localhost:3001
 * Terminal 3: ./maekawa 3003 localhost:3001 localhost:3002
 * Terminal 4: ./maekawa 3004 localhost:3001 localhost:3002 localhost:3003
 * Terminal 5: ./maekawa 3005 localhost:3003 localhost:3004
 * Terminal 6: ./maekawa 3006 localhost:3003 localhost:3004 localhost:3005
 * 
 */