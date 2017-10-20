package com.example.wangjun.demoapk.SoftwareDemo;

/**
 * Created by wangjun on 2017/10/20.
 */

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

public class CDevicePolicyManager_MyAdmin extends DeviceAdminReceiver {
    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        System.out.println("已经注册成为系统组件");
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        super.onDisabled(context, intent);
        System.out.println("已经注销了系统组件");
    }
}