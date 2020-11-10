package com.example.tiisetsosemaushu.mensa.Controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.tiisetsosemaushu.mensa.R;
import com.example.tiisetsosemaushu.mensa.UserApplication;
import com.example.tiisetsosemaushu.mensa.Util;
public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword;


    /*
     * Used for the Login splash screen
     * */
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmailMain);
        etPassword = findViewById(R.id.etPasswordMain);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);


    }

    /*
    * View method for when Login button is clicked
    * Checks is the user entered valid then it will proceed to log the user in if it isn a valid login.
    *
    * * */
    public void Login(View view)
    {
        boolean isEmpty = Util.isEmpty(etEmail,etPassword);
        String mail, password;

        if (isEmpty)
        {
            Util.toast(MainActivity.this,"Please enter all fields");
        }
        else
        {
            mail = etEmail.getText().toString();
            password = etPassword.getText().toString();

            //Sets the text for the splash screen
            tvLoad.setText("Busy signing you in please wait");
            //displays the progress view.
            showProgress(true);

            //Logs the user in to the application
            Backendless.UserService.login(mail, password, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser response) {

                    //hides the progress view
                    showProgress(false);
                    Util.toast(MainActivity.this,"User successfully logged in");
                    UserApplication.user = response;
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                    finish();

                }

                //In case something goes wrong with registering the user this method will display the error message
                @Override
                public void handleFault(BackendlessFault fault) {

                    Util.toast(MainActivity.this, "Error: "+fault.getMessage());
                    showProgress(false);

                }
            },false);

        }
    }

    /*
    * Starts Register activity when the user opts to Register
    * */
    public void Register(View view)
    {
        Intent intent = new Intent(MainActivity.this,Register.class);
        startActivity(intent);
        finish();
    }


    /**
     * Shows the progress UI and hides the login form.
     * Provided by Android Studio (Login Activity)
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
