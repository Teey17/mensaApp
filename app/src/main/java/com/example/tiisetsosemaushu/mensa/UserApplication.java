package com.example.tiisetsosemaushu.mensa;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.HashMap;
import java.util.List;


public class UserApplication extends Application
{

    public static final String APPLICATION_ID = "31C93D4E-550C-ADD5-FF51-1220BEEA2B00";
    public static final String API_KEY = "C8289EBC-F7A2-47B4-9C6A-52F91D9B05DF";
    public static final String SERVER_URL = "https://api.backendless.com";


    public static BackendlessUser user;
    public static List<Comment> comments;



    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );
    }
}
