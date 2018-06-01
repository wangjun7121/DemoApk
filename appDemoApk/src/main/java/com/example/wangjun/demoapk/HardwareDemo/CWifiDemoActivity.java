package com.example.wangjun.demoapk.HardwareDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class CWifiDemoActivity extends AppCompatActivity {
    private static final String LOG_TAG = "EMODE_WIFITEST";

    private static final int MSG_WIFI_ENABLED = 101;
    private static final int MSG_WIFI_SCAN_RESULTS_AVAILABLE = 102;
    private static final int MSG_WIFI_TEST_PASS = 103;
    private static final int MSG_WIFI_TEST_FAIL = 104;

    private static final int WIFI_SCAN_RESULTS_SIZE = 1;

    private TextView mWifiStatusText;
    private TextView mAddressText;
    private TextView mScanListText;

    private WifiManager mWifiManager;
    private StringBuilder mDeviceList = new StringBuilder();
    private boolean DBG = true;
    private final Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch(msg.what){
                case MSG_WIFI_ENABLED:

                    mWifiManager.startScan();
                    //取得WifiInfo对象
                    WifiInfo wifiInfo = mWifiManager.getConnectionInfo();

                    mWifiStatusText.setText("WLAN status：" + "Scanning...");
                    mAddressText.setText("WLAN MAC address:" + wifiInfo.getMacAddress());
                    break;

                case MSG_WIFI_SCAN_RESULTS_AVAILABLE:
                    // 获得 wifi 结果
                    List<ScanResult> scanList = mWifiManager.getScanResults();
                    for (ScanResult scanResult : scanList) {
                        if (!mDeviceList.toString().contains(scanResult.SSID)) {
                            mDeviceList.append(scanResult.SSID);
                            mDeviceList.append("\n");
                        }
                    }
                    mScanListText.setText(mDeviceList.toString());
                    if (scanList.size() >= WIFI_SCAN_RESULTS_SIZE) {
                        //mBtnPass.setVisibility(View.VISIBLE);
                        mHandler.sendEmptyMessageDelayed(MSG_WIFI_TEST_PASS, 500);
                    }
                    break;
                case MSG_WIFI_TEST_PASS:
                    //finishTestcase(Constants.TEST_PASS);
                    break;
                case MSG_WIFI_TEST_FAIL:
                    //finishTestcase(Constants.TEST_FAIL);
                    break;
                default:
                    break;
            }
        }

    };

    // 广播驱动
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            final String action = intent.getAction();
            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {
                int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
                if (state == WifiManager.WIFI_STATE_ENABLED) {
                    mHandler.sendEmptyMessage(MSG_WIFI_ENABLED);
                }
            } else if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
                mHandler.sendEmptyMessage(MSG_WIFI_SCAN_RESULTS_AVAILABLE);
            }
        }

    };

    protected void log(String msg) {
        if (DBG) {
            //Log.e(LOG_TAG, msg);
        }
    }

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.hardwaredemo_cwifidemo);
        mWifiStatusText = (TextView) findViewById(R.id.wifi_status);
        mAddressText = (TextView) findViewById(R.id.wifi_address);
        mScanListText = (TextView) findViewById(R.id.wifi_list);
        mAddressText.setText("WLAN MAC address:" + "unknown");

        // 获取 Wifi 服务
        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        // 监听 Wifi 变化广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(mReceiver, intentFilter);

        // wifi 使能
        if (!mWifiManager.isWifiEnabled()) {
            mWifiStatusText.setText("WLAN status：" + "Off");
            mWifiManager.setWifiEnabled(true);
        } else {

            mHandler.sendEmptyMessage(MSG_WIFI_ENABLED);
        }
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}