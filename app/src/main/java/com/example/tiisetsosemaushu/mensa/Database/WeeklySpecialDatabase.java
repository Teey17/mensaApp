package com.example.tiisetsosemaushu.mensa.Database;

import com.example.tiisetsosemaushu.mensa.WeeklySpecial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WeeklySpecialDatabase
{
    public static final WeeklySpecial[] weeklySpecial =
            {
                new WeeklySpecial("2","MONDAY","Italian vegetable soup ","Cevapcici with Ajvar (S, 24) Djuvetschreis","Farfalle-Spinat-Pfanne Kirschtomaten in Käsesahne ","Turkey Schnitzel (Halal) (G) Forest Mushroom Cream Sauce","Laksa chili soup with roast duck, pak soi, peppers and coconut",3.20
                ),
                new WeeklySpecial("3","TUESDAY","Gebrannte Grießsuppe ","Pork schnitzel","Gnocchi alla casa with peppers, zucchini, tomatoes","Chicken pumpkin seed schnitzel","Pasta from the wok",3.20
                ),
                new WeeklySpecial("4","WEDNESDAY","Cauliflower soup","Viennese fried chicken potato cucumber salad","Vegetable Burger Barbecuedip Wild Potatoes","Deer stag with wild mushrooms cranberry pear","Pork Togarashi on ramen noodles with carrots, Pepper, spicy sesame soy sauce",3.20
                ),
                new WeeklySpecial("5","THURSDAY","Potato soup","Plaice fillet baked tartare sauce","Grilled MaultaschenSnackriegel with spicy tomato curry sauce roasted onion","Veal sliced Zurich style","Meatballs and grilled sausage",3.20
                ),
                new WeeklySpecial("6","FRIDAY","Leek soup","Kottbullar (Swedish meatballs)","Spring (Garderner)","1/4 peasants (G) Orange pepper sauce","Pho Bo Hanoi Soup (beef - meat strips, Pak Soi, Peppers, ramen noodles,  Vietnamese beef broth",3.20
                )

            };
    public  final WeeklySpecial[] weeklySpecial1 = weeklySpecial;

    private final static Map<String, WeeklySpecial> SPECIAL_MAP = new HashMap<>();

    private final static String[] SPECIALS_LIST;

    static
    {
        for (WeeklySpecial r : weeklySpecial) {
            SPECIAL_MAP.put(r.getDayValue(), r);
        }
        SPECIALS_LIST = new String[SPECIAL_MAP.size()];

        SPECIAL_MAP.keySet().toArray(SPECIALS_LIST);

        Arrays.sort(SPECIALS_LIST);

    }

    public String[] getSpecials()
    {
        return SPECIALS_LIST;
    }
    public final int getSize()
    {
       return weeklySpecial.length;
    }
}
