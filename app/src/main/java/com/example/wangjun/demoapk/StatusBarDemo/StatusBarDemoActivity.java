package com.example.wangjun.demoapk.StatusBarDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.R;


public class StatusBarDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "StatusBarDemoActivity";
    // 定义按钮
    private Button testStaticHideBarBtn1;
    private Button testStaticHideBarBtn2;
    private Button testDynamicStatusBarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_bar_demo);

        testStaticHideBarBtn1 = (Button) findViewById(R.id.testStaticHideBarBtn1);
        testStaticHideBarBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testStaticHideBarBtn1");
                // Intent intent = new Intent("android.intent.action.mylistview");
                Intent intent = new Intent(StatusBarDemoActivity.this, testStaticHideBar1Activity.class);
                startActivity(intent);
            }
        });


        testStaticHideBarBtn2 = (Button) findViewById(R.id.testStaticHideBarBtn2);
        testStaticHideBarBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testStaticHideBarBtn2");
                // Intent intent = new Intent("android.intent.action.mylistview");
                Intent intent = new Intent(StatusBarDemoActivity.this, testStaticHideBar2Activity.class);
                startActivity(intent);
            }
        });

        testDynamicStatusBarBtn = (Button) findViewById(R.id.testDynamicStatusBarBtn);
        testDynamicStatusBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testStaticHideBarBtn2");
                // Intent intent = new Intent("android.intent.action.mylistview");
                Intent intent = new Intent(StatusBarDemoActivity.this, testDynamicStatusBarActivity.class);
                startActivity(intent);
            }
        });


    }


}
