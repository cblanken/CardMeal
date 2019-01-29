package com.example.cardmeal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                case R.id.navigation_search:
                    mTextMessage.setText(getString(R.string.title_search));
                    return true;
                case R.id.navigation_map:
                    mTextMessage.setText(getString(R.string.title_map));
                    return true;
                case R.id.navigation_media:
                    mTextMessage.setText(getString(R.string.title_media));
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void gotoSearch(View view) {
        Log.d("nav-action", "GOTO SEARCH");
    }

    public void gotoMap(View view) {
        Log.d("nav-action", "GOTO MAP");
    }

    public void gotoMedia(View view) {
        Log.d("nav-action", "GOTO MEDIA");
    }

    public void gotoSettings(View view) {
        Log.d("nav-action", "GOTO SETTINGS");
    }

}
