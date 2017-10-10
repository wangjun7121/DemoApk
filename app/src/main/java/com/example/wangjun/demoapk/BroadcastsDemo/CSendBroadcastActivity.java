package com.example.wangjun.demoapk.BroadcastsDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wangjun.demoapk.R;

public class CSendBroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcastsdemo_csendbroadcast);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.wangjun.demoapk.BroadcastsDemo.CSendBroadcastActivity.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });

    }

    // 静态接收的广播不能使用内部类
//    public class MyBroadcastReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "received in MyBroadcastReceive", Toast.LENGTH_SHORT).show();
//        }
//
//    }


}
