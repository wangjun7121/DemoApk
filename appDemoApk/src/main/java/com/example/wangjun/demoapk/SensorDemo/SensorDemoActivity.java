package com.example.wangjun.demoapk.SensorDemo;

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
import com.example.wangjun.demoapk.Tools.LogUtil;

public class SensorDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "SensorDemoActivity";
    Context m_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testAlsBtn = new Button(m_Context);
        testAlsBtn.setText("环境光传感器");
        testAlsBtn.setAllCaps(false);
        layout.addView(testAlsBtn);
        testAlsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(SensorDemoActivity.this, CAlsActivity.class);
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
                Intent intent = new Intent(SensorDemoActivity.this, CAccelActivity.class);
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
                Intent intent = new Intent(SensorDemoActivity.this, CCompassActivity.class);
                startActivity(intent);
            }
        });

        Button testFingerprintBtn = new Button(m_Context);
        testFingerprintBtn.setText("指纹传感器+Keystor");
        testFingerprintBtn.setAllCaps(false);
        layout.addView(testFingerprintBtn);
        testFingerprintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(SensorDemoActivity.this, CFingerprintActivity.class);
                startActivity(intent);
            }
        });

    }


}
