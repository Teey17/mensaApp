package com.example.tiisetsosemaushu.mensa.Controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.tiisetsosemaushu.mensa.Comment;
import com.example.tiisetsosemaushu.mensa.CommentAdapter;
import com.example.tiisetsosemaushu.mensa.R;
import com.example.tiisetsosemaushu.mensa.UserApplication;
import com.example.tiisetsosemaushu.mensa.Util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Comments extends AppCompatActivity  {

    EditText etComment;
    Spinner spinnerType;
    Comment comment = new Comment();


    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    RecyclerView rvList;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        rvList = findViewById(R.id.comments_list_view);
        rvList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        gettingComments("fetching comments.... Please wait");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.comment_menu,menu);
        return true;
    }



    public void comment(MenuItem item)
    {

        final AlertDialog.Builder commentDialog = new AlertDialog.Builder(Comments.this);
        commentDialog.setTitle("Comment");
        commentDialog.setMessage("Enter your comment");


        spinnerType = new Spinner(getApplicationContext());
        etComment = new EditText(getApplicationContext());



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.meal_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerType.setAdapter(adapter);


        LinearLayout layout = new LinearLayout(Comments.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.setPadding(1,10,1,5);

        layout.addView(spinnerType);
        layout.addView(etComment);

        commentDialog.setView(layout);





        commentDialog.setPositiveButton("Post", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();


                if (spinnerType.getSelectedItem().toString().equals("Select meal type"))
                {
                    Util.toast(Comments.this,"Please select a valid meal type");

                }
                else
                {
                    String ms = etComment.getText().toString();
                    comment.setComment(ms);
                    comment.setMealType(spinnerType.getSelectedItem().toString());
                    comment.setName(UserApplication.user.getProperty("firstNmae").toString());
                    comment.setLastName(UserApplication.user.getProperty("LastName").toString());
                    comment.setEmail(UserApplication.user.getEmail());
                    comment.setCreated1(dtf.format(now));

                    saveComment();

                }


            }
        });


        commentDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        commentDialog.show();
    }

    public void saveComment(){

        Backendless.Persistence.save(comment, new AsyncCallback<Comment>() {
            @Override
            public void handleResponse(Comment response) {
                gettingComments("Adding comment... please wait");
                Util.toast(getApplicationContext(),"Comment was successfully added");



            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Util.toast(getApplicationContext(),"Comment failed "+fault.getMessage() );

            }
        });
    }

    public void gettingComments(String message){

        showProgress(true);
        tvLoad.setText(message);
        Backendless.Persistence.of(Comment.class).find(new AsyncCallback<List<Comment>>() {
            @Override
            public void handleResponse(List<Comment> response) {

                UserApplication.comments = response;
                myAdapter = new CommentAdapter(Comments.this,UserApplication.comments);
                 rvList.setAdapter(myAdapter);
                showProgress(false);

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Util.toast(getApplicationContext(),"Error: "+fault.getMessage());
                showProgress(false);
            }
        });

    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
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
    }

}
