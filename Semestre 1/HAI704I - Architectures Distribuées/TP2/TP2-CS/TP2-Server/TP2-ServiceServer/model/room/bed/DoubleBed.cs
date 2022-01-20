using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP2_Server.model.room.bed
{
    class DoubleBed : IBed
    {
        public int GetPersonAmount()
        {
            return 2;
        }
    }
}
