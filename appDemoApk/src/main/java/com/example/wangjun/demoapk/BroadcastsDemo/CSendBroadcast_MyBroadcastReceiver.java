package com.example.wangjun.demoapk.BroadcastsDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wangjun on 2017/10/10.
 */

public class CSendBroadcast_MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in CSendBroadcast_MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        // 高优先级广播会截断低优先级广播的传播
        abortBroadcast();
    }
}
