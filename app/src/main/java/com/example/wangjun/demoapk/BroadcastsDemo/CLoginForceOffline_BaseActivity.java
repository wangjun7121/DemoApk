package com.example.wangjun.demoapk.BroadcastsDemo;

import android.app.Activity;
import android.os.Bundle;
/**
 * Created by wangjun on 2017/10/11.
 */

public class CLoginForceOffline_BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CLoginForceOffline_ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CLoginForceOffline_ActivityCollector.removeActivity(this);
    }

}