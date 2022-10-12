package fr.umontpellier.bettersncf;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class TrainTravelAdapter extends BaseAdapter
{
    private static final List<TrainTravel> TRAIN_TRAVELS = Arrays.asList(
        new TrainTravel(0, "8h50", "Montpellier Saint Roch", "10h40", "Perpignan", 13.60f, "1h50", 0),
        new TrainTravel(1, "14h20", "Montpellier Saint Roch", "16h10", "Perpignan", 13.60f, "1h50", 0),
        new TrainTravel(2, "18h30", "Montpellier Saint Roch", "20h40", "Perpignan", 27.20f, "2h10", 1),

        new TrainTravel(3, "9h10", "Perpignan", "10h00", "Montpellier Saint Roch", 13.60f, "1h50", 0),
        new TrainTravel(4, "12h15", "Perpignan", "14h25", "Montpellier Saint Roch", 27.20f, "2h10", 1),
        new TrainTravel(5, "21h45", "Perpignan", "23h35", "Montpellier Saint Roch", 6.80f, "1h50", 0),

        new TrainTravel(6, "9h00", "Montpellier Saint Roch", "13h40", "Paris Gare de Lyon", 104f, "4h40", 1),
        new TrainTravel(7, "16h25", "Montpellier Saint Roch", "19h55", "Paris Gare de Lyon", 25f, "3h30", 0),

        new TrainTravel(8, "6h15", "Paris Gare de Lyon", "9h45", "Montpellier Saint Roch", 8f, "3h30", 0),
        new TrainTravel(9, "14h30", "Paris Gare de Lyon", "19h10", "Montpellier Saint Roch", 78f, "4h40", 1)
    );

    private List<TrainTravel> travels;

    public TrainTravelAdapter(Predicate<TrainTravel> filter)
    {
        this.travels = new ArrayList<TrainTravel>();

        for(TrainTravel travel : TRAIN_TRAVELS)
        {
            if(filter.accept(travel))
            {
                this.travels.add(travel);
            }
        }
    }

    @Override
    public int getCount()
    {
        return this.travels.size();
    }

    @Override
    public Object getItem(int i)
    {
        return this.travels.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return this.travels.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup container)
    {
        if (view == null)
        {
            view = LayoutInflater.from(container.getContext()).inflate(R.layout.train_travel_item, container, false);
        }
        TrainTravel travel = this.travels.get(i);

        TextView departure = view.findViewById(R.id.departure);
        departure.setText(travel.departureTime + " " + travel.departureLocation);

        TextView arrival = view.findViewById(R.id.arrival);
        arrival.setText(travel.arrivalTime + " " + travel.arrivalLocation);

        TextView price = view.findViewById(R.id.price);
        price.setText(travel.price + "€");

        TextView duration = view.findViewById(R.id.duration);
        duration.setText(travel.duration);

        TextView changes = view.findViewById(R.id.changes);

        if(travel.changes > 0)
        {
            changes.setText(travel.changes + " " + container.getContext().getResources().getString(R.string.changes));
        }
        else
        {
            changes.setText(R.string.direct);
        }

        return view;
    }
}
