package com.example.wangjun.demoapk;

import android.app.Application;
import android.content.Context;

/**
 * Created by wangjun on 2017/10/16.
 */


//一般获取context的方法
//
//    1. Activity.this 的 context
//        (一般用法)返回当前 activity 的上下文，属于 activity ，activity 摧毁他就摧毁
//    2. getApplicationContext()
//        返回应用的上下文，生命周期是整个应用，应用摧毁它才摧毁
//    3. getBaseContext()
//        返回由构造函数指定或 setBaseContext() 设置的上下文
//    4. getActivity()
//        多用于 fragment 中


public class AppContext extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}