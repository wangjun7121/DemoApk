package com.example.wangjun.demoapk.ServiceDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.ActivityDemo.ActivityDemoActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLaunchModeActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLifeCycleActivity;
import com.example.wangjun.demoapk.R;

public class ServiceDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "ServiceDemo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicedemo);

        Button testAndroidThreadBtn;
        testAndroidThreadBtn = (Button) findViewById(R.id.testAndroidThreadBtn);
        testAndroidThreadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ServiceDemoActivity.this, CAndroidThreadActivity.class);
                startActivity(intent);
            }
        });

        Button testAsyncTaskBtn;
        testAsyncTaskBtn = (Button) findViewById(R.id.testAsyncTaskBtn);
        testAsyncTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ServiceDemoActivity.this, CAsyncTaskActivity.class);
                startActivity(intent);
            }
        });

        Button testServiceBtn;
        testServiceBtn = (Button) findViewById(R.id.testServiceBtn);
        testServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ServiceDemoActivity.this, CServiceTestActivity.class);
                startActivity(intent);
            }
        });

        Button serviceLifeCycleBtn;
        serviceLifeCycleBtn = (Button) findViewById(R.id.serviceLifeCycleBtn);
        serviceLifeCycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ServiceDemoActivity.this, CServiceLifeCycleActivity.class);
                startActivity(intent);
            }
        });

        Button alarmServiceBtn;
        alarmServiceBtn = (Button) findViewById(R.id.alarmServiceBtn);
        alarmServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ServiceDemoActivity.this, CAlarmServiceActivity.class);
                startActivity(intent);
            }
        });

    }
}
