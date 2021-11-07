using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TP2_Server.model.customer.card;

namespace TP2_Server.model.customer
{
    class Customer
    {
        private string firstName;
        private string lastName;
        private ISet<CreditCard> cards;
    }
}
