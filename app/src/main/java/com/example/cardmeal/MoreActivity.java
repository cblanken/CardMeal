package com.example.cardmeal;

import android.os.Bundle;
import android.widget.LinearLayout.LayoutParams;

public class MoreActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: construct settings/other view layout
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
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
