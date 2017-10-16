package com.example.wangjun.demoapk.ServiceDemo;

/**
 * Created by wangjun on 2017/10/12.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.wangjun.demoapk.R;


//定义一个服务：
//    1. 写一个类继承 Service 类：调用流程
//        onBind: 覆写，实现活动与服务绑定，返回内部类 Binder 子类
//        onCreate: 覆写，服务第一次创建时调用
//        onStartCommand: 覆写，每次服务启动时调用
//        onDestroy: 覆写，服务销毁时调用
//
//    2. 在 AndroidManifest.xml 中注册
//        Android 四大组件都要在 Androidmanifest.xml 中注册

//活动与服务进行通信：
//        主要是想实现活动上点击下载，后台就开始下载了
//        实现步骤：
//        1. 在 Service 的继承类里写一个内部类继承 Binder
//            这个内部类即是活动中要调用的后台方法，如下载什么
//        2. 在 Activity 的继承类中声明一个 ServiceConnection 对象
//            onServiceDisconnected: 覆写，解除活动与服务绑定时调用
//            onServiceConnected: 覆写，活动与服务绑定成功时调用，调用 Binder 继承类后台方法
//                在这个函数里获得 1 声明的内部类，可以在活动中任务调用其 public 方法
//                即实现活动想让服务做什么就做什么
//        3. 在 Activity 里绑定服务
//            声明一个 Intent,将当前活动与服务绑定
//            bindService: 绑定服务

public class CServiceTest_MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    // 用于与 Activity 进行通信
    class DownloadBinder extends Binder {

        public void startDownload() {

            // 创建一个新线程执行
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // start downloading
                }
            }).start();
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyService", "onBind executed");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        // 在API11之后构建Notification的方式
        Notification.Builder builder = new Notification.Builder
        (this.getApplicationContext()); //获取一个Notification构造器
        Intent nfIntent = new Intent(this, CServiceTestActivity.class);

        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                .setContentTitle("下拉列表中的Title") // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentText("要显示的内容") // 设置上下文内容
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间

        Notification notification = builder.build();    // 获取构建好的 Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音

        // 启动前台服务
        // 参数一：唯一的通知标识；参数二：通知消息。
        startForeground(110, notification);// 开始前台服务

//        Notification notification = new Notification(R.mipmap.ic_launcher,
//                "Notification comes", System.currentTimeMillis());
//        Intent notificationIntent = new Intent(this, CServiceTestActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                notificationIntent, 0);
//        notification.setLatestEventInfo(this, "This is title", "This is content",
//                pendingIntent);
//        startForeground(1, notification);


        Log.d("MyService", "onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand executed");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // do something here
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        // 停止前台服务
        stopForeground(true);// 停止前台服务--参数：表示是否移除之前的通知

        super.onDestroy();


        Log.d("MyService", "onDestroy executed");
    }

}
