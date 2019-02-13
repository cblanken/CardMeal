package com.example.cardmeal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import java.util.LinkedList;

public class SearchActivity extends MainActivity {

    // TODO: map search string / query
    private String searchString;
    private View searchView;
    private LinkedList<RestaurantCardData> restaurantCards;
    private RecyclerView recyclerView;
    private RestaurantListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_search, null), params);

        searchView = findViewById(R.id.searchView);

        // TODO: dynamically retrieve and load data to cards,
        restaurantCards = new LinkedList<RestaurantCardData>();
        restaurantCards.add(new RestaurantCardData("[1] Einstein Bros. Bagels",
                "Bagels",
                5,
                true));
        restaurantCards.add(new RestaurantCardData("[2] Starbucks",
                "Coffee",
                3,
                true));
        restaurantCards.add( new RestaurantCardData("[3] Twisted Taco",
                "Tacos",
                4,
                true));
        restaurantCards.add(new RestaurantCardData("[4] Ville Grille",
                getString(R.string.Lorem20),
                2,
                true));
        restaurantCards.add(new RestaurantCardData("[5] Panda Express",
                "Pandas?",
                4,
                true));
        restaurantCards.add( new RestaurantCardData("[6] Subway",
                "Subs",
                4,
                true));
        restaurantCards.add(new RestaurantCardData("[7] Papa John's",
                getString (R.string.Lorem25),
                1,
                true));
        restaurantCards.add(new RestaurantCardData("[7] Papa John's",
                getString (R.string.Lorem25),
                1,
                true));
        restaurantCards.add(new RestaurantCardData("[7] Papa John's",
                getString (R.string.Lorem25),
                1,
                true));
        restaurantCards.add(new RestaurantCardData("[7] Papa John's",
                getString (R.string.Lorem25),
                1,
                true));

        adapter = new RestaurantListAdapter(this, restaurantCards);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO: optimize recyclerView (laggy scrolling), implement Glide? [https://github.com/bumptech/glide]
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
