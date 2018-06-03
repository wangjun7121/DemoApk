package com.example.wangjun.demoapk.HardwareDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.format.DateUtils;

import android.view.View;
import android.widget.TextView;

import com.example.wangjun.demoapk.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

// 程序整体是以监听电池广播实现的，部分是直接读电池节点

public class CBatteryInfoActivity extends AppCompatActivity {
    private static final int EVENT_TICK = 1;
    private static final int READ_ELEC = 2;
    private TextView mStatus;
    private TextView mPower;
    private TextView mLevel;
    private TextView mScale;
    private TextView mHealth;
    private TextView mVoltage;
    private TextView mTemperature;
    private TextView mTechnology;
    private TextView mUptime;
    private TextView mElectricity;
    private TextView mElectricityTv;
    //private IBatteryStats mBatteryStats;
    //private IPowerManager mScreenStats;
    private boolean isShowElec;
    private View btn_pass = null;
    private  Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EVENT_TICK:// 每 1s 发消息唤醒一次线程，更新电池信息

                    // 更新电池信息
                    updateBatteryStats();

                    // 再隔 1s 再次唤醒此线程
                    sendEmptyMessageDelayed(EVENT_TICK, 1000);

                    break;
                case READ_ELEC:// 每 3s 发消息唤醒一次线程，更新信息
                    if (getElectricityData() != null){
                        if (getElectricityData().toString().trim().equals("0")){
                            mElectricity.setText(getString(R.string.tv_loading));
                            sendEmptyMessageDelayed(READ_ELEC, 3000);
                        }else {
                            mElectricity.setText(getElectricityData().toString().trim());
                            sendEmptyMessageDelayed(READ_ELEC, 3000);
                        }
                    }else {
                        sendEmptyMessageDelayed(READ_ELEC, 3000);
                    }


                    break;
            }
        }
    };

    /**
     * Listens for intent broadcasts
     */
    private IntentFilter mIntentFilter;

    // 这里处理电池信息变化广播
    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                //int plugType = intent.getIntExtra("plugged", 0);
                File plugType = new File("/sys/class/power_supply/usb/type");

                mLevel.setText("" + intent.getIntExtra("level", 0));
                mScale.setText("" + intent.getIntExtra("scale", 0));
                mVoltage.setText("" + intent.getIntExtra("voltage", 0) + " " +
                        getString(R.string.battery_info_voltage_units));
                mTemperature.setText("" +
                        tenthsToFixedString(intent.getIntExtra("temperature", 0)) +
                        getString(R.string.battery_info_temperature_units));
                mTechnology.setText("" + intent.getStringExtra("technology"));

                mStatus.setText(getBatteryStatus(getResources(), intent));


                //qiancheng@wind-mobi.com start
