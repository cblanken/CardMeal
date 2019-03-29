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

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private LinkedList<RestaurantCardData> mRestaurantList;
    private int mExpandedPosition = -1;
    private ViewGroup recyclerView;

    public RestaurantRecyclerViewAdapter(Context context, LinkedList<RestaurantCardData> restaurantList) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mRestaurantList = restaurantList;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        private View cardDetails;
        private View cardBody;
        private TextView restaurantName;
        private TextView restaurantDescription;
        private RatingBar restaurantRating;
        private TextView restaurantOpenStatus;
        private TextView restaurantHours;
        private TextView restaurantDays;
        private RecyclerView.Adapter mAdapter;

        public RestaurantViewHolder(View itemView, RestaurantRecyclerViewAdapter adapter) {
            super(itemView);
            cardBody = (View) itemView.findViewById(R.id.cardView);
            restaurantName = (TextView) itemView.findViewById(R.id.cardName);
            cardDetails = itemView.findViewById(R.id.details);
            restaurantDescription = (TextView) itemView.findViewById(R.id.cardDescription);
            restaurantOpenStatus = (TextView) itemView.findViewById(R.id.cardOpenStatus);
            restaurantRating = (RatingBar) itemView.findViewById(R.id.cardRating);
            restaurantHours = (TextView) itemView.findViewById(R.id.cardHours);
            restaurantDays = (TextView) itemView.findViewById(R.id.cardDays);
            mAdapter = (RecyclerView.Adapter) adapter;
        }
    }

    @Override
    public RestaurantRecyclerViewAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        if (mCurrent.isOpen) {
            holder.restaurantOpenStatus.setTextColor(context.getResources().getColor(R.color.customPositiveGreen));
        } else {
            holder.restaurantOpenStatus.setTextColor(context.getResources().getColor(R.color.customNegativeRed));
        }
        holder.restaurantOpenStatus.setText(mCurrent.isOpen ? "OPEN" : "CLOSED");
        String days = "";
        String hours = "";
        for (String d : mCurrent.days) {
            days = days.concat(d + "\n");
        }
        for (String h : mCurrent.hours) {
            hours = hours.concat(h + "\n");
        }
        holder.restaurantDays.setText(days);
        holder.restaurantHours.setText(hours);

        // Restaurant card expand functionality
        final boolean isExpanded = position == mExpandedPosition;
        holder.cardDetails.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
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
