package fr.umontpellier.tp1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ContactActivity  extends AppCompatActivity
{
    private static int PERMISSION_REQUEST_CODE = 66;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_contact);

        this.initFromIntent();
        this.initListeners();
    }

    private void initFromIntent()
    {
        TextView name = this.findViewById(R.id.contact_name);
        TextView phone = this.findViewById(R.id.contact_phone);

        Bundle extras = getIntent().getExtras();

        name.setText(extras.getString("name"));
        phone.setText(extras.getString("phone"));
    }

    private void initListeners()
    {
        Button call = this.findViewById(R.id.call);
        call.setOnClickListener(view -> {
            this.call();
        });
    }

    private void call()
    {
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.CALL_PHONE}, ContactActivity.PERMISSION_REQUEST_CODE);

        }
        else
        {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getIntent().getExtras().getString("phone")));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == ContactActivity.PERMISSION_REQUEST_CODE)
        {
            for(int i = 0; i < permissions.length; i++)
            {
                String permission = permissions[i];
                int result = grantResults[i];

                if(permission == android.Manifest.permission.CALL_PHONE && result == PackageManager.PERMISSION_GRANTED)
                {
                    this.call();
                }
            }
        }
    }
}