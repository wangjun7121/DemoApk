package com.example.wangjun.demoapk.HardwareDemo;

import android.app.admin.DevicePolicyManager;
import android.graphics.Color;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.app.Service;
import android.os.Vibrator;
import android.os.Message;
import android.os.Handler;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import android.app.PendingIntent;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.widget.Toast;

import java.util.Random;


public class CLEDTestActivity extends AppCompatActivity {

    private static final int LED_ON_RED = 0x66;
    private static final int LED_OFF_RED = 0x67;
    private static final int LED_ON_GREEN = 0x68;
    private static final int LED_OFF_GREEN = 0x69;
    private static final int LED_ON_BLUE = 0x70;
    private static final int LED_OFF_BLUE = 0x71;
    private static final int LED_ON_ORANGE = 0x72;
    private static final int LED_OFF_ORANGE = 0x73;

    // 底层颜色定义： alpa/reg/green/blue
    //      alpa 不为零时亮灯
    private static final int RED = 0xffff0000;
    private static final int GREEN = 0xff00ff00;
    private static final int BLUE = 0xff0000ff;
    private static final int ORANGE = 0xffffff00; // 红+绿

    private NotificationManager mNM;
    private Notification mNotification;
    private static final int ID_LED = 19871103;
    private boolean flag;
    private boolean isBlueSupport;
    private boolean isOrangeSupport;


    private int times = 0;
    private int count = 0;

    protected static final String LOG_TAG = "EMODE_Led";

    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LED_ON_RED:
                    if (flag) {
                        mNotification.ledARGB = RED;
                        mNM.notify(ID_LED, mNotification);
                        handler.sendEmptyMessageDelayed(LED_OFF_RED, 1000);
                    }
                    break;
                case LED_OFF_RED:
                    mNM.cancel(ID_LED);
                    if (flag)
                        handler.sendEmptyMessageDelayed(LED_ON_GREEN, 1000);
                    break;
                case LED_ON_GREEN:
                    if (flag) {
                        mNotification.ledARGB = GREEN;
                        mNM.notify(ID_LED, mNotification);
                        handler.sendEmptyMessageDelayed(LED_OFF_GREEN, 1000);
                    }
                    break;
                case LED_OFF_GREEN:
                    mNM.cancel(ID_LED);
                    if (flag) {
                        if (isBlueSupport) {
                            handler.sendEmptyMessageDelayed(LED_ON_BLUE, 1000);
                            break;
                        } else if (isOrangeSupport) {
                            handler.sendEmptyMessageDelayed(LED_ON_ORANGE, 1000);
                            break;
                        } else {
                            showNextRound();
                        }
                    }
                    break;
                case LED_ON_BLUE:
                    if (flag) {
                        mNotification.ledARGB = BLUE;
                        mNM.notify(ID_LED, mNotification);
                        handler.sendEmptyMessageDelayed(LED_OFF_BLUE, 1000);
                    }
                    break;
                case LED_OFF_BLUE:
                    count++;
                    mNM.cancel(ID_LED);
                    if (flag) {
                        showNextRound();
                    }

                    break;

                case LED_ON_ORANGE:
                    if (flag) {
                        mNotification.ledARGB = ORANGE;
                        mNM.notify(ID_LED, mNotification);
                        handler.sendEmptyMessageDelayed(LED_OFF_ORANGE, 1000);
                    }
                    break;
                case LED_OFF_ORANGE:
                    count++;
                    mNM.cancel(ID_LED);
                    if (flag) {
                        showNextRound();
                    }

                    break;

                default:
                    break;
            }
        }
    };


    public void showNextRound() {
        if (count < times) {
            handler.sendEmptyMessageDelayed(LED_ON_RED, 1000);
        //} else {
            //showDialogNew();
        }
    }


    private DevicePolicyManager devicePolicyManager;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.hardwaredemo_cledtest);
//        times = getRandom();
        times = 3;
        count = 0;
        isBlueSupport = false;          // 蓝灯
        isOrangeSupport = true;         // 橙灯 = 红 + 绿



        // 发送通知
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotification = new Notification.Builder(this).
                setPriority(Notification.PRIORITY_HIGH).
                setSmallIcon(R.mipmap.ic_launcher).build();
        //mNotification.ledOnMS = 100;
        //mNotification.ledOffMS = 100;


        mNotification.flags = Notification.FLAG_SHOW_LIGHTS;
        mNotification.defaults = Notification.DEFAULT_VIBRATE;

        flag = true;

        Button testRed_btn;
        testRed_btn = (Button) findViewById(R.id.testRed_btn);
        testRed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotification.ledARGB = Color.RED;
                mNM.notify(ID_LED, mNotification);
            }
        });

        Button testGreen_btn;
        testGreen_btn = (Button) findViewById(R.id.testGreen_btn);
        testGreen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotification.ledARGB = Color.GREEN;
                mNM.notify(ID_LED, mNotification);
            }
        });

        Button testBlue_btn;
        testBlue_btn = (Button) findViewById(R.id.testBlue_btn);
        testBlue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotification.ledARGB = Color.BLUE;
                mNM.notify(ID_LED, mNotification);
            }
        });

        Button testAllLED_btn;
        testAllLED_btn = (Button) findViewById(R.id.testAllLED_btn);
        testAllLED_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessageDelayed(LED_ON_RED, 1000);
            }
        });


        // 需要获得休眠锁，防止进程休眠，有时候也不起作用。。。
        acquireWakeLock();

    }

    @Override
    protected void onDestroy() {


        mNM.cancel(ID_LED);
        super.onDestroy();
        synchronized(this) {
            releaseWakeLock();
        }



    }
    PowerManager.WakeLock wakeLock;
    private void acquireWakeLock() {

        if (wakeLock == null) {

            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, this.getClass().getCanonicalName());
            wakeLock.acquire();
        }

    }


    private void releaseWakeLock() {
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
            wakeLock = null;
        }

    }


//    protected void showDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
//        builder.setTitle("请判断LED灯循环闪烁几次");
//        builder.setMessage("每种颜色各闪一次为一次循环哦！");
//        builder.setCancelable(false);
//        builder.setPositiveButton("通过", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                btn_pass.setVisibility(View.VISIBLE);
//                btn_pass.callOnClick();
//            }
//        });
//        builder.setNegativeButton("失败", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
////                btn_fail = getWindow().getDecorView().findViewById(R.id.btn_fail);
////                btn_fail.callOnClick();
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//
//    protected void showDialogNew() {
//        count = 0;
//        Toast.makeText(this, "每种颜色各闪一次为一次循环哦！", Toast.LENGTH_SHORT).show();
//
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
//        builder.setTitle("每种颜色各闪一次为一次循环哦！");
//        final String[] numbers = {"1", "2", "3", "4"};
//        builder.setSingleChoiceItems(numbers, -1, new DialogInterface.OnClickListener() {
//            @java.lang.Override
//            public void onClick(DialogInterface dialog, int which) {
//                count = which + 1;
//            }
//        });
//        builder.setPositiveButton("通过", new DialogInterface.OnClickListener() {
//            @java.lang.Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (times == count) {
//                    btn_pass.setVisibility(View.VISIBLE);
//                    btn_pass.callOnClick();
//                } else {
////                    btn_fail = getWindow().getDecorView().findViewById(R.id.btn_fail);
////                    btn_fail.callOnClick();
//                }
//            }
//        });
//        builder.show();
//    }

    protected int getRandom() {
        Random random = new Random();
        int i = random.nextInt();
        i = Math.abs(i) % 4 + 1;
        return i;
    }
}