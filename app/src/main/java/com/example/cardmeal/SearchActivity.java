package com.example.cardmeal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout.LayoutParams;

public class SearchActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_search, null), params);

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_search;
    }

}
