using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using TP2_Server.model;
using TP2_Server.model.agency;
using TP2_Server.model.customer;
using TP2_Server.model.room;
using TP2_Server.model.room.bed;
using TP2_ServiceServer.model.offer;

namespace TP2_ServiceServer
{
    /// <summary>
    /// Description résumée de HotelService
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Pour autoriser l'appel de ce service Web depuis un script à l'aide d'ASP.NET AJAX, supprimez les marques de commentaire de la ligne suivante. 
    // [System.Web.Script.Services.ScriptService]
    public class HotelService : System.Web.Services.WebService
    {
        private static Hotel hotel;
        private static OfferCache cache;

        static HotelService()
        {
            HotelService.hotel = new Hotel("L'Anxova d'Aqui", 5, new Address("8", "Avenue du Miradou", "Collioure", "66190", "Catalogne du nord"), new GPSCoordinate(42.527689, 3.083373));

            Room simpleRoom = new Room(66, new IBed[]{ new SimpleBed() });
            HotelService.hotel.AddRoom(simpleRoom);

            Room familyRoom = new Room(106, new IBed[] { new DoubleBed(), new SimpleBed(), new SimpleBed() });
            HotelService.hotel.AddRoom(familyRoom);

            Room luxuryVIPGoldenPremiumLounge = new Room(666, new IBed[] { new DoubleBed() });
            HotelService.hotel.AddRoom(luxuryVIPGoldenPremiumLounge);

            Agency agency1 = new Agency("Voyage Voyage", "desireless", "1986");
            HotelService.hotel.AddPartnership(agency1, 0.98f);

            Agency agency2 = new Agency("Erwan Aviation", "airone", "r1");
            HotelService.hotel.AddPartnership(agency2, 1f);

            HotelService.cache = new OfferCache();
        }

        [WebMethod]
        public string HelloWorld()
        {
            return "Hello World";
        }

        [WebMethod]
        public Offer[] FetchAvailableOffers(string login, string password, DateTime start, DateTime end, int persons)
        {
            Agency agency = HotelService.hotel.IsPartner(login, password);

            if(agency != null)
            {
                IList<Room> rooms = HotelService.hotel.GetAvailableRooms(start, end, persons);
                Offer[] offers = new Offer[rooms.Count];

                for (int i = 0; i < rooms.Count; i++)
                {
                    Room room = rooms[i];
                    offers[i] = new Offer(0, room.GetPersonMaxAmount(), HotelService.hotel.GetFirstAvailability(room, start), room.Price * HotelService.hotel.GetRateFor(agency), start, end, room);
                }

                HotelService.cache.CacheAll(offers);
                return offers;
            }
            else
            {
                return new Offer[0];
            }
        }

        [WebMethod]
        public bool MakeReservation(string login, string password, int id, Customer customer)
        {
            Agency agency = HotelService.hotel.IsPartner(login, password);

            if (agency != null)
            {
                Offer offer = HotelService.cache.Uncache(id);
                HotelService.hotel.MakeReservation(offer, customer);
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
