package fr.umontpellier.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_confirm);

        this.initFromIntent();
        this.initListeners();
    }

    private void initFromIntent()
    {
        TextView name = this.findViewById(R.id.confirmation_name);
        TextView phone = this.findViewById(R.id.confirmation_phone);
        TextView skill = this.findViewById(R.id.confirmation_skill);

        Bundle extras = getIntent().getExtras();

        name.setText(extras.getString("first_name") + " " + extras.getString("last_name") + " (" + extras.getString("age") + " " + this.getResources().getString(R.string.year) + ")");
        phone.setText(extras.getString("phone"));
        skill.setText(this.getResources().getString(R.string.skill) + " : \n" + extras.getString("skill"));
    }

    private void initListeners()
    {
        Button cancel = this.findViewById(R.id.cancel);
        cancel.setOnClickListener(view -> {
            this.goBackToInformation();
        });

        Button confirm = this.findViewById(R.id.confirm);
        confirm.setOnClickListener(view -> {
            this.confirmInformation();
        });
    }

    private void goBackToInformation()
    {
        Bundle extras = getIntent().getExtras();

        Intent intent = new Intent(this, InformationActivity.class);
        intent.putExtra("first_name", extras.getString("first_name"));
        intent.putExtra("last_name", extras.getString("last_name"));
        intent.putExtra("age", extras.getString("age"));
        intent.putExtra("skill", extras.getString("skill"));
        intent.putExtra("phone", extras.getString("phone"));
        startActivity(intent);
    }

    private void confirmInformation()
    {
        Bundle extras = getIntent().getExtras();

        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra("name", extras.getString("first_name") + " " + extras.getString("last_name"));
        intent.putExtra("phone", extras.getString("phone"));
        startActivity(intent);
    }
}