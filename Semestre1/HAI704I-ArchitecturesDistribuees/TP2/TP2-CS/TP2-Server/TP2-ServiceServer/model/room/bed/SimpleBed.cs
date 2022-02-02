using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP2_Server.model.room.bed
{
    class SimpleBed : IBed
    {
        public int GetPersonAmount()
        {
            return 1;
        }
    }
}
