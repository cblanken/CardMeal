package com.example.cardmeal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;

public class SearchActivity extends MainActivity {

    private String searchString;
    private View searchView;
    private View recyclerView;
    private ArrayList<View> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_search, null), params);

        // TODO: remove, added for testing display
        addContentView(getLayoutInflater().inflate(R.layout.restaurant_card, null), params);

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_search;
    }
}
