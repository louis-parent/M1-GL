package fr.umontpellier.carhiboux.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.umontpellier.carhiboux.R

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}