package com.example.tiisetsosemaushu.mensa;

public class Desserts
{
    String type, description;
    double price;

    public String getType() {
        return type;
    }


    public double getPrice() {
        return price;
    }

    public Desserts(String type, String description, double price) {
        this.type = type;
        this.description = description;
        this.price = price;
    }
}
