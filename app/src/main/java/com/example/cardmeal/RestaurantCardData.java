package com.example.cardmeal;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class RestaurantCardData {

    protected int rating = 0; // delete this??
    protected String name;
    protected String description;
    protected String menu;
    protected List<String> days;
    protected List<String> hours;
    protected boolean openStatus;

    public RestaurantCardData (String name, String description, String menu, String days, String hours, String openStatus) {
        this.name = name;
        this.description = description;
        this.menu = menu;
        this.days = new LinkedList<>(Arrays.asList(days.split("'")));
        this.hours =  new LinkedList<>(Arrays.asList(hours.split("'")));
        for (int i = 0; i < this.days.size(); i++) {
            if (this.days.get(i).charAt(0) < 'F' || this.days.get(i).charAt(0) > 'W' ) {
                this.days.remove(i);
                this.hours.remove(i);
            }
        }
        if (openStatus.equals("Open")) {
            this.openStatus = true;
        } else {
            this.openStatus = false;
        }
    }

}
