package com.example.wangjun.demoapk.ServiceDemo;

/**
 * Created by wangjun on 2017/10/12.
 */
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CServiceTest_MyIntentService extends IntentService {

    public CServiceTest_MyIntentService() {
        super("MyIntentService");
    }

//    首先是要提供一个无参的构造函数，并且必须在其内部调用父类的有参构造函数。
//    然后要在子类中去实现 onHandleIntent()这个抽象方法， 在这个方法中可以去处理一些具体的
//        逻辑，而且不用担心 ANR 的问题，因为这个方法已经是在子线程中运行的了。这里为了证
//        实一下， 我们在 onHandleIntent()方法中打印了当前线程的 id。另外根据 IntentService 的特性，
//    这个服务在运行结束后应该是会自动停止的， 所以我们又重写了 onDestroy()方法， 在这里也
//    打印了一行日志，以证实服务是不是停止掉了。


    @Override
    protected void onHandleIntent(Intent intent) {
        // 打印当前线程的id
        Log.d("MyIntentService", "Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy executed");
    }

}