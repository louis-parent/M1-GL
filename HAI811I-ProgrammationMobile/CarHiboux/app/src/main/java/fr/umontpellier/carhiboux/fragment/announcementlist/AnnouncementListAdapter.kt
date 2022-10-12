package fr.umontpellier.carhiboux.fragment.announcementlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import fr.umontpellier.carhiboux.R
import fr.umontpellier.carhiboux.entity.Address
import fr.umontpellier.carhiboux.entity.Announcement
import fr.umontpellier.carhiboux.entity.enumeration.AnnouncementType
import fr.umontpellier.carhiboux.entity.enumeration.Energy
import fr.umontpellier.carhiboux.entity.enumeration.Gearbox

class AnnouncementListAdapter : BaseAdapter()
{
    private val items = arrayOf(Announcement(0, AnnouncementType.SELL, "Renault", "Twingo", 12990.0, Address("1, Wall Street", "00000", "New York", "USA"), 5600, 2021, true, Energy.GASOLINE, Gearbox.MANUAL, "Metalic White", "Gray", 5, 4, 1, 4, 65, 117.0, 5.2, arrayOf()))

    override fun getCount(): Int
    {
        return items.size
    }

    override fun getItem(i: Int): Any
    {
        return items[i]
    }

    override fun getItemId(i: Int): Long
    {
        return items[i].id
    }

    override fun getView(i: Int, view: View?, group: ViewGroup?): View
    {
        val v : View = view ?: LayoutInflater.from(group?.context).inflate(R.layout.announcement_list_item, group, false)

        val item : Announcement = getItem(i) as Announcement
        v.findViewById<TextView>(R.id.announcement_item_title).text = item.brand + " " + item.model
        v.findViewById<TextView>(R.id.announcement_item_subtitle).text = "" + item.kilometers + "km | " + item.year + " | " + item.energy + " | " + item.gearbox + " box | " + item.places + " places"
        v.findViewById<TextView>(R.id.announcement_item_price).text = "" + item.price + "€"
        v.findViewById<TextView>(R.id.announcement_item_place).text = item.address.zip.subSequence(0, 2)

        if(item.type == AnnouncementType.RENT)
        {
            val price = v.findViewById<TextView>(R.id.announcement_item_price)
            price.text = "" + price.text + " /day"

            v.findViewById<TextView>(R.id.announcement_item_type).text = "To Rent"
            v.findViewById<ImageView>(R.id.announcement_item_type_icon).setImageResource(R.drawable.ic_baseline_car_rental_24)
        }
        else
        {
            v.findViewById<TextView>(R.id.announcement_item_type).text = "To Sell"
            v.findViewById<ImageView>(R.id.announcement_item_type_icon).setImageResource(R.drawable.ic_baseline_sell_24)
        }

        return v
    }
}