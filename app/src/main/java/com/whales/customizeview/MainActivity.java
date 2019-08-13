package com.whales.customizeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.whales.widget.CircleBezierView;

public class MainActivity extends AppCompatActivity {
    private CircleBezierView mCircleBezierView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCircleBezierView = findViewById(R.id.cbv_Circle);
    }
}
