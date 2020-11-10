package com.example.tiisetsosemaushu.mensa.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tiisetsosemaushu.mensa.R;
import com.example.tiisetsosemaushu.mensa.WeeklySpecial;

import java.io.Serializable;

public class SelectedSpecial extends AppCompatActivity implements Serializable
{


    TextView tvSoup,tvCheap,tvGrill,tvGourmat,tvPrimaClima,tvDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_special);

        tvCheap = findViewById(R.id.tvCheap);
        tvSoup = findViewById(R.id.tvSoup);
        tvGourmat = findViewById(R.id.tvGourmat);
        tvPrimaClima = findViewById(R.id.tvPrimaClima);
        tvGrill = findViewById(R.id.tvGrill);
        tvDay = findViewById(R.id.tvDay);

        WeeklySpecial weeklySpecial = (WeeklySpecial)getIntent().getSerializableExtra("weeklySpecial");

        tvCheap.setText("Save of the day :\n" +weeklySpecial.getCheap());
        tvGrill.setText("On Grill :\n"+weeklySpecial.getGrill());
        tvPrimaClima.setText("Pima Clima :\n"+weeklySpecial.getPrimaClima());
        tvGourmat.setText("The gourmat :\n"+weeklySpecial.getGourmat());
        tvSoup.setText("Soup of the day :\n"+weeklySpecial.getSoup());

        tvDay.setText(weeklySpecial.getDay());



    }
}
