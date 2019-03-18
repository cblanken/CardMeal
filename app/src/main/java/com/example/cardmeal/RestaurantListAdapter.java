package com.example.cardmeal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.LinkedList;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private LinkedList<RestaurantCardData> mRestaurantList;
    private int mExpandedPosition = -1;
    private ViewGroup recyclerView;

    public RestaurantListAdapter(Context context, LinkedList<RestaurantCardData> restaurantList) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mRestaurantList = restaurantList;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View details;
        private TextView restaurantName;
        private TextView restaurantDescription;
        private RatingBar restaurantRating;
        private TextView restaurantOpenStatus;
        private RecyclerView.Adapter mAdapter;

        public RestaurantViewHolder(View itemView, RestaurantListAdapter adapter) {
            super(itemView);
            details = itemView.findViewById(R.id.details);
            restaurantName = (TextView) itemView.findViewById(R.id.cardName);
            restaurantDescription = (TextView) itemView.findViewById(R.id.cardDescription);
            restaurantOpenStatus = (TextView) itemView.findViewById(R.id.cardOpenStatus);
            restaurantRating = (RatingBar) itemView.findViewById(R.id.cardRating);
            this.mAdapter = (RecyclerView.Adapter) adapter;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            restaurantName.setText("CLICKED!");
            // TODO: link to appropriate view (map, social media, menu)
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
        RestaurantCardData mCurrent = mRestaurantList.get(position);

        holder.restaurantName.setText(mCurrent.name);
        holder.restaurantDescription.setText(mCurrent.description);
        holder.restaurantRating.setNumStars(mCurrent.rating);
        holder.restaurantOpenStatus.setText(this.context.getResources().getString(R.string.open_closed));

        final boolean isExpanded = position == mExpandedPosition;
        holder.details.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.findViewById(R.id.expand_card_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExpandedPosition = isExpanded ? -1 : position;
                TransitionManager.beginDelayedTransition((ViewGroup)holder.itemView.getParent());
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mRestaurantList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
