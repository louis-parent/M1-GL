package fr.umontpellier.noodlecalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EventAdapter extends BaseAdapter
{
    private final List<Event> events;

    public EventAdapter(int year, int month, int day)
    {
        this.events = Event.getAt(year, month, day);
    }

    @Override
    public int getCount()
    {
        if(this.events.isEmpty())
        {
            return 1;
        }
        else
        {
            return this.events.size();
        }
    }

    @Override
    public Object getItem(int i)
    {
        if(this.events.isEmpty())
        {
            return R.string.no_event;
        }
        else
        {
            return this.events.get(i);
        }
    }

    @Override
    public long getItemId(int i)
    {
        if(this.events.isEmpty())
        {
            return R.string.no_event;
        }
        else
        {
            return this.events.get(i).id;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(this.events.isEmpty())
        {
            return this.buildNoEventText(view, viewGroup);
        }
        else
        {
            return this.buildEventItem(i, view, viewGroup);
        }
    }

    private View buildNoEventText(View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            view = new TextView(viewGroup.getContext());
        }

        ((TextView) view).setText(R.string.no_event);

        return view;
    }

    private View buildEventItem(int i, View view, ViewGroup container)
    {
        if(view == null)
        {
            view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_event, container, false);

        }

        Event event = this.events.get(i);

        TextView time = view.findViewById(R.id.time);
        time.setText(event.time);

        TextView title = view.findViewById(R.id.name);
        title.setText(event.title);

        TextView location = view.findViewById(R.id.location);
        location.setText(event.location);

        TextView description = view.findViewById(R.id.description);
        description.setText(event.description);

        return view;
    }
}
