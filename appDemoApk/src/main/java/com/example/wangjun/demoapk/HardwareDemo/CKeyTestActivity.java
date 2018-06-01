package com.example.wangjun.demoapk.HardwareDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;


import android.content.res.Resources;

import android.os.Bundle;

import android.provider.Settings;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import android.widget.Button;
import android.widget.TextView;

public class CKeyTestActivity extends AppCompatActivity {

    TextView t4;
    TextView t5;
    TextView t6;
    TextView tv_power;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. 隐藏状态栏无效：原因为我继承的是 AppCompatActivity 将其改为 Activity 即可。
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 2. 或用此句隐藏状态栏也行，继承自 AppCompatActivity
        //if (getSupportActionBar() != null){
        //    getSupportActionBar().hide();
        //}

        setContentView(R.layout.hardwaredemo_ckeytest);



        t4 = (TextView) findViewById(R.id.t_4);
        t4.setText("VOLUME UP");

        t5 = (TextView) findViewById(R.id.t_5);
        t5.setText("VOLUME DOWN");

        tv_power = (TextView) findViewById(R.id.tv_power);
        tv_power.setText("POWER");

        t6 = (TextView) findViewById(R.id.t_6);
        t6.setText("只能监听实体音量上下键\n"+
                    "Power/Back 通过广播监听\n"+
                    "工模电源不灭屏需要修改代码\n");

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // log("------ key code:" + keyCode);

        switch (keyCode) {

            case KeyEvent.KEYCODE_VOLUME_UP:
                t4.setText("");


                break;

            case KeyEvent.KEYCODE_VOLUME_DOWN:
                t5.setText("");


                break;

            case KeyEvent.KEYCODE_POWER:
                tv_power.setText("");

                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
}

//
//public class CKeyTestActivity extends AppCompatActivity {
//    protected static final String sLogcatTAG = "CKeyTestActivity";
//    TextView t1;
//    TextView t2;
//    TextView t3;
//    TextView t4;
//    TextView t5;
//    TextView t6;
//    TextView tv_power;
//    View btn_pass = null;
//    int up = 1;
//    int down = 1;
//    int power = 1;
//    int h = 0;
//    int m = 0;
//    int b = 0;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.hardwaredemo_ckeytest);
//
//
//
//        t4 = (TextView) findViewById(R.id.t_4);
//        t4.setText("VOLUME UP");
//
//        t5 = (TextView) findViewById(R.id.t_5);
//        t5.setText("VOLUME DOWN");
//
//        tv_power = (TextView) findViewById(R.id.tv_power);
//        tv_power.setText("POWER");
//
//    }
//
//
//
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // log("------ key code:" + keyCode);
//
//        switch (keyCode) {
//
//            case KeyEvent.KEYCODE_VOLUME_UP:
//                t4.setText("");
//                up = 0;
//
//                break;
//
//            case KeyEvent.KEYCODE_VOLUME_DOWN:
//                t5.setText("");
//                down = 0;
//
//                break;
//
//            case KeyEvent.KEYCODE_POWER:
//                // log("lizusheng  KEYCODE_POWER");
//                Log.v(sLogcatTAG, "wjwind testAccelBtn");
//                tv_power.setText("");
//                power = 0;
//
//                break;
//
//            default:
//                break;
//        }
//
//        return true;
//    }
//}