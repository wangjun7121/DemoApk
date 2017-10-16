package com.example.wangjun.demoapk.ServiceDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;

public class CAlarmServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicedemo_calarmservice);

        // 启动服务
        Intent intent = new Intent(this, CAlarmService_LongRunningService.class);
        startService(intent);

    }
}
