package com.example.wangjun.demoapk.StatusBarDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;

public class testStaticHideBar1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 2. 或用此句隐藏状态栏也行，继承自 AppCompatActivity
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.hello_world);
        setContentView(R.layout.status_bar_demo_teststatichidebar1);
    }
}
