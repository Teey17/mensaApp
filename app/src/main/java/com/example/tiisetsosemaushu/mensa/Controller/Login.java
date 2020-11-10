package com.example.tiisetsosemaushu.mensa.Controller;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.tiisetsosemaushu.mensa.AlarmReceiver;
import com.example.tiisetsosemaushu.mensa.Database.WeeklySpecialDatabase;
import com.example.tiisetsosemaushu.mensa.MealNotifier;
import com.example.tiisetsosemaushu.mensa.R;
import com.example.tiisetsosemaushu.mensa.Util;
import com.example.tiisetsosemaushu.mensa.WeeklySpecial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;


public class Login extends AppCompatActivity implements Serializable
{

    /*
    * Used for the Login splash screen
    * */
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    public static Context contextReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        contextReceiver = getApplicationContext();


        mySharedPreference();

    }

    /*
    * Goes to the Weekly Special List activity when the button is clicked
    * */
    public void WeelySpecial(View view)
    {
        Intent intent = new Intent(Login.this,WeeklySpecialList.class);
        startActivity(intent);
    }


    /*
     * Goes to the Today's Special activity when the button is clicked
     * This method gets the WeelySpecial[] list and assigns one special to the weekly special1o object which-
     * is thw selected special.
     * This method gets the day of the week from the system in order to go to the appropriate item when selected.
     * If it is not a week day the it will notify the user that the Mensa is closed
     * */
    public void todaySpecial(View view)
    {




        WeeklySpecial[] weeklySpecial;
        WeeklySpecial weeklySpecial1O = null;

        WeeklySpecialDatabase weeklySpecialDatabase = new WeeklySpecialDatabase();

        weeklySpecial = weeklySpecialDatabase.weeklySpecial1;
        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int today;

        if (day < 7 && day > 1)
        {

            for (WeeklySpecial special : weeklySpecial) {
                today = Integer.parseInt(special.getDayValue());
                if (today == day) {
                    weeklySpecial1O = special;
                }

            }

            Intent intent = new Intent(Login.this, SelectedSpecial.class);
                intent.putExtra("weeklySpecial", (Serializable) weeklySpecial1O);
                startActivity(intent);
        }
        else
        {
            Util.toast(Login.this,"Mensa is Closed on Weekends");
        }

    }

    /*
     * Going to the DessertsSelected activity when the button is clicked
     * */
    public void DessertsClicked(View view)
    {
        Intent intent = new Intent(Login.this,DessertsSelected.class);
        startActivity(intent);
    }

    public void CommentsCilcked(View view)
    {

        Intent intent = new Intent(Login.this, Comments.class);
        startActivity(intent);



    }

   /*
   * To inflate the Toolbar with the custom menu
   * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }



    /*
    * Does a specific process depending on which Menu was selected from the tool bar
    * Case share: Starts an intent for the camera fot the user to take the picture then they can send to a friend.
    * Case location: Opens a new map application and gives the user the directions to the university.
    * Case logout: Logs the user out of the applications and goes to the Home activity(Main Activity)
    * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.share:

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 101);
                }

                break;

            case R.id.location:
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0`?q=HS ULM Mensa Prittwitzstraße 10, 89075 Ulm"));
                startActivity(intent1);
                break;

            case R.id.logout:

                showProgress(true);
                tvLoad.setText("Logging out");
                Backendless.UserService.logout(new AsyncCallback<Void>()
                {
                    /*
                    * If the user was logged out successfully
                    * */
                    @Override
                    public void handleResponse(Void response) {
                        Intent intent2 = new Intent(Login.this,MainActivity.class);
                        startActivity(intent2);
                        finish();

                        showProgress(false);

                    }
                    /*
                    * If something goes wrong and can't log the user out. The user will be notified
                    * */
                    @Override
                    public void handleFault(BackendlessFault fault) {

                        Util.toast(Login.this,"Error: "+fault.getMessage());
                    }
                });
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            try
            {
                Uri uri = saveImage(bitmap);
                shareImageUri(uri);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    /*
    * Gets the Image Uri and starts the Send intent
    * @Uri image Uri to send as an extra stream
    * */
    private void shareImageUri(Uri uri)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.putExtra(Intent.EXTRA_TEXT,"Have this at THU Mensa, :)");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/jpeg");
        startActivity(intent);
    }

    /*
    * Saves the image as PNG to the app's cache directory.
     *
     * @param image Bitmap to save.
     * @return Uri of the saved file or null
    * */
    private Uri saveImage(Bitmap image) {


        File imagesFolder = new File(getCacheDir(), "images");
        Uri uri = null;
        try {
            imagesFolder.mkdirs();
            File file = new File(imagesFolder, "shared_image.jpeg");

            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.flush();
            stream.close();
            uri = FileProvider.getUriForFile(this, "com.mydomain.fileprovider", file);

        } catch (IOException e) {
            Util.toast(Login.this, "Error: " + e.getMessage());
        }
        return uri;
    }

    //When the user opens the app for the first time
    private void mySharedPreference(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {

            Intent alarmIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 12);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 1);

            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.apply();
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     * Provided by Android Studio. (Login Application )
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }



}
