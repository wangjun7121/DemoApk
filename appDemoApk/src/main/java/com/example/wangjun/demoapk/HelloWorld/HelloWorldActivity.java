package com.example.wangjun.demoapk.HelloWorld;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;

public class HelloWorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. 隐藏状态栏无效：原因为我继承的是 AppCompatActivity 将其改为 Activity 即可。
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 2. 或用此句隐藏状态栏也行，继承自 AppCompatActivity
        //if (getSupportActionBar() != null){
        //    getSupportActionBar().hide();
        //}
        setContentView(R.layout.helloworld);





    }
}
