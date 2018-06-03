package com.example.wangjun.demoapk;

import android.app.Application;
import android.content.Context;

import com.example.wangjun.demoapk.Tools.LogUtil;

/**
 * Created by wangjun on 2017/10/16.
 */


//一般获取 context 的方法
//
//    1. Activity.this 的 context
//        (一般用法)返回当前 activity 的上下文，属于 activity ，activity 摧毁他就摧毁
//    2. getApplicationContext()
//        返回应用的上下文，生命周期是整个应用，应用摧毁它才摧毁
//    3. getBaseContext()
//        返回由构造函数指定或 setBaseContext() 设置的上下文
//    4. getActivity()
//        多用于 fragment 中

// 此类是 Acitvity 的上一级：
    // Application
    //      Activity1
    //      ...
    //      Activityn

//
//Android各种获取Context方法
//
//        1. getApplicationContext() ：
//            这个函数返回的这个 Application 的上下文，所以是与 app 挂钩的，所以在整个生命周期里面都是不变的，这个好理解，
//            但是使用的时候要注意，该 context 是和引用的生命周期一致的，所以和 activity 生命周期挂钩的任务不要使用该 context，
//            比如网络访问，防止内存泄露
//
//        2. getBasecontext()：
//
//            stackoverflow 上面写的是，这个函数不应该被使用，用 Context 代替，而 Context 是与 activity 相关连，所以当 activity 死亡后
//            可能会被 destroyed,我举个我自己写的例子
//
//        public Dialog  displayDialog(int choice)
//        {
//            switch(choice){
//            case 0:
//                　　AlertDialog aDialog = new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher)
//                　　                                              .setTitle("Hello World") .setPositiveButton("OK",
//                                new DialogInterface.OnClickListener()
//                                {
//
//                                　　　　　　@Override
//                        　　　　　　public void onClick(DialogInterface arg0, int arg1)
//                                    {
//                                    　　Toast.makeText(getBaseContext(), "OK clicked", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//            }
//        }
//        　　
//
//            这个例子中的 getBaseContext() 就不能被 this 代替，因为上面的 this 返回的是这个 activity 的 context，
//            而在这个onClick函数中如果使用 this 的话，则返回的是这个 AlertDialog 的 context，所以要使用的是当
//            前 activity 名.this 去使用，比如当前 activity 为 TestActivity，那么在里面就是用 TestActivity.this 即可
//
//        3. getApplication():
//
//            getApplication 只能被 Activity 和 Services 使用，虽然在现在的 Android 的实现中，getApplication 和 getApplicationContext
//            返回一样的对象，但也不能保证这两个函数一样（例如在特殊的提供者来说），所以如果你想得到你在 Manifest 文件里面注册的 App class，
//            你不要去调用 getApplicationContext，以为你可能得不到你所要的app实例（你显然有测试框架的经验）。。。。
//
//            翻译完成，一目了然（哪里翻译错误，请指出，水B一只），原文：
//
//            getApplication() is available to Activity and Services only. Although in current Android Activity and Service implementations,
//            getApplication() and getApplicationContext() return the same object, there is no guarantee that this will always be the
//            case (for example, in a specific vendor implementation). So if you want the Application class you registered in the
//            Manifest, you should never call getApplicationContext() and cast it to your application, because it may not be the application
//            instance (which you obviously experienced with the test framework).
//
//        4. getParent() ：
//
//            返回 activity 的上下文，如果这个子视图的话，换句话说，就是当在子视图里面调用的话就返回一个带有子视图的 activity 对象，一目了然。。。
//
//        5.getActivity():
//
//            在 fragment 中使用，返回该 fragment 所依附的 activity 上下文
//
//        6.this
//
//            记住 Activity，Service 类，Application 类是继承自 Context 类的，所以在有的时候需要上下文，只需要使用 this 关键字即可，
//            但是有的时候再线程里面，this 关键字的意义就改变了，但这个时候如果需要上下文，则需要使用 类名.this，这样就可以了
//
//        这里有点注意的：
//
//            做项目时遇见的，提一下吧，动态注册广播，在调用 registerBroadcast 函数的时候，需要传入一个上下文和 broadcastReceiver，
//            查看源码可以知道，存储的时候 context 是作为一个 key 的作用，所以使用同一个 context 来注册同一个广播，onreceive 只会
//            调用一次，但是如果使用不同的 context，则会调用多次，虽然不调用 unregisterBroadcast 有时也没事，不会报错，但是一定不要
//            忘记取消注销
//
//        后续：为了简化 context 的使用方法，现在有这么一种方法，就是在 Application 类里面维护一个弱引用：
//
//            /** 用来保存当前该Application的context */
//            private static Context instance;
//            /** 用来保存最新打开页面的context */
//            private volatile static WeakReference<Context> instanceRef = null;
//
//
//        再写一个方法，
//            public static Context getInstance(){
//                    if (instanceRef == null || instanceRef.get() == null){
//                        synchronized (RootApplication.class) {
//                            if (instanceRef == null || instanceRef.get() == null) {
//                                Context context = ActivityManager.getInstance().getActivity();
//                                if (context != null)
//                                    instanceRef = new WeakReference<>(context);
//                                else {
//                                    instanceRef = new WeakReference<>(instance);
//                                    L.w("请确保RootActivity调用setInstanceRef方法");
//                                }
//                            }
//                         }
//                    }
//                    return instanceRef.get()
//            }
//　　
//
//            最后在应用的Activity基类中（这个应该有的吧）加上两个语句：
//
//                public void onCreate(Bundle savedInstanceState) {
//                    super.onCreate(savedInstanceState);
//                    RootApplication.setInstanceRef(this);
//                }
//                protected void onResume() {
//                    super.onResume();
//                    //也要在 onresume 函数里面进行设置，保证弱引用一直引用当前的可见页面
//                    RootApplication.setInstanceRef(this);
//                }
//                    　　
//
//        这样每次调用 application 的 getInstance() 方法一定能够返回一个 context，而且是当前唯一可见 activity 的 context，其他地
//        方就可以直接使用了，不用到处传递 context，再此处统一维护即可，
//


public class AppContext extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LogUtil.d("Hello World\n");
    }

    public static Context getContext() {
        return context;
    }
}