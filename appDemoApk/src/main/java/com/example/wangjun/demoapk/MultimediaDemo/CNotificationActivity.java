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


public class CNotificationActivity extends AppCompatActivity {
    private static final int NO_1 =0x1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimediademo_cnotification);
    }

    // 一种新的添加按钮的方法：直接在 xml 文件中添加
    public  void show1(View v){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("郭嘉");
        builder.setContentText("我们打袁绍吧");

        // 设置 Notification.Default_ALL (默认启用全部服务(呼吸灯，铃声等)
        builder.setDefaults(Notification.DEFAULT_ALL);

        // 调用 NotificationCompat.Builder 的 setContentIntent() 来添加 PendingIntent
        Intent intent = new Intent(this, CNotification_IntentActivity.class);
        intent.putExtra("info", "郭嘉给你发了一个计策！");
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);
        //获取 Notification
        Notification n = builder.build();


        //通过 NotificationCompat.Builder.build() 来获得 notification 对象自己
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //然后调用NotificationManager.notify()向系统转交
        manager.notify(NO_1, n);
    }
}
//
//public class CNotificationActivity extends Activity {
//    private static final int NOTIFICATION_FLAG = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.multimediademo_cnotification);
//
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notification = new Notification.Builder(Context)
//                 .setAutoCancel(true)
//                 .setContentTitle("title")
//                 .setContentText("describe")
//                 .setContentIntent(pendingIntent)
//                 .setSmallIcon(R.drawable.ic_launcher)
//                 .setWhen(System.currentTimeMillis())
//                 .build();
//    }

//    public void notificationMethod(View view) {
//        // 在Android进行通知处理，首先需要重系统哪里获得通知管理器NotificationManager，它是一个系统Service。
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        switch (view.getId()) {
            // 默认通知
//            case R.id.btn1:
//                // 创建一个PendingIntent，和Intent类似，不同的是由于不是马上调用，需要在下拉状态条出发的activity，所以采用的是PendingIntent,即点击Notification跳转启动到哪个Activity
//                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                        new Intent(this, CNotificationActivity.class), 0);
//                // 下面需兼容Android 2.x版本是的处理方式
//                // Notification notify1 = new Notification(R.drawable.message,
//                // "TickerText:" + "您有新短消息，请注意查收！", System.currentTimeMillis());
//                Notification notify1 = new Notification();
//                notify1.icon = R.drawable.listviewdemo_i1;
//                notify1.tickerText = "TickerText:您有新短消息，请注意查收！";
//                notify1.when = System.currentTimeMillis();
//                notify1.setLatestEventInfo(this, "Notification Title",
//                        "This is the notification message", pendingIntent);
//                notify1.number = 1;
//                notify1.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
//                // 通过通知管理器来发起通知。如果id不同，则每click，在statu那里增加一个提示
//                manager.notify(NOTIFICATION_FLAG, notify1);
//                break;
//
//            // 默认通知 API11及之后可用
//            case R.id.btn2:
//                PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0,
//                        new Intent(this, CNotificationActivity.class), 0);
//                // 通过Notification.Builder来创建通知，注意API Level
//                // API11之后才支持
//                Notification notify2 = new Notification.Builder(this)
//                        .setSmallIcon(R.drawable.listviewdemo_i1) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
//                        // icon)
//                        .setTicker("TickerText:" + "您有新短消息，请注意查收！")// 设置在status
//                        // bar上显示的提示文字
//                        .setContentTitle("Notification Title")// 设置在下拉status
//                        // bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
//                        .setContentText("This is the notification message")// TextView中显示的详细内容
//                        .setContentIntent(pendingIntent2) // 关联PendingIntent
//                        .setNumber(1) // 在TextView的右方显示的数字，可放大图片看，在最右侧。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
//                        .getNotification(); // 需要注意build()是在API level
//                // 16及之后增加的，在API11中可以使用getNotificatin()来代替
//                notify2.flags |= Notification.FLAG_AUTO_CANCEL;
//                manager.notify(NOTIFICATION_FLAG, notify2);
//                break;
//            // 默认通知 API16及之后可用
//            case R.id.btn3:
//                PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 0,
//                        new Intent(this, CNotificationActivity.class), 0);
//                // 通过Notification.Builder来创建通知，注意API Level
//                // API16之后才支持
//                Notification notify3 = new Notification.Builder(this)
//                        .setSmallIcon(R.drawable.listviewdemo_i1)
//                        .setTicker("TickerText:" + "您有新短消息，请注意查收！")
//                        .setContentTitle("Notification Title")
//                        .setContentText("This is the notification message")
//                        .setContentIntent(pendingIntent3).setNumber(1).build(); // 需要注意build()是在API
//                // level16及之后增加的，API11可以使用getNotificatin()来替代
//                notify3.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
//                manager.notify(NOTIFICATION_FLAG, notify3);// 步骤4：通过通知管理器来发起通知。如果id不同，则每click，在status哪里增加一个提示
//                break;
//            // 自定义通知
//            case R.id.btn4:
//                // Notification myNotify = new Notification(R.drawable.message,
//                // "自定义通知：您有新短信息了，请注意查收！", System.currentTimeMillis());
//                Notification myNotify = new Notification();
//                myNotify.icon = R.drawable.listviewdemo_i1;
//                myNotify.tickerText = "TickerText:您有新短消息，请注意查收！";
//                myNotify.when = System.currentTimeMillis();
//                myNotify.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除
//                RemoteViews rv = new RemoteViews(getPackageName(),
//                        R.layout.multimediademo_cnotificationintent);
//                rv.setTextViewText(R.id.text_content, "hello wrold!");
//                myNotify.contentView = rv;
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                PendingIntent contentIntent = PendingIntent.getActivity(this, 1,
//                        intent, 1);
//                myNotify.contentIntent = contentIntent;
//                manager.notify(NOTIFICATION_FLAG, myNotify);
//                break;
//            case R.id.btn5:
//                // 清除id为NOTIFICATION_FLAG的通知
//                manager.cancel(NOTIFICATION_FLAG);
//                // 清除所有的通知
//                // manager.cancelAll();
//                break;
//            default:
//                break;
//        }
//    }
//}