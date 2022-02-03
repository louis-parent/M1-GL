package fr.umontpellier.bettersncf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private TrainTravelAdapter travelListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.travelListAdapter = new TrainTravelAdapter(travel -> { return false; });

        this.setContentView(R.layout.activity_main);
        this.updateTravelList();
        this.addListeners();
    }

    private void updateTravelList()
    {
        ListView travelList = this.findViewById(R.id.travel_list);
        travelList.setAdapter(this.travelListAdapter);
    }

    private void addListeners()
    {
        EditText from = this.findViewById(R.id.from);
        from.addTextChangedListener(new TextValidator() {
            @Override
            public void validate(String text) {
                makeSearch();
            }
        });

        EditText to = this.findViewById(R.id.to);
        to.addTextChangedListener(new TextValidator() {
            @Override
            public void validate(String text) {
                makeSearch();
            }
        });

        FloatingActionButton swap = this.findViewById(R.id.swap_locations);
        swap.setOnClickListener(button -> {
            this.swapLocations();
        });
    }

    private void makeSearch()
    {
        EditText from = this.findViewById(R.id.from);
        EditText to = this.findViewById(R.id.to);

        String fromLocation = from.getText().toString();
        String toLocation = to.getText().toString();

        if(!fromLocation.isEmpty() && !toLocation.isEmpty())
        {
            this.travelListAdapter = new TrainTravelAdapter(travel -> {
                return travel.departureLocation.toLowerCase().contains(fromLocation.toLowerCase())
                        &&
                        travel.arrivalLocation.toLowerCase().contains(toLocation.toLowerCase());
            });
            this.updateTravelList();
        }
    }

    private void swapLocations()
    {
        EditText from = this.findViewById(R.id.from);
        EditText to = this.findViewById(R.id.to);

        Editable temp = from.getText();
        from.setText(to.getText());
        to.setText(temp);
    }
}