package fr.umontpellier.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InformationActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_information);

        this.initFromIntent();
        this.initListeners();
    }

    private void initFromIntent()
    {
        EditText firstName = this.findViewById(R.id.firstName);
        EditText lastName = this.findViewById(R.id.lastName);
        EditText age = this.findViewById(R.id.age);
        EditText skill = this.findViewById(R.id.skill);
        EditText phone = this.findViewById(R.id.phone);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            if(extras.containsKey("first_name"))
            {
                firstName.setText(extras.getString("first_name"));
            }

            if(extras.containsKey("last_name"))
            {
                lastName.setText(extras.getString("last_name"));
            }

            if(extras.containsKey("age"))
            {
                age.setText(extras.getString("age"));
            }

            if(extras.containsKey("skill"))
            {
                skill.setText(extras.getString("skill"));
            }

            if(extras.containsKey("phone"))
            {
                phone.setText(extras.getString("phone"));
            }
        }
    }

    private void initListeners()
    {
        Button validate = findViewById(R.id.validate_information);
        validate.setOnClickListener(view -> {
            this.validateForm();
        });
    }

    private void validateForm()
    {
        EditText firstName = this.findViewById(R.id.firstName);
        EditText lastName = this.findViewById(R.id.lastName);
        EditText age = this.findViewById(R.id.age);
        EditText skill = this.findViewById(R.id.skill);
        EditText phone = this.findViewById(R.id.phone);

        Intent intent = new Intent(this, ConfirmActivity.class);
        intent.putExtra("first_name", firstName.getText().toString());
        intent.putExtra("last_name", lastName.getText().toString());
        intent.putExtra("age", age.getText().toString());
        intent.putExtra("skill", skill.getText().toString());
        intent.putExtra("phone", phone.getText().toString());
        startActivity(intent);
    }
}