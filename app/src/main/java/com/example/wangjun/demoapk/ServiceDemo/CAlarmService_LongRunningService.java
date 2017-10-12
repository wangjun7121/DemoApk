package com.example.wangjun.demoapk.ServiceDemo;

/**
 * Created by wangjun on 2017/10/12.
 */

import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class CAlarmService_LongRunningService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService", "executed at " + new Date().toString());
            }
        }).start();

        ///////////////////////////////////////////////////////////////////////////////
        // 创建一个定时器，到期再执行 Service，保证服务长期执行
        ///////////////////////////////////////////////////////////////////////////////

        // 获取一个 AlarmManager 的实例
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        int anHour =  60 * 1000; // 一分钟

//        SystemClock.elapsedRealtime()：方法可以获取到系统开机至今所经历时间的毫秒数
//        System.currentTimeMillis()方法可以获取到 1970 年 1 月 1 日 0 点至今所经历时间的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, CAlarmService_AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0); // 到期的发送广播

        // 调用 AlarmManager 的 set()方法就可以设置一个定时任务
//        第一个参数是一个整型参数，用于指定 AlarmManager 的工作类型， 有四种值可选， 分别是
//            ELAPSED_REALTIME: 表示让定时任务的触发时间从系统开机开始算起， 但不会唤醒 CPU
//            ELAPSED_REALTIME_WAKEUP：表示让定时任务的触发时间从系统开机开始算起，但会唤醒 CPU
//            RTC：表示让定时任务的触发时间从 1970 年 1 月 1 日 0 点开始算起， 但不会唤醒 CPU
//            RTC_WAKEUP：表示让定时任务的触发时间从 1970 年 1 月 1 日 0 点开始算起，但会唤醒 CPU
//        第二个参数，这个参数就好理解多了，就是定时任务触发的时间
//            如果第一个参数使用的是 ELAPSED_REALTIME 或 ELAPSED_REALTIME_WAKEUP，
//            则这里传入开机至今的时间再加上延迟执行的时间。如果第一个参数使用的是 RTC 或
//            RTC_WAKEUP，则这里传入 1970 年 1 月 1 日 0 点至今的时间再加上延迟执行的时间
//        第三个参数是一个 PendingIntent， 对于它你应该已经不会陌生了吧。 这里我们一般会调
//            用 getBroadcast()方法来获取一个能够执行广播的 PendingIntent。 这样当定时任务被触发的时
//            候，广播接收器的 onReceive()方法就可以得到执行
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);



        return super.onStartCommand(intent, flags, startId);
    }

}