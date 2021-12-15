#ifndef __QUORUM_H__
#define __QUORUM_H__

#include <vector>
#include <string>
#include <utility>

using namespace std;

typedef pair<string, unsigned short> SiteAddress;

class Quorum {
    private:
        unsigned int id;
        vector<SiteAddress> sites;

    public:
        Quorum(unsigned int id);
	
        void addSite(SiteAddress address);
        void removeSite(SiteAddress address);
	
		size_t size() const;
		SiteAddress getSite(int i) const;

        bool equals(Quorum quorum);
};

#endif
