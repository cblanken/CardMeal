package com.example.cardmeal;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class RestaurantCardData {
    protected String name;
    protected String description;
    protected String menu;
    protected String icon;
    protected String latitude;
    protected String longitude;
    protected List<String> days;
    protected List<String> hours;
    protected boolean isOpen;

    public RestaurantCardData (String name, String description, String menu, String icon,
                               String latitude, String longitude, String days, String hours,
                               String isOpen) {
        this.name = name;
        this.description = description;
        this.menu = menu;
        this.icon = icon;
        this.latitude = latitude;
        this.longitude = longitude;
        this.days = new LinkedList<>(Arrays.asList(days.split("'")));
        this.hours =  new LinkedList<>(Arrays.asList(hours.split("'")));
        for (int i = 0; i < this.days.size(); i++) {
            if (this.days.get(i).charAt(0) < 'F' || this.days.get(i).charAt(0) > 'W' ) {
                this.days.remove(i);
                this.hours.remove(i);
            }
        }

        this.isOpen = isOpen.equals("Open");
    }
}
