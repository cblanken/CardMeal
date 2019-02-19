package com.example.cardmeal;

import android.os.Bundle;
import android.widget.LinearLayout.LayoutParams;

public class MoreActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: construct settings/other view layout
        // TODO: add recyclerView
            // TODO: add icons and layouts/screens needed for all "more" options
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addContentView(getLayoutInflater().inflate(R.layout.activity_more, null), params);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_more;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_more;
    }
}
