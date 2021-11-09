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
        private static readonly string LOGIN = "airone";
        private static readonly string PWD = "r1";

        public static void Main(string[] args)
        {
            HotelServiceSoapClient client = new HotelServiceSoapClient();
            Offer[] offers = client.FetchAvailableOffers(Client.LOGIN, Client.PWD, new DateTime(2021, 11, 7), new DateTime(2021, 11, 14), 2);

            foreach(Offer offer in offers)
            {
                Console.WriteLine("Offer proposed : " + offer.id + " " + offer.maxPersonAmount + " " + offer.availabiltyDate + " " + offer.price + " " + offer.image);
            }

            Console.WriteLine();

            Customer customer = new Customer
            {
                FirstName = "Lluis",
                LastName = "Lluis",
                Card = new CreditCard
                {
                    Number = "6600 0066 0660 6006",
                    ExpirationDate = "06/60",
                    CVV = "666"
                }
            };

            Offer selectedOffer = offers[0];
            bool done = client.MakeReservation(Client.LOGIN, Client.PWD, selectedOffer.id, customer);

            if(done)
            {
                Console.WriteLine("Reservation for room for " + selectedOffer.maxPersonAmount + " persons done");
            }
            else
            {
                Console.WriteLine("Reservation refused");
            }

            Console.WriteLine();

            offers = client.FetchAvailableOffers(Client.LOGIN, Client.PWD, new DateTime(2021, 11, 7), new DateTime(2021, 11, 14), 2);

            foreach (Offer offer in offers)
            {
                Console.WriteLine("Offer proposed : " + offer.id + " " + offer.maxPersonAmount + " " + offer.availabiltyDate + " " + offer.price);
            }

            Console.ReadLine();
        }
    }
}
