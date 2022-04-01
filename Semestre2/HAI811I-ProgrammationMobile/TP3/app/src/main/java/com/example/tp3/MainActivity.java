package com.example.tp3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListenerInputFragment, ListenerValidateFragment {

    FragmentManager fragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        this.init();
    }

    public void init(){
        fragmentManager = this.getFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();

        InputFragment inputFragment = new InputFragment();
        ft.add(R.id.mainview, inputFragment);
        ft.commit();
    }

    @Override
    public void getUpdate(String lastname, String name, String birthday, String phoneNumber, String mail, boolean sportChecked, boolean musicChecked, boolean readChecked, boolean isSynchronized) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("lastName", lastname);
        bundle.putString("birthday", birthday);
        bundle.putString("phoneNumber", phoneNumber);
        bundle.putString("mail", mail);

        bundle.putBoolean("sport", sportChecked);
        bundle.putBoolean("music", musicChecked);
        bundle.putBoolean("read", readChecked);

        bundle.putBoolean("synchronized", isSynchronized);

        ValidateFragment validateFragment = new ValidateFragment();
        validateFragment.setArguments(bundle);

        ft.replace(R.id.mainview, validateFragment);
        ft.commit();
    }

    @Override
    public void returnUpdate(String lastname, String name, String birthday, boolean sportChecked, boolean musicChecked, boolean readChecked) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("lastName", lastname);
        bundle.putString("name", name);
        bundle.putString("birthday", birthday);

        bundle.putBoolean("sport", sportChecked);
        bundle.putBoolean("music", musicChecked);
        bundle.putBoolean("read", readChecked);

        InputFragment inputFragment = new InputFragment();
        inputFragment.setArguments(bundle);

        ft.replace(R.id.mainview, inputFragment);
        ft.commit();
    }
}
