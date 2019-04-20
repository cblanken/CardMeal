package com.example.cardmeal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SearchView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import java.util.LinkedList;

public class SearchActivity extends MainActivity {

    // TODO: map search string / query
    private String searchString;
    private SearchView searchView;
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

        searchView = (SearchView) findViewById(R.id.searchView);
        restaurantCards = RestaurantData.getInstance().restaurantCards;
        adapter = new RestaurantRecyclerViewAdapter(this, restaurantCards);
        recyclerView = findViewById(R.id.restaurantRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
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
