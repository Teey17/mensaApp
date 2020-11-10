package com.example.tiisetsosemaushu.mensa;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.tiisetsosemaushu.mensa.Controller.MainActivity;
import com.example.tiisetsosemaushu.mensa.Controller.SelectedSpecial;

import java.io.Serializable;

public class MealNotifier  {

    private static final int NOTIFICATION_ID = 123;
    private static String CHANNEL_ID = "meal_channel";
    private static String CHANNEL_DESCRIPTION = "Show meal of the day";
    NotificationCompat.Builder notificationBuilder;
    NotificationManager notificationManager;

    public MealNotifier() {

    }

    public MealNotifier(Context context, WeeklySpecial weeklySpecial) {

        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel("meal_channel");
            if (notificationChannel == null) {
                notificationChannel = new NotificationChannel(CHANNEL_ID,
                        CHANNEL_DESCRIPTION,
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Mensa Meal of the day")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(false);

        Intent resultIntent = new Intent(context, SelectedSpecial.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        resultIntent.putExtra("weeklySpecial", (Serializable) weeklySpecial);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);
        notificationBuilder.setContentIntent(resultPendingIntent);
    }

    public void showOrUpdateNotification() {
        notificationBuilder.setContentText("Come check what the Mensa is offering today");
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    public void removeNotification() {
        notificationManager.cancel(NOTIFICATION_ID);
    }

}
