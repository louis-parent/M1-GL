using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP2_Server.model.customer.card
{
    [Serializable]
    public class CreditCard
    {
        public string Number
        {
            get;
            set;
        }

        public string ExpirationDate
        {
            get;
            set;
        }

        public string CVV
        {
            get;
            set;
        }
    }
}
