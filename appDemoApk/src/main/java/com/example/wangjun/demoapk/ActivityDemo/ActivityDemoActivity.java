package com.example.wangjun.demoapk.ActivityDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.SensorDemo.SensorDemoActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class ActivityDemoActivity extends AppCompatActivity {

    private static String sLogcatTAG = "ActivityDemoActivity";
    Context m_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testActivityLiftCycleBtn = new Button(m_Context);
        testActivityLiftCycleBtn.setText("Activity 生命周期演示");
        testActivityLiftCycleBtn.setAllCaps(false);
        layout.addView(testActivityLiftCycleBtn);
        testActivityLiftCycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ActivityDemoActivity.this, CActivityLifeCycleActivity.class);
                startActivity(intent);
            }
        });

        Button testActivityLanchModeBtn = new Button(m_Context);
        testActivityLanchModeBtn.setText("Activity 启动模式介绍");
        testActivityLanchModeBtn.setAllCaps(false);
        layout.addView(testActivityLanchModeBtn);
        testActivityLanchModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ActivityDemoActivity.this, CActivityLaunchModeActivity.class);
                startActivity(intent);
            }
        });


    }
}
