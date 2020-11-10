package com.example.tiisetsosemaushu.mensa.Database;

import com.example.tiisetsosemaushu.mensa.Desserts;
import com.example.tiisetsosemaushu.mensa.WeeklySpecial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DessertsDatabase {
    public static final Desserts[] desserts =

            {
              new Desserts("Cake","Home made delicious chocolate cake",2.25),
              new Desserts("cookie","Fudge stuffed cookie",1.99),
              new Desserts("doughnut","Golden glazed with chocolate icing",89),
              new Desserts("muffin","Choclate chip muffin with cherry on top",1.50)
            } ;


    private final static Map<String, Desserts> DESSERT_MAP = new HashMap<>();
    public  final Desserts[] desserts1 = desserts;

    private final static String[] DESSERT_LIST;

    static
    {
        for (Desserts r : desserts)
        {
            DESSERT_MAP.put(r.getType(),r);
        }
        DESSERT_LIST = new String[DESSERT_MAP.size()];

        DESSERT_MAP.keySet().toArray(DESSERT_LIST);

        Arrays.sort(DESSERT_LIST);

    }

    public String[] getDesserts()
    {
        return DESSERT_LIST;
    }
    public final int getSize()
    {
        return desserts.length;
    }
}
