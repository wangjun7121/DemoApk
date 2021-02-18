package com.example.wangjun.demoapk.StatusBarDemo;

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


public class StatusBarDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "StatusBarDemoActivity";
    Context m_Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testStaticHideBarBtn1 = new Button(m_Context);
        testStaticHideBarBtn1.setText("动态显示/隐藏状态栏");
        testStaticHideBarBtn1.setAllCaps(false);
        layout.addView(testStaticHideBarBtn1);
        testStaticHideBarBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(StatusBarDemoActivity.this, CAppCompatHideBar1Activity.class);
                startActivity(intent);
            }
        });

        Button testStaticHideBarBtn2 = new Button(m_Context);
        testStaticHideBarBtn2.setText("AppCompatActivity 隐藏状态栏");
        testStaticHideBarBtn2.setAllCaps(false);
        layout.addView(testStaticHideBarBtn2);
        testStaticHideBarBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(StatusBarDemoActivity.this, CActivityHideBarActivity.class);
                startActivity(intent);
            }
        });

        Button testDynamicStatusBarBtn = new Button(m_Context);
        testDynamicStatusBarBtn.setText("Activity 隐藏状态栏");
        testDynamicStatusBarBtn.setAllCaps(false);
        layout.addView(testDynamicStatusBarBtn);
        testDynamicStatusBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(StatusBarDemoActivity.this, CDynamicStatusBarActivity.class);
                startActivity(intent);
            }
        });



    }


}
