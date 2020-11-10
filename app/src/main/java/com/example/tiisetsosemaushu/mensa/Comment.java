package com.example.tiisetsosemaushu.mensa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment
{
    private String comment;
    private String created1;
    private String mealType;
    private String name;
    private String LastName;
    private String email;
    private int likes;
    public static ArrayList<String> liked;



    public String getCreated1() {
        return created1;
    }

    public void setCreated1(String created1) {
        this.created1 = created1;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
    public String getMealType() {
        return mealType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
