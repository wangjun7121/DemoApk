package com.example.wangjun.demoapk.LocationDemo;

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

public class LocationDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "LocationDemo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationdemo);

        Button testLocationManagerBtn;
        testLocationManagerBtn = (Button) findViewById(R.id.testLocationManagerBtn);
        testLocationManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LocationDemoActivity.this, CLocationManagerActivity.class);
                startActivity(intent);
            }
        });

        Button testActivityLanchModeBtn;
        testActivityLanchModeBtn = (Button) findViewById(R.id.testActivityLanchModeBtn);
        testActivityLanchModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LocationDemoActivity.this, CActivityLaunchModeActivity.class);
                startActivity(intent);
            }
        });

    }
}
