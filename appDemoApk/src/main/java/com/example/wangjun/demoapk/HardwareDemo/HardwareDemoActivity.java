package com.example.wangjun.demoapk.HardwareDemo;

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
import com.example.wangjun.demoapk.SensorDemo.CAccelActivity;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.SensorDemo.CCompassActivity;
import com.example.wangjun.demoapk.SensorDemo.SensorDemoActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class HardwareDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hardwaredemo);

        Button testAlsBtn;
        testAlsBtn = (Button) findViewById(R.id.testAls_btn);
        testAlsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CAlsActivity.class);
                startActivity(intent);
            }
        });

        Button testAccelBtn;
        testAccelBtn = (Button) findViewById(R.id.testAccel_btn);
        testAccelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CAccelActivity.class);
                startActivity(intent);
            }
        });


        Button testCompassBtn;
        testCompassBtn = (Button) findViewById(R.id.testCompassBtn);
        testCompassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CCompassActivity.class);
                startActivity(intent);
            }
        });

        Button testLEDBtn;
        testLEDBtn = (Button) findViewById(R.id.testLEDBtn);
        testLEDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CLEDTestActivity.class);
                startActivity(intent);
            }
        });

        Button testKeytn;
        testLEDBtn = (Button) findViewById(R.id.testKeytn);
        testLEDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CKeyTestActivity.class);
                startActivity(intent);
            }
        });

        Button testBTtn;
        testBTtn = (Button) findViewById(R.id.testBTtn);
        testBTtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CBTTestActivity.class);
                startActivity(intent);
            }
        });

        Button testBTCStn;
        testBTCStn = (Button) findViewById(R.id.testBTCStn);
        testBTCStn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CBTCSActivity.class);
                startActivity(intent);
            }
        });

        Button testWifitn;
        testWifitn = (Button) findViewById(R.id.testWifitn);
        testWifitn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(HardwareDemoActivity.this, CWifiDemoActivity.class);
                startActivity(intent);
            }
        });

    }
}
