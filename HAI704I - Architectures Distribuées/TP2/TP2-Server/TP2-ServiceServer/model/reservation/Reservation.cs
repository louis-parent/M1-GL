using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TP2_Server.model.customer;
using TP2_Server.model.room;

namespace TP2_Server.model.reservation
{
    class Reservation
    {
        public DateTime Start
        {
            get;
        }

        public DateTime End
        {
            get;
        }

        private Customer customer;
        private Room room;

        public Reservation(DateTime start, DateTime end, Customer customer, Room room)
        {
            this.Start = start;
            this.End = end;
            this.customer = customer;
            this.room = room;
        }

        public bool Concern(Room room)
        {
            return this.room.Equals(room);
        }

        public bool Intersect(DateTime start, DateTime end)
        {
            return !(end < this.Start || start > this.End);
        }

        public bool IsLaterThan(DateTime date)
        {
            return date < this.Start;
        }

        public bool IsEarlierThan(DateTime date)
        {
            return date > this.End;
        }
    }
}
