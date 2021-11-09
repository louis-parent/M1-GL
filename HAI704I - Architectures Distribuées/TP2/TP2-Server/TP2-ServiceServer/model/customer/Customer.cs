using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TP2_Server.model.customer.card;

namespace TP2_Server.model.customer
{
    public class Customer
    {
        public string FirstName
        {
            get;
            set;
        }

        public string LastName
        {
            get;
            set;
        }

        public CreditCard Card
        {
            get;
            set;
        }

        public Customer() : this("", "", null)
        {
        }

        public Customer(string firstName, string lastName, CreditCard cards)
        {
            this.FirstName = firstName;
            this.LastName = lastName;
            this.Card = cards;
        }
    }
}
