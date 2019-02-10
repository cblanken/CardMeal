package com.example.cardmeal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import java.util.LinkedList;

public class SearchActivity extends MainActivity {

    private String searchString;
    private View searchView;
    private LinkedList<String> restaurantNames;
    private RecyclerView recyclerView;
    private RestaurantListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_search, null), params);

        searchView = findViewById(R.id.searchView);

        // TODO: dynamically map data to cards,

        restaurantNames = new LinkedList<String>();
        restaurantNames.add("[1] Einstein Bros. Bagels");
        restaurantNames.add("[2] Starbucks");
        restaurantNames.add("[3] Twisted Taco");
        restaurantNames.add("[4] The Ville Grill");
        restaurantNames.add("[5] Panda Express");
        restaurantNames.add("[6] Subway");
        restaurantNames.add("[7] Papa John's");



        adapter = new RestaurantListAdapter(this, restaurantNames);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
