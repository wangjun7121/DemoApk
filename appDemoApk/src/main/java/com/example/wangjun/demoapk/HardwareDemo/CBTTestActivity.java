package com.example.wangjun.demoapk.HardwareDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Context;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.bluetooth.BluetoothAdapter;



public class CBTTestActivity extends AppCompatActivity {
    private static final long MAX_SCAN_TIME = 30 * 1000;
    private static final int MSG_BT_ON = 100;
    private static final int MSG_BT_OFF = 101;
    private static final int MSG_BT_TEST_PASS = 102;
    private static final int MSG_BT_TEST_FAIL = 103;

    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private Button mButton;
    private boolean mOrigBTState = false;
    private BluetoothAdapter mBtAdapter = null;
    private StringBuilder mDeviceList = new StringBuilder();
    private long mScanStartTime;

    private static final String LOG_TAG = "EMODE_BTTest";
    private boolean DBG = true;
    private final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch(msg.what) {
                case MSG_BT_ON:
                    mBtAdapter.startDiscovery();
                    mText1.setText("BT status: ON");
                    mText2.setText("BT MAC address:" + mBtAdapter.getAddress());
                    break;
                case MSG_BT_TEST_PASS:
                    //finishTestcase(Constants.TEST_PASS);
                    break;
                case MSG_BT_TEST_FAIL:
                    //finishTestcase(Constants.TEST_FAIL);
                    break;
                default:
                    break;
            }
        }

    };
    protected void log(String msg) {
       // if(DBG) Log.e(LOG_TAG, msg);
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {

                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                if (state == BluetoothAdapter.STATE_ON) {
                    mHandler.sendEmptyMessage(MSG_BT_ON);
                }
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (!mDeviceList.toString().contains(device.getAddress())) {
                    mDeviceList.append(device.getName());
                    mDeviceList.append(":");
                    mDeviceList.append(device.getAddress());
                    mDeviceList.append("\n");

                    mHandler.sendEmptyMessageDelayed(MSG_BT_TEST_PASS, 500);
                }
                mText3.setText(mDeviceList);

            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                mScanStartTime = System.currentTimeMillis();
                mText1.setText("BT status:" + "Scanning...");

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                if (mDeviceList.length() == 0) {
                    mText1.setText("Scanning is finished, didn't find the Bluetooth device!");
                    mHandler.sendEmptyMessageDelayed(MSG_BT_TEST_FAIL, 500);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.hardwaredem_cbttest);

        mText1 = (TextView)findViewById(R.id.m0_t1);
        mText2 = (TextView)findViewById(R.id.m0_t2);
        mText3 = (TextView)findViewById(R.id.m0_t3);
        mText2.setText("BT status:" + "unknown");
        //mBtnPass.setVisibility(View.GONE);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, intentFilter);

        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter == null) {
            log("BluetoothAdapter is null");
            finish();
        } else {
            if (!mBtAdapter.isEnabled()) {
                mText1.setText("BT status:" + "Off");
                mBtAdapter.enable();
            } else {
                mOrigBTState = true;
            }
            if (mBtAdapter.isEnabled()) {
                mHandler.sendEmptyMessage(MSG_BT_ON);
            }
        }
    }


    @Override
    protected void onStop(){
        super.onStop();
        if (mBtAdapter != null) {
            mBtAdapter.cancelDiscovery();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}

