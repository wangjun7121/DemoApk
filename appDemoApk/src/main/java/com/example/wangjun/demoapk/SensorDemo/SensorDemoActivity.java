package com.example.wangjun.demoapk.SensorDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.R;

public class SensorDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "SensorDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensordemo);

        Button testAlsBtn;
        testAlsBtn = (Button) findViewById(R.id.testAls_btn);
        testAlsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(SensorDemoActivity.this, CAlsActivity.class);
                startActivity(intent);
            }
        });

        Button testAccelBtn;
        testAccelBtn = (Button) findViewById(R.id.testAccel_btn);
        testAccelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testAccelBtn");
                Intent intent = new Intent(SensorDemoActivity.this, CAccelActivity.class);
                startActivity(intent);
            }
        });


        Button testCompassBtn;
        testCompassBtn = (Button) findViewById(R.id.testCompassBtn);
        testCompassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testAccelBtn");
                Intent intent = new Intent(SensorDemoActivity.this, CCompassActivity.class);
                startActivity(intent);
            }
        });


    }


}
