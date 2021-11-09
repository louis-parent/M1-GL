using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using TP2_ServiceServer.model.offer;

namespace TP2_ServiceServer
{
    public class OfferCache
    {
        private IList<Offer> cached;

        public OfferCache()
        {
            this.cached = new List<Offer>();
        }

        public void CacheAll(ICollection<Offer> offers)
        {
            foreach(Offer offer in offers)
            {
                this.Cache(offer);
            }
        }

        public void Cache(Offer offer)
        {
            offer.id = this.GetNextId();
            this.cached.Add(offer);
        }

        public Offer Uncache(int id)
        {
            foreach(Offer offer in this.cached)
            {
                Console.WriteLine(offer.id + " ?= " + id);
                if(offer.id == id)
                {
                    this.cached.Remove(offer);
                    return offer;
                }
            }

            return null;
        }

        private int GetNextId()
        {
            if(this.cached.Count == 0)
            {
                return 0;
            }
            else
            {
                return (from offer in this.cached select offer.id).Max() + 1;
            }
        }
    }
}