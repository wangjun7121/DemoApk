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
    // 定义按钮
    private Button m_testActivityLiftCycleBtn;
    private Button m_testActivityLanchModeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitydemo);

        m_testActivityLiftCycleBtn = (Button) findViewById(R.id.m_testActivityLiftCycleBtn);
        m_testActivityLiftCycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ActivityDemoActivity.this, TestActivityLifeCycleActivity.class);
                startActivity(intent);
            }
        });

        m_testActivityLanchModeBtn = (Button) findViewById(R.id.m_testActivityLanchModeBtn);
        m_testActivityLanchModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ActivityDemoActivity.this, TestActivityLaunchModeActivity.class);
                startActivity(intent);
            }
        });

    }
}
