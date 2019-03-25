package com.example.cardmeal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout.LayoutParams;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import android.util.Log;

public class MediaActivity extends MainActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: construct social media view layout
        // TODO: add swipe functionality between tabs (via fragments)
        // TODO: social media API setup (Twitter, Facebook, Yelp)

        //Move to OnCreate
        Twitter.initialize(this);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("TWITTER_CONSUMER_KEY", "TWITTER_CONSUMER_SECRET"))
                .debug(true)
                .build();
        Twitter.initialize(config);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_media, null), params);

        tabLayout = (TabLayout) findViewById(R.id.mediaTabLayout);
        viewPager = (ViewPager) findViewById(R.id.mediaPager);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.facebook)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.twitter)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.yelp)));
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