/*                switch (plugType) {
                    case 0:
                        mPower.setText(getString(R.string.battery_info_power_unplugged));
                        btn_pass.setVisibility(View.GONE);
                        break;

                    case BatteryManager.BATTERY_PLUGGED_AC:
                        mPower.setText(getString(R.string.battery_info_power_ac));
                        btn_pass.setVisibility(View.VISIBLE);
                        break;

                    case BatteryManager.BATTERY_PLUGGED_USB:
                        mPower.setText(getString(R.string.battery_info_power_usb));
                        btn_pass.setVisibility(View.VISIBLE);
                        break;

                    case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                        mPower.setText(getString(R.string.battery_info_power_wireless));
                        btn_pass.setVisibility(View.VISIBLE);
                        break;

                    case (BatteryManager.BATTERY_PLUGGED_AC | BatteryManager.BATTERY_PLUGGED_USB):
                        mPower.setText(getString(R.string.battery_info_power_ac_usb));
                        btn_pass.setVisibility(View.VISIBLE);
                        break;

                    default:
                        mPower.setText(getString(R.string.battery_info_power_unknown));
                        btn_pass.setVisibility(View.GONE);
                        break;
                }*/
                FileReader plugTypeReader = null;
                try {
                    plugTypeReader = new FileReader(plugType);
                    char[] arrayOfChar = new char[30];
                    String[] arrayOfString = new String(arrayOfChar, 0, plugTypeReader.read(arrayOfChar)).trim().split("\n");
                    if ( "USB_DCP".equalsIgnoreCase(arrayOfString[0]))
                    {
                        mPower.setText("AC");
                       // btn_pass.setVisibility(View.VISIBLE);
                    }else if ("USB".equalsIgnoreCase(arrayOfString[0])) {
                        mPower.setText("USB");
                        //btn_pass.setVisibility(View.VISIBLE);
                    }else if( "Unknown".equals(arrayOfString[0])){
                        mPower.setText("power unplugged");
                        //btn_pass.setVisibility(View.GONE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (plugTypeReader != null) {
                            plugTypeReader.close();
                            plugTypeReader = null;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //qiancheng@wind-mobi.com add end
                int health = intent.getIntExtra("health", BatteryManager.BATTERY_HEALTH_UNKNOWN);
                String healthString;
                if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
                    healthString = "good";
                } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
                    //songyan01@wind-mobi.com 2017/12/8 start
                    showDialogMessage(R.string.temhighmessage,R.string.temmessage);
                    //songyan01@wind-mobi.com 2017/12/8 end
                    healthString = "overheat";
                } else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
                    healthString = "dead";
                } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
                    //songyan01@wind-mobi.com 2017/12/8 start
                    showDialogMessage(R.string.volagehighmessage,R.string.volagehigh);
                    //songyan01@wind-mobi.com 2017/12/8 end
                    healthString = "over voltage";
                } else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
                    healthString = "unspecified failure";
                } else if (health == BatteryManager.BATTERY_HEALTH_COLD) {
                    //songyan01@wind-mobi.com 2017/12/8 start
                    showDialogMessage(R.string.temlowmessage,R.string.temlow);
                    //songyan01@wind-mobi.com 2017/12/8 end
                    healthString = "battery low";
                } else {
                    healthString = "unknown";
                }

                mHealth.setText(healthString);

            }
        }
    };

    /**
     * Format a number of tenths-units as a decimal string without using a
     * conversion to float.  E.g. 347 -> "34.7"
     */
    //songyan01@wind-mobi.com 2017/12/8 start

    // 过温保护显示
    private void showDialogMessage(int tem,int message){
        AlertDialog.Builder builder = new AlertDialog.Builder(CBatteryInfoActivity.this);
        builder.setTitle(tem);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        builder.show();
    }
    //songyan01@wind-mobi.com 2017/12/8 end

    private final String tenthsToFixedString(int x) {
        int tens = x / 10;

        return Integer.toString(tens) + "." + Math.abs((x - (10 * tens)));
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.hardwaredemo_cbatteryinfo);
        // 设置标题
        setTitle("battery information");
        isShowElec = false;//getResources().getBoolean(R.bool.config_show_battery_elec_support);

        // create the IntentFilter that will be used to listen
        // to battery status broadcasts

        // 监听电池信息变化广播
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (btn_pass == null) {
            View dv = getWindow().getDecorView();
            //btn_pass = dv.findViewById(R.id.btn_succ);
            //btn_pass.setVisibility(View.GONE);
        }
        mStatus = (TextView) findViewById(R.id.status);
        mPower = (TextView) findViewById(R.id.power);
        mLevel = (TextView) findViewById(R.id.level);
        mScale = (TextView) findViewById(R.id.scale);
        mHealth = (TextView) findViewById(R.id.health);
        mTechnology = (TextView) findViewById(R.id.technology);
        mVoltage = (TextView) findViewById(R.id.voltage);
        mTemperature = (TextView) findViewById(R.id.temperature);
        mUptime = (TextView) findViewById(R.id.uptime);
        mElectricity = (TextView) findViewById(R.id.electricity);
        mElectricityTv = (TextView) findViewById(R.id.electricity_tv);
        if (!isShowElec){
            mElectricity.setVisibility(View.GONE);
            mElectricityTv.setVisibility(View.GONE);
        }else {
            // 发消息给 Handle 处理读消息
            mHandler.sendEmptyMessageDelayed(READ_ELEC, 3000);
        }




        // Get awake time plugged in and on battery
        //mBatteryStats = IBatteryStats.Stub.asInterface(ServiceManager.getService("batteryinfo"));
        //mScreenStats = IPowerManager.Stub.asInterface(ServiceManager.getService(POWER_SERVICE));

        // 发消息进行电池信息更新
        mHandler.sendEmptyMessageDelayed(EVENT_TICK, 1000);

        // 注册广播监听
        registerReceiver(mIntentReceiver, mIntentFilter);
    }

    private String getElectricityData() {
        File mProximsenor = new File("/sys/class/power_supply/battery/current_now");
        String data = null;
        FileInputStream fis = null;
        if (mProximsenor.exists()) {
            try {
                /*fis =  new FileInputStream(mProximsenor);
                int len = fis.available();
                byte[] buf = new byte[len];
                fis.read(buf);
                data = new String(buf);*/
                Scanner scanner = new Scanner(mProximsenor);
                data = scanner.next();
                data = data.replaceAll("\\s","");
                scanner.close();
                return String.valueOf(Math.abs(Integer.parseInt(data)) / 1000);

            } catch (IOException e) {
                e.printStackTrace();
            }
            //Log.i("BatteryTest", "getLightSensorData value " + data);
        }
        return String.valueOf(Math.abs(Integer.parseInt(data)) / 1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeMessages(EVENT_TICK);
        mHandler.removeMessages(READ_ELEC);

        // we are no longer on the screen stop the observers
        unregisterReceiver(mIntentReceiver);
    }

    private void updateBatteryStats() {

        // 更新时间显示： Battery uptime
        long uptime = SystemClock.elapsedRealtime();
        mUptime.setText(DateUtils.formatElapsedTime(uptime / 1000));
    }

    public String getBatteryStatus(Resources res, Intent batteryChangedIntent) {
        final Intent intent = batteryChangedIntent;

        int plugType = intent.getIntExtra("plugged", 0);
        int status = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
        String statusString;

        if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
            statusString = "charging";

            if (plugType > 0) {
                int resId;

                if (plugType == BatteryManager.BATTERY_PLUGGED_AC) {
                    resId = R.string.battery_info_status_charging_ac;
                } else if (plugType == BatteryManager.BATTERY_PLUGGED_USB) {
                    resId = R.string.battery_info_status_charging_usb;
                } else {
                    resId = R.string.battery_info_status_charging_wireless;
                }

                statusString = statusString + " " + res.getString(resId);
            }
        } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
            statusString = res.getString(R.string.battery_info_status_discharging);
        } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
            statusString = res.getString(R.string.battery_info_status_not_charging);
        } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
            statusString = res.getString(R.string.battery_info_status_full);
        } else {
            statusString = res.getString(R.string.battery_info_status_unknown);
        }

        return statusString;
    }

}