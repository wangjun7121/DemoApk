package com.example.wangjun.demoapk.MultimediaDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wangjun.demoapk.R;

public class CNotificationIntroduceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimediademo_cnotificationintroduce);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.multimediademo_notification);
    }
}
