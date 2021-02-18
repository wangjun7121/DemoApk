package com.example.wangjun.demoapk.BroadcastsDemo;

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

public class BroadcastsDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "ActivityDemoActivity";
    Context m_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button tempBroadcastsIntroduceBtn = new Button(m_Context);
        tempBroadcastsIntroduceBtn.setText("有序/无序 广播介绍");
        tempBroadcastsIntroduceBtn.setAllCaps(false);
        layout.addView(tempBroadcastsIntroduceBtn);
        tempBroadcastsIntroduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(BroadcastsDemoActivity.this, CBroadcastsIntroduceActivity.class);
                startActivity(intent);
            }
        });

        Button testRegisterReceiverBtn = new Button(m_Context);
        testRegisterReceiverBtn.setText("动态/动态 接收广播");
        testRegisterReceiverBtn.setAllCaps(false);
        layout.addView(testRegisterReceiverBtn);
        testRegisterReceiverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(BroadcastsDemoActivity.this, CRegisterReceiverActivity.class);
                startActivity(intent);
            }
        });

        Button testSendBroadcastBtn = new Button(m_Context);
        testSendBroadcastBtn.setText("发送/接收 标准/有序 广播");
        testSendBroadcastBtn.setAllCaps(false);
        layout.addView(testSendBroadcastBtn);
        testSendBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(BroadcastsDemoActivity.this, CSendBroadcastActivity.class);
                startActivity(intent);
            }
        });

        Button testLocalBroadcastBtn = new Button(m_Context);
        testLocalBroadcastBtn.setText("本地广播");
        testLocalBroadcastBtn.setAllCaps(false);
        layout.addView(testLocalBroadcastBtn);
        testLocalBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(BroadcastsDemoActivity.this, CLocalBroadcastManagerActivity.class);
                startActivity(intent);
            }
        });

        Button testLoginForceOfflineBtn = new Button(m_Context);
        testLoginForceOfflineBtn.setText("登录->广播 强制下线");
        testLoginForceOfflineBtn.setAllCaps(false);
        layout.addView(testLoginForceOfflineBtn);
        testLoginForceOfflineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(BroadcastsDemoActivity.this, CLoginForceOfflineLoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
