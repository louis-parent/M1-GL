#include "Quorum.h"

Quorum::Quorum(unsigned int id) : id(id) {
}

void Quorum::addSite(SiteAddress address){
    this->sites.push_back(address);
}

void Quorum::removeSite(SiteAddress address){
	bool find = false;
	
    for(unsigned int i = 0; i < this->sites.size() && !find; i++) {
        if(address == this->sites[i]) {
            this->sites.erase(this->sites.begin() + i);
            find = true;
        }
    }
}

size_t Quorum::size() const {
	return this->sites.size();
}

SiteAddress Quorum::getSite(int i) const {
	return this->sites[i];
}

bool Quorum::equals(Quorum quorum){
    return this->id == quorum.id;
}
