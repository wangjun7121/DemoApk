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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statusbardemo);

        Button testStaticHideBarBtn1;
        testStaticHideBarBtn1 = (Button) findViewById(R.id.testStaticHideBarBtn1);
        testStaticHideBarBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testStaticHideBarBtn1");
                Intent intent = new Intent(StatusBarDemoActivity.this, CAppCompatHideBar1Activity.class);
                startActivity(intent);
            }
        });

        Button testStaticHideBarBtn2;
        testStaticHideBarBtn2 = (Button) findViewById(R.id.testStaticHideBarBtn2);
        testStaticHideBarBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testStaticHideBarBtn2");
                Intent intent = new Intent(StatusBarDemoActivity.this, CActivityHideBarActivity.class);
                startActivity(intent);
            }
        });

        Button testDynamicStatusBarBtn;
        testDynamicStatusBarBtn = (Button) findViewById(R.id.testDynamicStatusBarBtn);
        testDynamicStatusBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testStaticHideBarBtn2");
                Intent intent = new Intent(StatusBarDemoActivity.this, CDynamicStatusBarActivity.class);
                startActivity(intent);
            }
        });


    }


}
