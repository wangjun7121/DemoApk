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
    // 定义按钮
    private Button testAlsBtn;
    private Button testAccelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensordemo);

        testAlsBtn = (Button) findViewById(R.id.testAls_btn);
        testAlsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(SensorDemoActivity.this, TestAlsActivity.class);
                startActivity(intent);
            }
        });


        testAccelBtn = (Button) findViewById(R.id.testAccel_btn);
        testAccelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testAccelBtn");
                Intent intent = new Intent(SensorDemoActivity.this, TestAccelActivity.class);
                startActivity(intent);
            }
        });





    }


}
