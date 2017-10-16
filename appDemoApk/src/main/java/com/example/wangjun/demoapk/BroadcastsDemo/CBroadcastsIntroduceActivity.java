package com.example.wangjun.demoapk.BroadcastsDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wangjun.demoapk.R;

public class CBroadcastsIntroduceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcastsdemo_cbroadcastsintroduce);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.broadcastsdemo_broadcastsintroduce);
    }
}
