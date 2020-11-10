package com.example.tiisetsosemaushu.mensa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiisetsosemaushu.mensa.Database.DessertsDatabase;


public class DessertAdapter extends BaseAdapter
{
    DessertsDatabase dessertDb;

    public DessertAdapter(DessertsDatabase db)
    {
        dessertDb = db;
    }
    @Override
    public int getCount() {
        return dessertDb.getSize();
    }

    @Override
    public Object getItem(int position)
    {
        return dessertDb.getDesserts()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();
        String type = dessertDb.getDesserts()[position];
        Desserts desserts = dessertDb.desserts1[position];

        String price = String.valueOf(desserts.getPrice());


        int imageID = context.getResources().getIdentifier(type.toLowerCase(), "drawable", context.getPackageName());

        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dessert_list_view_item,null,false);

            ImageView ivType = convertView.findViewById(R.id.ivType);
            TextView tvDescription = convertView.findViewById(R.id.tvDescription);
            TextView tvPrice = convertView.findViewById(R.id.tvPrice);


            ivType.setImageResource(imageID);
            tvDescription.setText(type);
            tvPrice.setText(price);

        }
        return convertView;
    }
}
