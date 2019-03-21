package com.example.cardmeal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import java.util.LinkedList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SearchActivity extends MainActivity {

    // TODO: map search string / query
    private String searchString;
    private View searchView;
    private LinkedList<RestaurantCardData> restaurantCards;
    private RecyclerView recyclerView;
    private RestaurantListAdapter adapter;

    private DatabaseReference database;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_search, null), params);

        searchView = findViewById(R.id.searchView);

        restaurantCards = new LinkedList<RestaurantCardData>();

        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword("arieltrnr@gmail.com", "Z$yadlP055")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getRestaurantData();
                        } else {
                            Log.e("firebase", "authentication failed"); // do something here??
                        }
                    }
                });

        adapter = new RestaurantListAdapter(this, restaurantCards);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO: optimize recyclerView (laggy scrolling), implement Glide? [https://github.com/bumptech/glide]
    }

    public void getRestaurantData()
    {
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    restaurantCards.add(new RestaurantCardData(ds.child("Name").getValue(String.class),
                            ds.child("Description").getValue(String.class),
                            ds.child("Menu").getValue(String.class),
                            ds.child("Days").getValue(String.class),
                            ds.child("Hours").getValue(String.class),
                            ds.child("Status").getValue(String.class)));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
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
