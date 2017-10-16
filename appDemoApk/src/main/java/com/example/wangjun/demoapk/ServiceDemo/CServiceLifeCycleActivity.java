package com.example.wangjun.demoapk.ServiceDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wangjun.demoapk.R;

public class CServiceLifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicedemo_cservicelifecycle);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.servicedemo_servicelifecycle);

    }
}
