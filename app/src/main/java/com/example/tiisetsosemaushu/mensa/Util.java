package com.example.tiisetsosemaushu.mensa;

import android.content.Context;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tiisetsosemaushu.mensa.Database.WeeklySpecialDatabase;


/*
* This class is used for all the reusable code in the project
* */
public class Util
{
    /*
    * This method is used to check if Edit Texts are empty and return true if they are
    * @params varargs of EditTexts to test if they are empty.
    * @return boolean empty returns true or false depending on whether the edit texts are empty or not.
    * */
    public static boolean isEmpty(EditText... editTexts)
    {
        boolean empty = false;
        for (EditText editText: editTexts)
        {
            if(editText.getText().toString().isEmpty())
            {
                empty = true;
            }
        }

        return empty;
    }



    /*
    * This method is used for displaying the
    * @params Context used to set the application context
    * @params String message used to set the message on the toast
    * */
    public static void toast(Context context, String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }



}
