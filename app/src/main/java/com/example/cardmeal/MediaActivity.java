package com.example.cardmeal;

import android.os.Bundle;
import android.widget.LinearLayout.LayoutParams;

public class MediaActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_media, null), params);
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
