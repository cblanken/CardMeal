package com.example.cardmeal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.LinkedList;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {

    private LayoutInflater mInflater;
    private  LinkedList<String> mRestaurantList;

    public RestaurantListAdapter(Context context, LinkedList<String> restaurantList) {
        mInflater = LayoutInflater.from(context);
        this.mRestaurantList = restaurantList;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView restaurantName;
        private TextView restaurantDescription;
        private RatingBar restaurantRating;
        private RecyclerView.Adapter mAdapter;

        public RestaurantViewHolder(View itemView, RestaurantListAdapter adapter) {
            super(itemView);
            restaurantName = (TextView) itemView.findViewById(R.id.cardName);
            restaurantDescription = (TextView) itemView.findViewById(R.id.cardDescription);
            restaurantRating = (RatingBar) itemView.findViewById(R.id.cardRating);
            this.mAdapter = (RecyclerView.Adapter) adapter;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            restaurantName.setText("CLICKED!");
        }
    }



    @Override
    public RestaurantListAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate an item view
        View mItemView = mInflater.inflate(R.layout.restaurant_card, parent, false);
        return new RestaurantViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        // Retrieve the data for that position
        String mCurrent = mRestaurantList.get(position);
        // TODO: Add the data to the view

    }

    @Override
    public int getItemCount() {
        return mRestaurantList.size();
    }
}
