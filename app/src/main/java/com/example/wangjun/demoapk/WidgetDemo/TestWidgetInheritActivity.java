package com.example.wangjun.demoapk.WidgetDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wangjun.demoapk.R;

public class TestWidgetInheritActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widgetdemo_testwidgetinherit);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.widgetdemo_widgetinherit);
        
    }
}
