package com.example.tp3;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class InputFragment extends Fragment implements ListenerService {
    private ListenerInputFragment listener;

    private EditText name;
    private EditText lastName;
    private EditText birthday;
    private EditText phoneNumber;
    private EditText mail;
    private CheckBox sport;
    private CheckBox music;
    private CheckBox read;
    private Switch sync;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initListener(){
        Button submit = getView().findViewById(R.id.submit_button);
        submit.setOnClickListener(view -> {
            String nameValue = name.getText().toString();
            String lastNameValue = lastName.getText().toString();
            String mailValue = mail.getText().toString();
            String dateValue = birthday.getText().toString();
            String phoneValue = phoneNumber.getText().toString();

            boolean sportChecked = sport.isChecked();
            boolean musicChecked = music.isChecked();
            boolean readChecked = read.isChecked();

            boolean isSynchronized = sync.isChecked();

            listener.getUpdate(lastNameValue, nameValue, dateValue, phoneValue, mailValue, sportChecked, musicChecked, readChecked, isSynchronized);
        });

        getView().findViewById(R.id.download_button).setOnClickListener(view -> {
            Intent intent = new Intent(getView().getContext(), PrefillService.class);
            getView().getContext().startService(intent);
            getView().getContext().bindService(intent, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder service) {
                    ((PrefillService.LocalBlinder)service).getService().setListener(InputFragment.this);
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {

                }
            }, Context.BIND_AUTO_CREATE);

            resetFields();
        });
    }

    private void init(){
        this.name = getView().findViewById(R.id.name);
        this.lastName = getView().findViewById(R.id.lastname);
        this.mail = getView().findViewById(R.id.mail);
        this.birthday = getView().findViewById(R.id.birthday);
        this.phoneNumber = getView().findViewById(R.id.phone);

        this.sport = getView().findViewById(R.id.sport);
        this.music = getView().findViewById(R.id.music);
        this.read = getView().findViewById(R.id.read);

        this.sync = getView().findViewById(R.id.sync);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle bundle = this.getArguments();

        this.init();
        this.initListener();

        if(bundle != null){
            this.name.setText(bundle.getString("name", ""));
            this.lastName.setText(bundle.getString("lastName", ""));
            this.mail.setText(bundle.getString("mail", ""));
            this.birthday.setText(bundle.getString("birthday", ""));
            this.phoneNumber.setText(bundle.getString("phoneNumber", ""));

            this.sport.setChecked(bundle.getBoolean("sport", false));
            this.music.setChecked(bundle.getBoolean("music", false));
            this.read.setChecked(bundle.getBoolean("read", false));

            this.sync.setChecked(bundle.getBoolean("sync", false));
        }
    }

    public void resetFields(){
        name.setText("");
        lastName.setText("");
        birthday.setText("");
        phoneNumber.setText("");
        mail.setText("");

        music.setChecked(false);
        sport.setChecked(false);
        read.setChecked(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.input_fragment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (ListenerInputFragment)context;
    }

    @Override
    public void getUpdate(String lastname, String name, String birthday, String phoneNumber, String mail, boolean sportChecked, boolean musicChecked, boolean readChecked) {
        this.name.setText(name);
        this.lastName.setText(lastname);
        this.mail.setText(mail);
        this.birthday.setText(birthday);
        this.phoneNumber.setText(phoneNumber);

        this.sport.setChecked(sportChecked);
        this.music.setChecked(musicChecked);
        this.read.setChecked(readChecked);
    }
}
