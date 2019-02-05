package com.example.cardmeal;

public class MoreActivity extends MainActivity {

    @Override
    int getContentViewId() {
        return R.layout.activity_more;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_more;
    }
}
