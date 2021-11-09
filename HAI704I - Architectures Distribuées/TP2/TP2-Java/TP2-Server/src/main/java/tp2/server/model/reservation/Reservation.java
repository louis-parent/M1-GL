package tp2.server.model.reservation;

import java.util.Date;

import tp2.server.model.customer.Customer;
import tp2.server.model.room.Room;

public class Reservation
    {
		private Date start;
        private Date end;
        private Customer customer;
        private Room room;

        public Reservation(Date start, Date end, Customer customer, Room room)
        {
            this.start = start;
            this.end = end;
            this.customer = customer;
            this.room = room;
        }

        public boolean concern(Room room)
        {
            return this.room.equals(room);
        }

        public boolean intersect(Date start, Date end)
        {
            return !(end.before(this.start) || start.after(this.end));
        }

        public boolean isLaterThan(Date date)
        {
            return date.before(this.start);
        }

        public boolean isEarlierThan(Date date)
        {
            return date.after(this.end);
        }

		public Date getStart()
		{
			return start;
		}

		public Date getEnd()
		{
			return end;
		}
        
        
    }
