using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP2_Server.model.agency
{
    class Agency
    {
        public string Name
        {
            get;
        }

        private string login;
        private string password;

        public Agency(string name, string login, string password)
        {
            this.Name = name;
            this.login = login;
            this.password = password;
        }

        public bool Connect(string login, string password)
        {
            return this.login.Equals(login) && this.password.Equals(password);
        }
    }
}
