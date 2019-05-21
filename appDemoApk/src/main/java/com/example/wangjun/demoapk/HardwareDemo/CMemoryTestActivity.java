package com.example.wangjun.demoapk.HardwareDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.Tools.LogUtil;
import com.example.wangjun.demoapk.Tools.RunScript;

import android.app.ActivityManager;

import android.app.ActivityManager.MemoryInfo;

import android.content.Context;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;

import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;

import android.text.format.Formatter;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;


public class CMemoryTestActivity extends AppCompatActivity {
    public String availableMemory = null;
    public TextView memory = null;
    public TextView totalMemory = null;
    private TextView tv_percent1,tv_percent2,tv_flashtotalsize;
    public Button finish = null;;
    private String percent_sd,percent_memory;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hardwaredemo_cmemorytest);
        memory = (TextView) findViewById(R.id.sd_t1);
        tv_percent1 = (TextView) findViewById(R.id.percent1);
        tv_percent2 = (TextView) findViewById(R.id.percent2);
        tv_flashtotalsize = (TextView) findViewById(R.id.flashtotalsize);
        finish = (Button) findViewById(R.id.sd_b);
        finish.setText("OK");
        finish.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }
        });

        totalMemory = (TextView) findViewById(R.id.sd_t0);

        // 通过执行 cat /proc/meminfo 获取 RAM 信息
        exeFree();

        String path = "";

        // 1. 获得非移动存储的路径

//       使用反射调用 getVolumeList（）
//                从getVolumeList源码可知，该方法为Hide方法，对外不可见，所以需要使用反射调用。
//                反射调用代码
        StorageManager mStorageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
        StorageVolume[] storageVolumes;
        try {
            // 获得 getVolumeList 隐藏方法
            Method getVolumeList = StorageManager.class.getDeclaredMethod("getVolumeList");
            storageVolumes = (StorageVolume[]) getVolumeList.invoke(mStorageManager);

            // 获得 getVolumeState 隐藏方法
            Method getVolumeState = StorageManager.class.getDeclaredMethod("getVolumeState", String.class);

            for (StorageVolume storageVolume : storageVolumes) {


                Method getPath = StorageVolume.class.getMethod("getPath");
                path = (String) getPath.invoke(storageVolume);
                String state = (String) getVolumeState.invoke(mStorageManager, path);

                String desc = storageVolume.getDescription(this);
                LogUtil.i("storageVolume name--->" + desc);
                LogUtil.i("StoragePath--->" + path);
                //这里需要用StorageManager反射调用getVolumeState函数，而不应该用StorageVolume的getState方法，因为可能会报错
                LogUtil.i("storageVolume State--->" + state);

                if (!storageVolume.isRemovable() &&
                        // Environment.MEDIA_MOUNTED.equals(mStorageManager.getVolumeState(volume.getPath()))
                        Environment.MEDIA_MOUNTED.equals(state)
                        )

                {
                    path = (String) getPath.invoke(storageVolume);
                    break;
                }


            }
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }


        // 根据路径，获取相关存储信息
        StatFs stat = new StatFs(path);
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long availableBlocks = stat.getAvailableBlocks();
        String totalSpace = Formatter.formatFileSize(this, totalBlocks * blockSize);
        String availableSpace = Formatter.formatFileSize(this, availableBlocks * blockSize);
        if(totalBlocks != 0){
            percent_sd = String.valueOf(availableBlocks * 100 / totalBlocks);
        }else {
            percent_sd = "0";
        }
        tv_percent2.setText("Internal SDCard avail percent:" + percent_sd +"%");


        TextView tvTotalSpace = (TextView) findViewById(R.id.sd_t2);
        TextView tvAvailableSpace = (TextView) findViewById(R.id.sd_t3);

        tvTotalSpace.setText("Internal SDCard total:" + totalSpace);
        tvAvailableSpace.setText("Internal SDCard avail percent:" + availableSpace);

        tv_flashtotalsize.setText("Data+Root about:"+roundStorageSize(Environment.getDataDirectory().getTotalSpace()+Environment.getRootDirectory().getTotalSpace()));
        LogUtil.i("Total Size-->" + roundStorageSize(Environment.getDataDirectory().getTotalSpace()+Environment.getRootDirectory().getTotalSpace()));

    }

    public static long roundStorageSize(long size) {
        long val = 1;
        long pow = 1;
        while ((val * pow) < size) {
            val <<= 1;
            if (val > 512) {
                val = 1;
                pow *= 1000;
            }
        }
        return val * pow;
    }

    public boolean exeFree() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo memInfo = new MemoryInfo();

        // 获取存储信息
        am.getMemoryInfo(memInfo);

        String free = Formatter.formatFileSize(this, memInfo.availMem);
        memory.setText("RAM available:"+ free);

        String strMeminfo = RunScript.runIt("cat /proc/meminfo");

        if (strMeminfo == null) {
            return false;
        }

        String[] lines = strMeminfo.split("\n");
        String[] t = lines[0].split("[\\s]+");
        percent_memory = String.valueOf(memInfo.availMem * 100/(Long.parseLong(t[1]) * 1024));
        tv_percent1.setText("RAM available percent:" +percent_memory+"%");
        totalMemory.setText("RAM total:" +
                Formatter.formatFileSize(this, Long.parseLong(t[1]) * 1024));

        return true;
    }

    public String getMemory() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();

        return Formatter.formatFileSize(this, availableBlocks * blockSize);
    }

    public String getTotalMemory() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getBlockCount();

        return Formatter.formatFileSize(this, availableBlocks * blockSize);
    }
}
