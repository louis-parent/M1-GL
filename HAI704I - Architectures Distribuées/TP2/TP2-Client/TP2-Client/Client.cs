using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TP2_Client.HotelReference;

namespace TP2_Client
{
    class Client
    {
        public static void Main(string[] args)
        {
            HotelServiceSoapClient client = new HotelServiceSoapClient();
            Offer[] offers = client.FetchAvailableOffers("airone", "r1", new DateTime(2021, 11, 7), new DateTime(2021, 11, 14), 2);

            foreach(Offer offer in offers)
            {
                Console.WriteLine(offer.id + " " + offer.maxPersonAmount + " " + offer.availabiltyDate + " " + offer.price);
            }

            Console.ReadLine();
        }
    }
}
