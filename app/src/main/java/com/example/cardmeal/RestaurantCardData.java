package com.example.cardmeal;

public class RestaurantCardData {

    protected String name;
    protected String description;
    protected int rating;
    protected boolean openStatus;

    public RestaurantCardData (String name, String description, int rating, boolean openStatus) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.openStatus = openStatus;
    }

}
