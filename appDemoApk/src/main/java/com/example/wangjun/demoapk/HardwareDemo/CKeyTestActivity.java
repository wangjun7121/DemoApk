package com.example.wangjun.demoapk.HardwareDemo;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
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

    private AudioManager mAudioManager;
    private ComponentName mComponentName;

    TextView t4;
    TextView t5;
    TextView t6;
    TextView tv_power;

    TextView tHeasetStatus;
    TextView tHeasetHook;

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


        // 监听耳机插拔事件
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        // AudioManager注册一个MediaButton对象
//        mComponentName = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());


        t4 = (TextView) findViewById(R.id.t_4);
        t4.setText("VOLUME UP");

        t5 = (TextView) findViewById(R.id.t_5);
        t5.setText("VOLUME DOWN");

        tv_power = (TextView) findViewById(R.id.tv_power);
        tv_power.setText("POWER");

        tHeasetStatus = (TextView) findViewById(R.id.theasetstatus);
        tHeasetStatus.setText("Heaset plug: Unknow");

        tHeasetHook = (TextView) findViewById(R.id.theasethook);
        tHeasetHook.setText("Heaset Hook");

        t6 = (TextView) findViewById(R.id.t_6);
        t6.setText("只能监听实体音量上下键\n"+
                    "Power/Back 通过广播监听\n"+
                    "工模电源不灭屏需要修改代码\n");

    }

    @Override
    protected void onResume() {

        mAudioManager.registerMediaButtonEventReceiver(mComponentName);
        registerReceiver(headSetReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));

        super.onResume();

    }

    @Override
    protected void onPause() {
        // 取消注册
        mAudioManager.unregisterMediaButtonEventReceiver(mComponentName);
        unregisterReceiver(headSetReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mAudioManager = null;
        mComponentName = null;
        super.onDestroy();
    }

    private final BroadcastReceiver headSetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_HEADSET_PLUG)) {
                // phone headset plugged
                if (intent.getIntExtra("state", 0) == 1) {
                    // do something
//                  Log.d(TAG, "耳机检测：插入");
//                  Toast.makeText(context, "耳机检测：插入", Toast.LENGTH_SHORT) .show();
                    //mAudioManager.registerMediaButtonEventReceiver(mComponentName);

                    tHeasetStatus.setText("Heaset plug: In");


                } else {// phone head unplugged

                    // do something
//                  Log.d(TAG, "耳机检测：没有插入");
//                  Toast.makeText(context, "耳机检测：没有插入", Toast.LENGTH_SHORT).show();
                    //mAudioManager.unregisterMediaButtonEventReceiver(mComponentName);

                    tHeasetStatus.setText("Heaset plug: Out");
                }
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // log("------ key code:" + keyCode);

        switch (keyCode) {
            case KeyEvent.KEYCODE_HEADSETHOOK://按下了耳机键
                if (event.getRepeatCount() == 0) {  //如果长按的话，getRepeatCount值会一直变大
                    //短按
                    tHeasetHook.setText("Heaset Hook: click");
                } else {
                    //长按
                    tHeasetHook.setText("Heaset Hook: press");
                }

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
