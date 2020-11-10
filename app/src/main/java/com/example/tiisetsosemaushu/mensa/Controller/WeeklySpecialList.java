package com.example.tiisetsosemaushu.mensa.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiisetsosemaushu.mensa.Database.WeeklySpecialDatabase;
import com.example.tiisetsosemaushu.mensa.R;
import com.example.tiisetsosemaushu.mensa.WeeklySpecial;

import java.io.Serializable;

public class WeeklySpecialList extends AppCompatActivity implements Serializable
{

    final WeeklySpecialDatabase weeklySpecialDatabase = new WeeklySpecialDatabase();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_special_list);

    }

    public void mondaySelected(View view)
    {
        WeeklySpecial weeklySpecial;
        weeklySpecial = weeklySpecialDatabase.weeklySpecial[0];

        Intent intent = new Intent(WeeklySpecialList.this,SelectedSpecial.class);
        intent.putExtra("weeklySpecial", (Serializable) weeklySpecial);
        startActivity(intent);
    }

    public void tusdaySelected(View view) {

        WeeklySpecial weeklySpecial;
        weeklySpecial = weeklySpecialDatabase.weeklySpecial[1];

        Intent intent = new Intent(WeeklySpecialList.this,SelectedSpecial.class);
        intent.putExtra("weeklySpecial", (Serializable) weeklySpecial);
        startActivity(intent);
    }

    public void wednesdaySelected(View view) {
        WeeklySpecial weeklySpecial;
        weeklySpecial = weeklySpecialDatabase.weeklySpecial[2];

        Intent intent = new Intent(WeeklySpecialList.this,SelectedSpecial.class);
        intent.putExtra("weeklySpecial", (Serializable) weeklySpecial);
        startActivity(intent);
    }

    public void thursdaySelected(View view) {
        WeeklySpecial weeklySpecial;
        weeklySpecial = weeklySpecialDatabase.weeklySpecial[3];

        Intent intent = new Intent(WeeklySpecialList.this,SelectedSpecial.class);
        intent.putExtra("weeklySpecial", (Serializable) weeklySpecial);
        startActivity(intent);
    }

    public void fridazSelected(View view) {

        WeeklySpecial weeklySpecial;
        weeklySpecial = weeklySpecialDatabase.weeklySpecial[4];

        Intent intent = new Intent(WeeklySpecialList.this,SelectedSpecial.class);
        intent.putExtra("weeklySpecial", (Serializable) weeklySpecial);
        startActivity(intent);
    }
}
