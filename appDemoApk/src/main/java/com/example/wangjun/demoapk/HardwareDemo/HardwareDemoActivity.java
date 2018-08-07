package com.example.wangjun.demoapk.HardwareDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.wangjun.demoapk.ActivityDemo.ActivityDemoActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLaunchModeActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLifeCycleActivity;
import com.example.wangjun.demoapk.MainActivity;
import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAccelActivity;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.SensorDemo.CCompassActivity;
import com.example.wangjun.demoapk.SensorDemo.SensorDemoActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class HardwareDemoActivity extends AppCompatActivity {
    Context m_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final  LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testAlsBtn = new Button(m_Context);
        testAlsBtn.setText("环境光传感器");
        testAlsBtn.setAllCaps(false);
        layout.addView(testAlsBtn);
        testAlsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CAlsActivity.class);
                startActivity(intent);
            }
        });

        Button testAccelBtn = new Button(m_Context);
        testAccelBtn.setText("加速度传感器");
        testAccelBtn.setAllCaps(false);
        layout.addView(testAccelBtn);
        testAccelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CAccelActivity.class);
                startActivity(intent);
            }
        });

        Button testProxBtn = new Button(m_Context);
        testProxBtn.setText("距离传感器");
        testProxBtn.setAllCaps(false);
        layout.addView(testProxBtn);
        testProxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CProximityTestActivity.class);
                startActivity(intent);
            }
        });

        Button testCompassBtn = new Button(m_Context);
        testCompassBtn.setText("方向传感器");
        testCompassBtn.setAllCaps(false);
        layout.addView(testCompassBtn);
        testCompassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CCompassActivity.class);
                startActivity(intent);
            }
        });

        Button testLEDBtn = new Button(m_Context);
        testLEDBtn.setText("三色灯/振动器测试(需灭屏)");
        testLEDBtn.setAllCaps(false);
        layout.addView(testLEDBtn);
        testLEDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CLEDTestActivity.class);
                startActivity(intent);
            }
        });


        Button testKeyBtn = new Button(m_Context);
        testKeyBtn.setText("按键测试");
        testKeyBtn.setAllCaps(false);
        layout.addView(testKeyBtn);
        testKeyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CKeyTestActivity.class);
                startActivity(intent);
            }
        });


        Button testBTBtn = new Button(m_Context);
        testBTBtn.setText("蓝牙硬件测试");
        testBTBtn.setAllCaps(false);
        layout.addView(testBTBtn);
        testBTBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CBTTestActivity.class);
                startActivity(intent);
            }
        });

        Button testBTCSBtn = new Button(m_Context);
        testBTCSBtn.setText("蓝牙服务测试");
        testBTCSBtn.setAllCaps(false);
        layout.addView(testBTCSBtn);
        testBTCSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CBTCSActivity.class);
                startActivity(intent);
            }
        });

        Button testWifiBtn = new Button(m_Context);
        testWifiBtn.setText("Wifi 硬件测试");
        testWifiBtn.setAllCaps(false);
        layout.addView(testWifiBtn);
        testWifiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CWifiDemoActivity.class);
                startActivity(intent);
            }
        });

        Button testBatteryInfoBtn = new Button(m_Context);
        testBatteryInfoBtn.setText("电池信息");
        testBatteryInfoBtn.setAllCaps(false);
        layout.addView(testBatteryInfoBtn);
        testBatteryInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CBatteryInfoActivity.class);
                startActivity(intent);
            }
        });

        Button testBacklightBtn = new Button(m_Context);
        testBacklightBtn.setText("背光亮度测试");
        testBacklightBtn.setAllCaps(false);
        layout.addView(testBacklightBtn);
        testBacklightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CBackLightTestActivity.class);
                startActivity(intent);
            }
        });

        Button testMemoryBtn = new Button(m_Context);
        testMemoryBtn.setText("内存信息");
        testMemoryBtn.setAllCaps(false);
        layout.addView(testMemoryBtn);
        testMemoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CMemoryTestActivity.class);
                startActivity(intent);
            }
        });



        Button testLCDBtn = new Button(m_Context);
        testLCDBtn.setText("LCD 测试");
        testLCDBtn.setAllCaps(false);
        layout.addView(testLCDBtn);
        testLCDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CLCDTestActivity.class);
                startActivity(intent);
            }
        });


    }
}
