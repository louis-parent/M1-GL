package fr.umontpellier.carhiboux.entity.enumeration

import android.view.View
import fr.umontpellier.carhiboux.R

enum class AnnouncementType(val string : Int)
{
    ANY(R.string.any),
    RENT(R.string.rent),
    SELL(R.string.sell);

    val view : Int = View.generateViewId()

    companion object
    {
        fun forView(view: Int) : AnnouncementType
        {
            return when(view) {
                RENT.view -> RENT
                SELL.view -> SELL
                else -> ANY
            }
        }
    }
}