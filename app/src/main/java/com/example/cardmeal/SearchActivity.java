package com.example.cardmeal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import java.util.LinkedList;

public class SearchActivity extends MainActivity {

    // TODO: map search string / query
    private String searchString;
    private View searchView;
    private LinkedList<RestaurantCardData> restaurantCards;
    private RecyclerView recyclerView;
    private RestaurantRecyclerViewAdapter adapter;
    private DatabaseReference database;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_search, null), params);

        searchView = findViewById(R.id.searchView);
        restaurantCards = RestaurantData.getInstance().restaurantCards;
        adapter = new RestaurantRecyclerViewAdapter(this, restaurantCards);
        recyclerView = findViewById(R.id.restaurantRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        RestaurantData.getInstance().setRestaurantCards(adapter);
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
