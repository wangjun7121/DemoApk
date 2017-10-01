package com.example.wangjun.demoapk.LayoutDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wangjun.demoapk.R;

public class CFragmentLifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutdemo_cfragmentlifecycle);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.activitydemo_activitylaunchmode);
    }
}
