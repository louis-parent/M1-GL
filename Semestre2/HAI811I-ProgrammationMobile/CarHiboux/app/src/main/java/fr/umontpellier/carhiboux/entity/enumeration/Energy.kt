package fr.umontpellier.carhiboux.entity.enumeration

import android.view.View
import fr.umontpellier.carhiboux.R

enum class Energy(val string: Int)
{
    ANY(R.string.any),
    GASOLINE(R.string.gasoline),
    DIESEL(R.string.diesel),
    ELECTRIC(R.string.electric),
    GAS(R.string.gas);

    val view : Int = View.generateViewId()

    companion object
    {
        fun forView(view: Int) : Energy
        {
            return when(view) {
                GASOLINE.view -> GASOLINE
                DIESEL.view -> DIESEL
                ELECTRIC.view -> ELECTRIC
                GAS.view -> GAS
                else -> ANY
            }
        }
    }
}