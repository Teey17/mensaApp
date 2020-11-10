package com.example.tiisetsosemaushu.mensa.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tiisetsosemaushu.mensa.Database.DessertsDatabase;
import com.example.tiisetsosemaushu.mensa.DessertAdapter;
import com.example.tiisetsosemaushu.mensa.R;

public class DessertsSelected extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desserts_selected);

        final DessertsDatabase dessertsDatabase= new DessertsDatabase();

        ListView listView = findViewById(R.id.my_dessert_list_view);
        final DessertAdapter adapter = new DessertAdapter(dessertsDatabase);


        listView.setAdapter(adapter);
    }
}
