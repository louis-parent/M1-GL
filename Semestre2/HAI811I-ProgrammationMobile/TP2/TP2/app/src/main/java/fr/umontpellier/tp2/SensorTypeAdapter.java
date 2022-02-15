package fr.umontpellier.tp2;

import android.hardware.Sensor;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

public class SensorTypeAdapter extends BaseAdapter
{
    List<SensorType> types;

    public SensorTypeAdapter(List<SensorType> types)
    {
        this.types = types;
    }

    @Override
    public int getCount()
    {
        return this.types.size();
    }

    @Override
    public Object getItem(int i)
    {
        return this.types.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return this.types.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            view = new TextView(viewGroup.getContext());
        }

        ((TextView) view).setText(this.types.get(i).getName());

        return view;
    }
}
