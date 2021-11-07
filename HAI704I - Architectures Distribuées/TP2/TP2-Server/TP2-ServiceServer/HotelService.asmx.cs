using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using TP2_Server.model;
using TP2_Server.model.agency;
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
        private Hotel hotel;

        public HotelService()
        {
            this.hotel = new Hotel("L'Anxova d'Aqui", 5, new Address("8", "Avenue du Miradou", "Collioure", "66190", "Catalogne du nord"), new GPSCoordinate(42.527689, 3.083373));

            Room simpleRoom = new Room(66, new IBed[]{ new SimpleBed() });
            this.hotel.AddRoom(simpleRoom);

            Room familyRoom = new Room(106, new IBed[] { new DoubleBed(), new SimpleBed(), new SimpleBed() });
            this.hotel.AddRoom(familyRoom);

            Room luxuryVIPGoldenPremiumLounge = new Room(666, new IBed[] { new DoubleBed() });
            this.hotel.AddRoom(luxuryVIPGoldenPremiumLounge);

            Agency agency1 = new Agency("Voyage Voyage", "desireless", "1986");
            this.hotel.AddPartnership(agency1, 0.98f);

            Agency agency2 = new Agency("Erwan Aviation", "airone", "r1");
            this.hotel.AddPartnership(agency2, 1f);
        }

        [WebMethod]
        public string HelloWorld()
        {
            return "Hello World";
        }

        [WebMethod]
        public Offer[] FetchAvailableOffers(string login, string password, DateTime start, DateTime end, int persons)
        {
            Agency agency = this.hotel.IsPartner(login, password);

            if(agency != null)
            {
                IList<Room> rooms = this.hotel.GetAvailableRooms(start, end, persons);
                Offer[] offers = new Offer[rooms.Count];

                for (int i = 0; i < rooms.Count; i++)
                {
                    Room room = rooms[i];
                    offers[i] = new Offer(i, room.GetPersonMaxAmount(), this.hotel.GetFirstAvailability(room, start), room.Price * this.hotel.GetRateFor(agency));
                }

                return offers;
            }
            else
            {
                return new Offer[0];
            }
        }
    }
}
