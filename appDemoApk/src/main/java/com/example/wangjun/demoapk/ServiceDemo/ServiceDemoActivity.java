package com.example.wangjun.demoapk.ServiceDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.ActivityDemo.ActivityDemoActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLaunchModeActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLifeCycleActivity;
import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.SensorDemo.SensorDemoActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class ServiceDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "ServiceDemo";
    Context m_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testAndroidThreadBtn = new Button(m_Context);
        testAndroidThreadBtn.setText("Handler: Thread 测试");
        testAndroidThreadBtn.setAllCaps(false);
        layout.addView(testAndroidThreadBtn);
        testAndroidThreadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ServiceDemoActivity.this, CAndroidThreadActivity.class);
                startActivity(intent);
            }
        });

        Button testAsyncTaskBtn = new Button(m_Context);
        testAsyncTaskBtn.setText("AsyncTask 介绍");
        testAsyncTaskBtn.setAllCaps(false);
        layout.addView(testAsyncTaskBtn);
        testAsyncTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ServiceDemoActivity.this, CAsyncTaskActivity.class);
                startActivity(intent);
            }
        });

        Button testServiceBtn = new Button(m_Context);
        testServiceBtn.setText("Service 测试");
        testServiceBtn.setAllCaps(false);
        layout.addView(testServiceBtn);
        testServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ServiceDemoActivity.this, CServiceTestActivity.class);
                startActivity(intent);
            }
        });

        Button serviceLifeCycleBtn = new Button(m_Context);
        serviceLifeCycleBtn.setText("Service 生命周期");
        serviceLifeCycleBtn.setAllCaps(false);
        layout.addView(serviceLifeCycleBtn);
        serviceLifeCycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ServiceDemoActivity.this, CServiceLifeCycleActivity.class);
                startActivity(intent);
            }
        });

        Button alarmServiceBtn = new Button(m_Context);
        alarmServiceBtn.setText("Alarm 定时 Service");
        alarmServiceBtn.setAllCaps(false);
        layout.addView(alarmServiceBtn);
        alarmServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ServiceDemoActivity.this, CAlarmServiceActivity.class);
                startActivity(intent);
            }
        });

    }
}
