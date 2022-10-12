package fr.umontpellier.noodlecalendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_calendar);

        this.init();
        this.initListeners();
    }

    private void init()
    {
        this.updateDateSelection();
    }

    private void initListeners()
    {
        CalendarView calendar = this.findViewById(R.id.calendar);
        calendar.setOnDateChangeListener((view, year, month, day) -> {
            this.updateDateSelection();
        });

        FloatingActionButton button = this.findViewById(R.id.add);
        button.setOnClickListener(view -> {
            this.createEvent();
        });
    }

    private void updateDateSelection()
    {
        CalendarView view = this.findViewById(R.id.calendar);
        ListView eventList = this.findViewById(R.id.events);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(view.getDate());

        eventList.setAdapter(new EventAdapter(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)));
    }

    private void createEvent()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        View contentView = this.getLayoutInflater().inflate(R.layout.create_event_dialog, null);
        alertDialogBuilder.setView(contentView);

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNegativeButton(R.string.cancel, (DialogInterface dialog, int id) -> {});

        alertDialogBuilder.setPositiveButton(R.string.create, (DialogInterface dialog, int id) -> {
            CalendarView calendar = this.findViewById(R.id.calendar);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(calendar.getDate());

            EditText title = contentView.findViewById(R.id.event_title);
            EditText description = contentView.findViewById(R.id.event_description);
            EditText time = contentView.findViewById(R.id.event_time);
            EditText location = contentView.findViewById(R.id.event_location);

            this.insertEventAndUpdate(new Event(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), time.getText().toString(), title.getText().toString(), description.getText().toString(), location.getText().toString()));
        });

        alertDialogBuilder.create().show();
    }

    private void insertEventAndUpdate(Event event)
    {
        Event.add(event);
        this.updateDateSelection();
    }
}