package com.example.wangjun.demoapk.ActivityDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.R;

public class ActivityDemoActivity extends AppCompatActivity {

    private static String sLogcatTAG = "ActivityDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitydemo);

        Button testActivityLiftCycleBtn;
        testActivityLiftCycleBtn = (Button) findViewById(R.id.testActivityLiftCycleBtn);
        testActivityLiftCycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ActivityDemoActivity.this, CActivityLifeCycleActivity.class);
                startActivity(intent);
            }
        });

        Button testActivityLanchModeBtn;
        testActivityLanchModeBtn = (Button) findViewById(R.id.testActivityLanchModeBtn);
        testActivityLanchModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ActivityDemoActivity.this, CActivityLaunchModeActivity.class);
                startActivity(intent);
            }
        });

    }
}
