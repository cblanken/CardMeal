package com.example.cardmeal;

public class RestaurantData {
    private static final RestaurantData ourInstance = new RestaurantData();

    public static RestaurantData getInstance() {
        return ourInstance;
    }

    private RestaurantData() {
    }
}
