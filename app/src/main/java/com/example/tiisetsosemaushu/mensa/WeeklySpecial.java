package com.example.tiisetsosemaushu.mensa;

import java.io.Serializable;

public class WeeklySpecial implements Serializable
{
    String day,soup,cheap, primaClima, gourmat, grill;
    double price;
    String dayValue;

    public String getDay() {
        return day;
    }

    public String getDayValue() {
        return dayValue;
    }

    public String getSoup() {
        return soup;
    }

    public String getCheap() {
        return cheap;
    }

    public String getPrimaClima() {
        return primaClima;
    }

    public String getGourmat() {
        return gourmat;
    }

    public String getGrill() {
        return grill;
    }

    public double getPrice() {
        return price;
    }

    public WeeklySpecial(String dayValue ,String day, String soup, String cheap, String primaClima, String gourmat, String grill, double price) {
        this.day = day;
        this.soup = soup;
        this.cheap = cheap;
        this.primaClima = primaClima;
        this.gourmat = gourmat;
        this.grill = grill;
        this.price = price;

        this.dayValue = dayValue;
    }
}
