package com.example.tiisetsosemaushu.mensa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.tiisetsosemaushu.mensa.Controller.Login;
import com.example.tiisetsosemaushu.mensa.Database.WeeklySpecialDatabase;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        WeeklySpecial[] weeklySpecial;
        WeeklySpecial weeklySpecial1O = null;

        WeeklySpecialDatabase weeklySpecialDatabase = new WeeklySpecialDatabase();

        weeklySpecial = weeklySpecialDatabase.weeklySpecial1;
        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int today;

        if (day < 7 && day > 1) {

            for (WeeklySpecial special : weeklySpecial) {
                today = Integer.parseInt(special.getDayValue());
                if (today == day) {
                    weeklySpecial1O = special;
                }

            }
             MealNotifier notifier = new MealNotifier(Login.contextReceiver,weeklySpecial1O);
             notifier.showOrUpdateNotification();
        }
    }
}
