package com.example.wangjun.demoapk;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wangjun.demoapk.Tools.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {


    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }
    */

    // listview 列表项名称:
    //      注：数组中的名称与类名有一定关系： 类名 = 类名文件夹.数组项名+Activity
    private String[] saDemo = {
            "ActivityDemo",
            "BroadcastsDemo",
            "ContentDemo",
            "DataPersistenceDemo",
            "HardwareDemo",
            "HelloWorld",
            "IntentDemo",
            "ListViewDemo",
            "LayoutDemo",
            "LocationDemo",
            "MenuDemo",
            "MultimediaDemo",
            "SensorDemo",
            "ServiceDemo",
            "SoftwareDemo",
            "StatusBarDemo",
            "WebDemo",
            "WidgetDemo"
    };

    //危险权限分组名称 动态申请权限必须在 manifest 配置文件里声明
     String[] saNeedPermissionList = {
//            //日程表
//            Manifest.permission.READ_CALENDAR,
//            Manifest.permission.WRITE_CALENDAR,
            //联系人
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
            //位置
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            //麦克风
            Manifest.permission.RECORD_AUDIO,
            //电话
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.ADD_VOICEMAIL,
            Manifest.permission.USE_SIP,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            //传感器
            Manifest.permission.BODY_SENSORS,
            //短信
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS,
            //储存
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,

            //相机
            Manifest.permission.CAMERA

    };
    List<String> mPermissionList = new ArrayList<>();

    private void expressItemClick(int position) throws ClassNotFoundException {
        Class<?> clTargetActivity;
        String sTargetActivity;

        sTargetActivity = "com.example.wangjun.demoapk." + saDemo[position] + "." + saDemo[position] + "Activity";
        LogUtil.d("debug\n");

        // 使用反射从类路径获得类 class 对象
        clTargetActivity = Class.forName(sTargetActivity);
        //Intent intent = new Intent(MainActivity.this,HelloWorldActivity.class);
        Intent intent = new Intent(MainActivity.this, (Class) clTargetActivity);
        startActivity(intent);
        //finish();//看你需不需要返回当前界面，如果点返回需要返回到当前界面，就不用这个
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏状态栏
        setContentView(R.layout.main);

        // 初始化一个 Addapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1, saDemo);

        // 获得 xml 中的 list_view 资源
        ListView listView = (ListView) findViewById(R.id.list_view);

        // 设置 listView 的 Adapter
        listView.setAdapter(adapter);

        // 设置 ListView 点击事件响应
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "Current Item = "+id,Toast.LENGTH_SHORT).show();
                switch (parent.getId()) {
                    case R.id.list_view:
                        try {
                            expressItemClick(position);//position 代表你点的哪一个
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                }


            }
        });


        // 动态权限申请
        if (Build.VERSION.SDK_INT>=23)       {

            /**
             * 判断哪些权限未授予
             */
            mPermissionList.clear();
            for (int i = 0; i < saNeedPermissionList.length; i++) {
                if (ContextCompat.checkSelfPermission(this, saNeedPermissionList[i]) != PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(saNeedPermissionList[i]);
                }
            }


            /**
             * 判断是否为空
             */
            if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了

            } else {//请求权限方法
                String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
                ActivityCompat.requestPermissions(this, permissions, 123);
            }

        }
        else{
            //低于23 不需要特殊处理，去掉用拍照的方法
        }

        acquireWakeLock();

    }


    //参数 requestCode是我们在申请权限的时候使用的唯一的申请码
    //String[] permission则是权限列表，一般用不到
    //int[] grantResults 是用户的操作响应，包含这权限是够请求成功
    //由于在权限申请的时候，我们就申请了一个权限，所以此处的数组的长度都是1
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 123:
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        //判断是否勾选禁止后不再询问
                        boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                        if (showRequestPermission) {//
                            //重新申请权限
                            return;
                        } else {
                            //已经禁止
                        }
                    }
                }
                break;
            default:
                break;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized(this) {
            releaseWakeLock();
        }
    }

    //    @Override
//    protected void onPause() {
//        super.onPause();
//        synchronized(this) {
//            releaseWakeLock();
//
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //获取锁，保持屏幕亮度
//        acquireWakeLock();
//    }
}