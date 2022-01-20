using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TP2_Server.model.agency;
using TP2_Server.model.customer;
using TP2_Server.model.reservation;
using TP2_Server.model.room;
using TP2_ServiceServer.model.offer;

namespace TP2_Server.model
{
    class Address
    {
        public string Number
        {
            get;
            set;
        }

        public string Street
        {
            get;
            set;
        }

        public string City
        {
            get;
            set;
        }

        public string ZipCode
        {
            get;
            set;
        }

        public string Country
        {
            get;
            set;
        }

        public Address(string number, string street, string city, string zipCode, string country)
        {
            Number = number;
            Street = street;
            City = city;
            ZipCode = zipCode;
            Country = country;
        }
    }

    class GPSCoordinate
    {
        public double Latitude
        {
            get;
            set;
        }

        public double Longitude
        {
            get;
            set;
        }

        public GPSCoordinate(double latitude, double longitude)
        {
            Latitude = latitude;
            Longitude = longitude;
        }
    }

    class Hotel
    {
        private string name;
        private int stars;
        private Address address;
        private GPSCoordinate gpsCoordinate;
        private ICollection<Room> rooms;
        private ICollection<Reservation> reservations;
        private IDictionary<Agency, float> partnerships;

        public Hotel(string name, int stars, Address address, GPSCoordinate gpsCoordinate)
        {
            this.name = name;
            this.stars = stars;
            this.address = address;
            this.gpsCoordinate = gpsCoordinate;

            this.rooms = new HashSet<Room>();
            this.reservations = new HashSet<Reservation>();
            this.partnerships = new Dictionary<Agency, float>();
        }

        public void AddRoom(Room room)
        {
            this.rooms.Add(room);
        }

        public void AddReservation(Reservation reservation)
        {
            this.reservations.Add(reservation);
        }

        public void AddPartnership(Agency agency, float rate)
        {
            this.partnerships.Add(agency, rate);
        }

        public Agency IsPartner(string login, string password)
        {
            foreach(Agency agency in this.partnerships.Keys)
            {
                if(agency.Connect(login, password))
                {
                    return agency;
                }
            }

            return null;
        }

        public IList<Room> GetAvailableRooms(DateTime start, DateTime end, int persons)
        {
            IList<Room> rooms = new List<Room>();

            foreach(Room room in this.rooms)
            {
                if(room.CanHost(persons) && this.IsFree(room, start, end))
                {
                    rooms.Add(room);
                }
            }

            return rooms;
        }

        public bool IsFree(Room room, DateTime start, DateTime end)
        {
            foreach(Reservation reservation in this.reservations)
            {
                if(reservation.Concern(room) && reservation.Intersect(start, end))
                {
                    return false;
                }
            }

            return true;
        }

        public float GetRateFor(Agency agency)
        {
            return this.partnerships[agency];
        }

        public DateTime GetFirstAvailability(Room room, DateTime start)
        {
            DateTime first = DateTime.MinValue;

            foreach(Reservation reservation in this.reservations)
            {
                if(reservation.Concern(room) && reservation.IsLaterThan(first) && reservation.IsEarlierThan(start))
                {
                    first = reservation.End.AddDays(1);
                }
            }

            return first;
        }

        public void MakeReservation(Offer offer, Customer customer)
        {
            this.reservations.Add(new Reservation(offer.Start, offer.End, customer, offer.OfferedRoom));
        }
    }
}
