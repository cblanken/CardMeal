package com.example.cardmeal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import android.net.Uri;
import android.webkit.WebView;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.database.core.Constants;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private LinkedList<RestaurantCardData> mRestaurantList;
    private ArrayList<RestaurantCardData> mRestaurantListCopy;
    private int mExpandedPosition = -1;
    private ViewGroup recyclerView;

    public RestaurantRecyclerViewAdapter(Context context, LinkedList<RestaurantCardData> restaurantList) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mRestaurantList = restaurantList;
        mRestaurantListCopy = new ArrayList<>();
        mRestaurantListCopy.addAll(this.mRestaurantList);
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        private View card;
        private View cardDetails;
        private View cardBody;
        private ImageView mapButton;
        private ImageView menuButton;
        private ImageView restaurantIcon;
        private TextView restaurantName;
        private TextView restaurantDescription;
        private TextView restaurantOpenStatus;
        private TextView restaurantHours;
        private TextView restaurantDays;
        private RecyclerView.Adapter mAdapter;

        public RestaurantViewHolder(View itemView, RestaurantRecyclerViewAdapter adapter) {
            super(itemView);
            card = itemView.findViewById(R.id.cardView);
            cardDetails = itemView.findViewById(R.id.details);
            cardBody = (View) itemView.findViewById(R.id.cardView);
            mapButton = (ImageView) itemView.findViewById(R.id.mapButton);
            menuButton = (ImageView) itemView.findViewById(R.id.menuButton);
            restaurantName = (TextView) itemView.findViewById(R.id.cardName);
            restaurantDescription = (TextView) itemView.findViewById(R.id.cardDescription);
            restaurantOpenStatus = (TextView) itemView.findViewById(R.id.cardOpenStatus);
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
        RestaurantCardData mCurrent = mRestaurantList.get(position);
        View.OnClickListener menuOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {

            }
        };

        // Card text
        holder.restaurantName.setText(mCurrent.name);
        holder.restaurantDescription.setText(mCurrent.description);

        // Open/Closed status
        if (mCurrent.isOpen) {
            holder.restaurantOpenStatus.setTextColor(context.getResources().getColor(R.color.customPositiveGreen));
        } else {
            holder.restaurantOpenStatus.setTextColor(context.getResources().getColor(R.color.customNegativeRed));
        }
        holder.restaurantOpenStatus.setText(mCurrent.isOpen ? "OPEN" : "CLOSED");

        // Card background
        Glide.with(context)
                .load(mCurrent.icon)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.card.setBackground(resource);
                    }
                });

        // Menu button
        holder.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: implement WebView for menus?
                // WebView webView = (WebView) ((Activity) context).findViewById(R.id.menuWebView);
                // webView.getSettings().setJavaScriptEnabled(true);
                // webView.loadUrl(mCurrent.menu);
                // webView.setVisibility(View.VISIBLE);

                String mimeType = "application/pdf";
                Uri menuPdf = Uri.parse(mCurrent.menu);
                Intent intent = new Intent(Intent.ACTION_VIEW).setDataAndType(menuPdf, mimeType);

                Intent chooser = Intent.createChooser(intent, context.getResources().getString(R.string.chooser_title));
                chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(chooser);
            }
        });

        // Map button
        holder.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double latitude = 38.217919;
                double longitude = -85.756097;
                try {
                    latitude = Double.parseDouble(mCurrent.latitude);
                    longitude = Double.parseDouble(mCurrent.longitude);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(context, MapsActivity.class);
                intent.setPackage("com.google.android.apps.maps");
                intent.putExtra("lat", latitude);
                intent.putExtra("long", longitude);
                intent.putExtra("index", position);
                intent.putExtra("name", mCurrent.name);
                Bundle locations = new Bundle();

                intent.putExtras(locations);

                context.startActivity(intent);
            }
        });

        // Service hours
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

    public void filter(String text) {
        if(text.isEmpty()) {
            mRestaurantList.clear();
            mRestaurantList.addAll((RestaurantData.getInstance().getRestaurantCardsCopy()));
        } else {
            ArrayList<RestaurantCardData> result = new ArrayList<>();
            text = text.toLowerCase();
            for(RestaurantCardData item : RestaurantData.getInstance().getRestaurantCardsCopy()) {
                if(item.name.toLowerCase().contains(text)) {
                    result.add(item);
                }
            }
            mRestaurantList.clear();
            mRestaurantList.addAll(result);
        }
        notifyDataSetChanged();
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
