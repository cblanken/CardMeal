package com.example.cardmeal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout.LayoutParams;

public class MediaActivity extends MainActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: construct social media view layout
        // TODO: social media API setup (Twitter, Facebook, Yelp)

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
