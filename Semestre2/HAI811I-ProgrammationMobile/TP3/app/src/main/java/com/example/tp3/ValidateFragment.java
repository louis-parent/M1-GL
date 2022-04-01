package com.example.tp3;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ValidateFragment extends Fragment {

    private String name;
    private String lastName;
    private String birthday;
    private String phoneNumber;
    private String mail;
    private boolean sport;
    private boolean music;
    private boolean read;
    private boolean isSynchronized;

    private ListenerValidateFragment listener;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.display_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle bundle = this.getArguments();

        init();
        initListeners();

        if(bundle != null){
            ((TextView)getView().findViewById(R.id.name2)).setText(this.name);
            ((TextView)getView().findViewById(R.id.lastname2)).setText(this.lastName);
            ((TextView)getView().findViewById(R.id.birthday2)).setText(this.birthday);
            ((TextView)getView().findViewById(R.id.phone2)).setText(this.phoneNumber);
            ((TextView)getView().findViewById(R.id.mail2)).setText(this.mail);

            ((CheckBox)getView().findViewById(R.id.sport2)).setChecked(this.sport);
            ((CheckBox)getView().findViewById(R.id.music2)).setChecked(this.music);
            ((CheckBox)getView().findViewById(R.id.read2)).setChecked(this.read);
        }
    }

    private void init(){
        Bundle bundle = this.getArguments();

        this.name = bundle.getString("name", "");
        this.lastName = bundle.getString("lastName", "");
        this.phoneNumber = bundle.getString("phoneNumber", "");
        this.birthday = bundle.getString("birthday", "");
        this.mail = bundle.getString("mail", "");

        this.music = bundle.getBoolean("music", false);
        this.sport = bundle.getBoolean("sport", false);
        this.read = bundle.getBoolean("read", false);

        this.isSynchronized = bundle.getBoolean("synchronized", false);
    }

    private void initListeners(){
        getView().findViewById(R.id.return_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.returnUpdate(lastName, name, birthday, music, sport, read);
            }
        });

        getView().findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSynchronized){
                    saveData();
                }
            }
        });
    }

    private void saveData(){
        JSONObject json = new JSONObject();

        try {
            json.put("lastName", this.lastName);
            json.put("name", this.name);
            json.put("phoneNumber", this.phoneNumber);
            json.put("birthday", this.birthday);
            json.put("mail", this.mail);
            json.put("music", this.music);
            json.put("sport", this.sport);
            json.put("read", this.read);

            String data = json.toString();
            File file = new File(getView().getContext().getFilesDir(), "data.json");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(data);
            bufferedWriter.close();

            Toast toast = Toast.makeText(getView().getContext(), "data saved", Toast.LENGTH_LONG);
            toast.show();

        } catch (Exception e) {
            Toast toast = Toast.makeText(getView().getContext(), "Error saving data", Toast.LENGTH_LONG);
            toast.show();
            e.printStackTrace();
        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (ListenerValidateFragment)context;
    }
}
