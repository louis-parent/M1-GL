using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TP2_Server.model.room.bed;

namespace TP2_Server.model.room
{
    public class Room
    {
        public float Price
        {
            get;
        }

        public Image Photo
        {
            get;
        }

        private ISet<IBed> beds;


        public Room(float price, IBed[] beds) : this(price, new Bitmap(1, 1))
        {
            this.AddBeds(beds);
        }

        public Room(float price, Image photo)
        {
            this.Price = price;
            this.Photo = photo;
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
