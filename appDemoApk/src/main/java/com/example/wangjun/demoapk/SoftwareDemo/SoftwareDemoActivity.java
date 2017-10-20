package com.example.wangjun.demoapk.SoftwareDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.HardwareDemo.HardwareDemoActivity;
import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;

public class SoftwareDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.softwaredemo);

        Button testDevicePolicyManagerBtn;
        testDevicePolicyManagerBtn = (Button) findViewById(R.id.testDevicePolicyManagerBtn);
        testDevicePolicyManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SoftwareDemoActivity.this, CDevicePolicyManagerActivity.class);
                startActivity(intent);
            }
        });

    }
}
