package fr.umontpellier.carhiboux.entity.enumeration

import android.view.View
import fr.umontpellier.carhiboux.R

enum class Gearbox(val string : Int)
{
    ANY(R.string.any),
    AUTOMATIC(R.string.automatic),
    MANUAL(R.string.manual),
    SEQUENTIAL(R.string.sequential);

    val view : Int = View.generateViewId()

    companion object
    {
        fun forView(view: Int) : Gearbox
        {
            return when(view) {
                AUTOMATIC.view -> AUTOMATIC
                MANUAL.view -> MANUAL
                SEQUENTIAL.view -> SEQUENTIAL
                else -> ANY
            }
        }
    }
}