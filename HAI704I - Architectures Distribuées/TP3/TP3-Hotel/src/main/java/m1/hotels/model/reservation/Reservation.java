package m1.hotels.model.reservation;

import java.util.Date;

import m1.hotels.model.customer.Customer;
import m1.hotels.model.room.Room;

public class Reservation
    {
		private static int MAX_ID = 0;
		
		private final int id;
		private Date start;
        private Date end;
        private Customer customer;
        private Room room;

        public Reservation(Date start, Date end, Customer customer, Room room)
        {
        	this.id = Reservation.MAX_ID++;
            this.start = start;
            this.end = end;
            this.customer = customer;
            this.room = room;
        }

        public int getId()
        {
        	return this.id;
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
