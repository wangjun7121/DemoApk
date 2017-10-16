package com.example.wangjun.demoapk.LayoutDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wangjun.demoapk.R;

public class CMultiLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutdemo_cmultilayout);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.layoutdemo_cmuliti);
    }
}
