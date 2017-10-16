package com.example.wangjun.demoapk.BroadcastsDemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.wangjun.demoapk.R;

public class CLoginForecOfflineMainActivity extends CLoginForceOffline_BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcastsdemo_cloginforecofflinemain);

        Button forceOffline = (Button) findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 发送广播，跳转到 CLoginForceOfflineReceiver 中进行处理
                Intent intent = new Intent("com.example.wangjun.demoapk.BroadcastsDemo.CLoginForecOfflineMainActivity.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}
