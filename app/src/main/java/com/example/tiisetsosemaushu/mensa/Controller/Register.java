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
import com.example.tiisetsosemaushu.mensa.Util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {



    EditText etFirstName,etLastName,etEmail,etPassword,etPasswordRe;

    /*
    * For the splash screen
    * */
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmailRegister);
        etLastName = findViewById(R.id.etLastNameRegister);
        etFirstName = findViewById(R.id.etNameRegister);
        etPassword = findViewById(R.id.etEmailPasswordRegister);
        etPasswordRe = findViewById(R.id.etEmailPasswordReenrty);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);


    }

    /*
    *
    * */
    public void Register(View view) {

        boolean isEmpty = Util.isEmpty(etEmail,etFirstName,etFirstName,etLastName,etPasswordRe);
        boolean isStrong = isStrong(etPassword.getText().toString());

        if (!isStrong)
        {
            Util.toast(Register.this,"Password is too weak");

        }
        else {
            if (isEmpty) {
                Util.toast(Register.this,"Please enter all fields");
            }

            else {
                if (!etPassword.getText().toString().equals(etPasswordRe.getText().toString())) {
                    Toast.makeText(Register.this,"The passwords do no match",Toast.LENGTH_SHORT).show();
                }
                else {
                    BackendlessUser user = new BackendlessUser();
                    user.setProperty("firstNmae",etFirstName.getText().toString());
                    user.setProperty("lastName",etLastName.getText().toString());
                    user.setEmail(etEmail.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    tvLoad.setText("Registering new user please wait");
                    showProgress(true);

                    Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            Util.toast(Register.this, "User Successfully registered");

                            Intent intent = new Intent(Register.this,Login.class);
                            startActivity(intent);
                            showProgress(false);
                            finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Util.toast(Register.this, "Error: "+fault.getMessage());
                            showProgress(false);
                        }
                    });
                }
            }
        }
    }

    /*
    * This method checks if the password is strong enough to be used in the app
    * */
    public Boolean isStrong(String password) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        Matcher matcher = pattern.matcher(password);
        boolean isStrong = false;

        boolean strong = matcher.find();

        if (strong)
        {
            isStrong = true;
        }

        return isStrong;
    }

    /**
     * Shows the progress UI and hides the login form.
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
