package com.example.wangjun.demoapk.ActivityDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wangjun.demoapk.R;

public class CActivityLaunchModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitydemo_cactivitylaunchmode);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.activitydemo_activitylaunchmode);
    }
}
