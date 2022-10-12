package com.example.tp3;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class PrefillService extends Service {

    private final LocalBlinder binder = new LocalBlinder();
    private ListenerService listener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void setListener(ListenerService listener){
        this.listener = listener;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new DonwloadJsonTask().execute();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // replace data
        super.onDestroy();
    }

    private class DonwloadJsonTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String result = "";

            try {
                URL url = new URL("https://pastebin.com/raw/2xgpgarW");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                result = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())).readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String response) {
            try {
                JSONObject json = new JSONObject(response);
                listener.getUpdate(json.getString("lastName"), json.getString("name"), json.getString("birthday"), json.getString("phoneNumber"), json.getString("mail"), json.getBoolean("sport"), json.getBoolean("music"), json.getBoolean("read"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public class LocalBlinder extends Binder{
        PrefillService getService() {
            return (PrefillService.this);
        }
    }
}
