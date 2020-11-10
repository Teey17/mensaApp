package com.example.tiisetsosemaushu.mensa;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.HashMap;
import java.util.List;


public class UserApplication extends Application
{

    public static final String APPLICATION_ID = "C6CC2F29-9C13-93AD-FFC6-878D45649B00";
    public static final String API_KEY = "74C0CBEC-231B-479B-9E6C-055EF249E0C9";
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
