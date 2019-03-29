package com.example.cardmeal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.twitter.sdk.android.tweetui.UserTimeline;


class TwitterTimelineRecyclerViewAdapter extends RecyclerView.Adapter<TwitterTimelineRecyclerViewAdapter.TwitterViewHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private UserTimeline timeline;
    private int mExpandedPosition = -1;
    private ViewGroup recyclerView;

    public TwitterTimelineRecyclerViewAdapter(Context context, UserTimeline timeline) {
        this.timeline = timeline;
    }

    public class TwitterViewHolder extends RecyclerView.ViewHolder{

        public TwitterViewHolder(View itemView, TwitterTimelineRecyclerViewAdapter adapter) {
            super(itemView);

        }
    }

    @Override
    public TwitterTimelineRecyclerViewAdapter.TwitterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view
        View mItemView = mInflater.inflate(R.layout.timeline, parent, false);
        return new TwitterViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(TwitterViewHolder holder, int position) {
        // Retrieve the data for that position
//        RestaurantCardData mCurrent = mTweetList.;

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
