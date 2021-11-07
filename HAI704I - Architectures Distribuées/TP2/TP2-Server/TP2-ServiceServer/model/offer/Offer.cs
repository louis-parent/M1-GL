using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace TP2_ServiceServer.model.offer
{
    [Serializable]
    public class Offer
    {
        public int id;
        public int maxPersonAmount;
        public DateTime availabiltyDate;
        public float price;

        public Offer() : this(0, 0, DateTime.Now, 0)
        { }
        
        public Offer(int id, int maxPersonAmount, DateTime availabiltyDate, float price)
        {
            this.id = id;
            this.maxPersonAmount = maxPersonAmount;
            this.availabiltyDate = availabiltyDate;
            this.price = price;
        }
    }
}