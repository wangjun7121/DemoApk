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
    private Button testActivityLiftCycleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        testActivityLiftCycleBtn = (Button) findViewById(R.id.testActivityLiftCycleBtn);
        testActivityLiftCycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ActivityDemoActivity.this, TestActivityLifeCycleActivity.class);
                startActivity(intent);
            }
        });
    }
}
