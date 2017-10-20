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

public class HardwareDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "ActivityDemoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hardwaredemo);

        Button testAlsBtn;
        testAlsBtn = (Button) findViewById(R.id.testAls_btn);
        testAlsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(HardwareDemoActivity.this, CAlsActivity.class);
                startActivity(intent);
            }
        });

        Button testAccelBtn;
        testAccelBtn = (Button) findViewById(R.id.testAccel_btn);
        testAccelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testAccelBtn");
                Intent intent = new Intent(HardwareDemoActivity.this, CAccelActivity.class);
                startActivity(intent);
            }
        });


        Button testCompassBtn;
        testCompassBtn = (Button) findViewById(R.id.testCompassBtn);
        testCompassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testAccelBtn");
                Intent intent = new Intent(HardwareDemoActivity.this, CCompassActivity.class);
                startActivity(intent);
            }
        });

        Button testLEDBtn;
        testLEDBtn = (Button) findViewById(R.id.testLEDBtn);
        testLEDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testAccelBtn");
                Intent intent = new Intent(HardwareDemoActivity.this, CLEDTestActivity.class);
                startActivity(intent);
            }
        });
    }
}
