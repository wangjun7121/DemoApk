package com.example.wangjun.demoapk.ServiceDemo;

/**
 * Created by wangjun on 2017/10/12.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CAlarmService_AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, CAlarmService_LongRunningService.class);
        context.startService(i);
    }

}