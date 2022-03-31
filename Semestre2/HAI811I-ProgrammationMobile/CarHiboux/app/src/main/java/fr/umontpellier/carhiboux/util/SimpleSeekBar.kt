package fr.umontpellier.carhiboux.util

import android.content.Context
import android.widget.SeekBar

class SimpleSeekBar(context: Context) : androidx.appcompat.widget.AppCompatSeekBar(context)
{
    fun getValue() : Double
    {
        return ((progress.toDouble() / max.toDouble()) * width.toDouble())
    }

    fun setValue(value : Double)
    {
        progress = ((value / width.toDouble()) * max.toDouble()).toInt()
    }
}