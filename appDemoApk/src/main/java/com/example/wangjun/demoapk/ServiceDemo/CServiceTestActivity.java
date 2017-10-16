package com.example.wangjun.demoapk.ServiceDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CServiceTestActivity extends Activity implements OnClickListener {

    private Button startService;

    private Button stopService;

    private Button bindService;

    private Button unbindService;

    private Button startIntentService;

    private CServiceTest_MyService.DownloadBinder downloadBinder;

    // 用于与 Service 进行通信时使用：
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
//            onServiceDisconnected: 覆写，解除活动与服务绑定时调用


        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            onServiceConnected: 覆写，活动与服务绑定成功时调用，调用 Binder 继承类后台方法

            // 【获得了服务中定义的 Binder 类，调用其公有函数进行通信】
            downloadBinder = (CServiceTest_MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicedemo_cservicetest);
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        startIntentService = (Button) findViewById(R.id.start_intent_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                // 启动服务
                Intent startIntent = new Intent(this, CServiceTest_MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                // 停止服务
                //       MyService 的任何一个位置调用 stopSelf()方法就能让这个服务停止下来了。
                Intent stopIntent = new Intent(this, CServiceTest_MyService.class);
                stopService(stopIntent);
                break;

            case R.id.bind_service:
                // 绑定服务进行通信操作
                Intent bindIntent = new Intent(this, CServiceTest_MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                // 解绑服务
                unbindService(connection);
                break;
            case R.id.start_intent_service:
                // IntendService 服务：在子线程中执行，会自动停止的服务
                Log.d("MainActivity", "Thread id is " + Thread.currentThread().getId());
                Intent intentService = new Intent(this, CServiceTest_MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }

}