using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TP2_Server.model.room.bed;

namespace TP2_Server.model.room
{
    class Room
    {
        public float Price
        {
            get;
        }

        private ISet<IBed> beds;

        public Room(float price, IBed[] beds) : this(price)
        {
            this.AddBeds(beds);
        }

        public Room(float price)
        {
            this.Price = price;
            this.beds = new HashSet<IBed>();
        }

        public int GetPersonMaxAmount()
        {
            return (from bed in this.beds select bed.GetPersonAmount()).Sum();
        }

        public void AddBeds(IBed[] beds)
        {
            foreach(IBed bed in beds)
            {
                this.AddBed(bed);
            }
        }

        public void AddBed(IBed bed)
        {
            this.beds.Add(bed);
        }

        public bool CanHost(int persons)
        {
            return this.GetPersonMaxAmount() >= persons;
        }
    }
}
