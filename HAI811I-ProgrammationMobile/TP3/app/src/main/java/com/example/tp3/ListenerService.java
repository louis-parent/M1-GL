package com.example.tp3;

public interface ListenerService{
    abstract void getUpdate(String lastname, String name, String birthday, String phoneNumber, String mail, boolean sportChecked, boolean musicChecked, boolean readChecked);
}