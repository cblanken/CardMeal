package com.example.cardmeal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout.LayoutParams;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import android.util.Log;

public class MediaActivity extends MainActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private UserTimeline userTimeline;

    private TwitterTimelineRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_media, null), params);

        // TODO: construct social media view layout
        // TODO: add swipe functionality between tabs (via fragments)
        // TODO: social media API setup (Twitter, Facebook, Yelp)

        tabLayout = (TabLayout) findViewById(R.id.mediaTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.twitter)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.facebook)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.yelp)));

//        viewPager = (ViewPager) findViewById(R.id.mediaPager);
//        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TwitterConfig config = new TwitterConfig.Builder(this)
            .logger(new DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(new TwitterAuthConfig(getResources().getString(R.string.TWITTER_CONSUMER_KEY), getResources().getString(R.string.TWITTER_CONSUMER_SECRET)))
            .build();
        Twitter.initialize(config);

        recyclerView = (RecyclerView) findViewById(R.id.twitterRecyclerView);
        userTimeline = new UserTimeline.Builder()
            .screenName("CardMeal")
            .includeRetweets(true)
            .includeReplies(true)
            .build();

        TweetTimelineRecyclerViewAdapter adapter = new TweetTimelineRecyclerViewAdapter.Builder(this)
            .setTimeline(userTimeline)
            .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
            .build();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_media;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_media;
    }
}
