package com.example.cardmeal;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.LinkedList;
import javax.inject.Inject;

public class RestaurantData {
    @Inject Context context;
    private static final RestaurantData restaurantData = new RestaurantData();
    private DatabaseReference database;
    private FirebaseAuth auth;

    public LinkedList<RestaurantCardData> restaurantCards;

    public static RestaurantData getInstance() {
        return restaurantData;
    }

    private RestaurantData() {
        restaurantCards = new LinkedList<RestaurantCardData>();
        FirebaseApp.initializeApp(context);
        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword("cardmeal@gmail.com", "Z$yadlP055")
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete( Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // getRestaurantData();
                    } else {
                        // TODO: handle firebase auth error?
                        Log.e("firebase", "authentication failed");
                    }
                }
            });
    }

    public void setRestaurantCards(RestaurantRecyclerViewAdapter adapter) {
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!RestaurantData.getInstance().restaurantCards.isEmpty()) {
                    RestaurantData.getInstance().restaurantCards.removeAll(restaurantCards);
                }
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    RestaurantData.getInstance().restaurantCards.add(new RestaurantCardData(
                            ds.child("Name").getValue(String.class),
                            ds.child("Description").getValue(String.class),
                            ds.child("Menu").getValue(String.class),
                            ds.child("Icon").getValue(String.class),
                            ds.child("Latitude").getValue(String.class).trim(),
                            ds.child("Longitude").getValue(String.class).trim(),
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
}
