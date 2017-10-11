package com.example.wangjun.demoapk.MultimediaDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.support.v7.app.NotificationCompat;

public class CWidthNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimediademo_cwidthnotification);
    }

    //按钮的点击方法
    public  void show(View v){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("郭嘉");//系统限制，可能不显示
        builder.setContentText("我们打袁绍吧");//系统限制，可能不显示
        builder.setDefaults(Notification.DEFAULT_ALL);
        //添加宽视图
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        style.setBigContentTitle("十胜十败");
        //由手机屏幕像素决定显示多少
        style.addLine("第一是道胜");
        style.addLine("第二是义胜");
        style.addLine("第三是治胜");
        style.addLine("第四是度胜");
        style.addLine("第五是谋胜");
        style.addLine("第六是德胜");
        style.addLine("第七是仁胜");
        style.addLine("第八是明胜");
        style.addLine("第九是文胜");
        style.addLine("第十是武胜");
        style.setSummaryText("作者：郭嘉");//添加概要
        builder.setStyle(style);
        Notification n = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, n);
    }
}
