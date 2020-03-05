package com.example.wangjun.demoapk.SoftwareDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.HardwareDemo.HardwareDemoActivity;
import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class SoftwareDemoActivity extends AppCompatActivity {
    Context m_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testDevicePolicyManagerBtn = new Button(m_Context);
        testDevicePolicyManagerBtn.setText("DevicePolicyManager:锁屏测试");
        testDevicePolicyManagerBtn.setAllCaps(false);
        layout.addView(testDevicePolicyManagerBtn);
        testDevicePolicyManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(SoftwareDemoActivity.this, CDevicePolicyManagerActivity.class);
                startActivity(intent);
            }
        });

        Button testSettingMonitorBtn = new Button(m_Context);
        testSettingMonitorBtn.setText("Setting: 监听飞行模式");
        testSettingMonitorBtn.setAllCaps(false);
        layout.addView(testSettingMonitorBtn);
        testSettingMonitorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(SoftwareDemoActivity.this, CSettingMonitorActivity.class);
                startActivity(intent);
            }
        });

        Button testShellExecBtn = new Button(m_Context);
        testShellExecBtn.setText("ShellExec: 安卓执行 Shell");
        testShellExecBtn.setAllCaps(false);
        layout.addView(testShellExecBtn);
        testShellExecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(SoftwareDemoActivity.this, CShellExecActivity.class);
                startActivity(intent);
            }
        });

    }
}
