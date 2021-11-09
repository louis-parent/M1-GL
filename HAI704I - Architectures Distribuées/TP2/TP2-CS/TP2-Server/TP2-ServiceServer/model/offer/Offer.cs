using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Web;
using TP2_Server.model.room;

namespace TP2_ServiceServer.model.offer
{
    [Serializable]
    public class Offer
    {
        public int id;
        public int maxPersonAmount;
        public DateTime availabiltyDate;
        public float price;
        public Byte[] image;

        public Room OfferedRoom
        {
            get;
        }

        public DateTime Start
        {
                get;
        }
        public DateTime End
        {
            get;
        }

        public Offer() : this(0, 0, DateTime.Now, 0, DateTime.Now, DateTime.Now, null)
        { }
        
        public Offer(int id, int maxPersonAmount, DateTime availabiltyDate, float price, DateTime start, DateTime end, Room room)
        {
            this.id = id;
            this.maxPersonAmount = maxPersonAmount;
            this.availabiltyDate = availabiltyDate;
            this.price = price;
            this.image = ((byte[]) new ImageConverter().ConvertTo(room.Photo, typeof(Byte[])));
            this.OfferedRoom = room;
            this.Start = start;
            this.End = end;
        }
    }
}