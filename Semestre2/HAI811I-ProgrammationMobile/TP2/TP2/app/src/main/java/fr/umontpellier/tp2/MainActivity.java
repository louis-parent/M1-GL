package fr.umontpellier.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.init();
    }

    private void init()
    {
        ListView sensors = this.findViewById(R.id.sensors);
        sensors.setAdapter(new SensorTypeAdapter(SensorTypes.getAll()));
    }
}