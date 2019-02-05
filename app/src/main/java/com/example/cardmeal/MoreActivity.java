package com.example.cardmeal;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

public class OtherActivity extends MainActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout constraintLayout = findViewById(R.id.container);
        Log.d("constraintLayout", constraintLayout.toString());

        getLayoutInflater().inflate(R.layout.activity_main, constraintLayout);
        setContentView(R.layout.activity_settings);
    }
}
