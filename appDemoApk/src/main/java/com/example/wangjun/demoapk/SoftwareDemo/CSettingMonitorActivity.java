package com.example.wangjun.demoapk.SoftwareDemo;

import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangjun.demoapk.R;

public class CSettingMonitorActivity extends AppCompatActivity {

    private  SettingsValueChangeContentObserver mContentOb;
    class SettingsValueChangeContentObserver extends ContentObserver {

        public SettingsValueChangeContentObserver() {
            super( new Handler());
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Toast.makeText(CSettingMonitorActivity.this, Settings.System.getString(getContentResolver(), Settings.System.AIRPLANE_MODE_ON), Toast.LENGTH_SHORT).show();
            airPlaneModeText.setText("当前飞行模式状态 ="+Settings.System.getString(getContentResolver(), Settings.System.AIRPLANE_MODE_ON));
        }
    }
    private TextView airPlaneModeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.softwoaredemo_csettingmonitor);
        airPlaneModeText = (TextView) findViewById(R.id.airPlaneModeText);
        mContentOb = new SettingsValueChangeContentObserver();
        getContentResolver().registerContentObserver(Settings.System.getUriFor(Settings.System.AIRPLANE_MODE_ON),true, mContentOb);//注册监听

    }

    @Override
    protected void onStop() {
        super.onStop();
        getContentResolver().unregisterContentObserver(mContentOb);
    }
}
