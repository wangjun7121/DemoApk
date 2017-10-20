package com.example.wangjun.demoapk.Tools;

import android.app.Activity;
import android.content.Context;
import android.os.PowerManager;

/**
 * Created by wangjun on 2017/10/20.
 */


//方法讲解:
//    　　acquireWakeLock()：为获取电源锁,即唤醒系统。
//    　　releaseWakeLock()：为释放电源锁(要在执行完毕后及时释放电源锁,否则会比较耗电)

public class WakeLockUtil
{
    public static PowerManager.WakeLock wakeLock = null;

    public static void acquireWakeLock(Activity myActivity)
    {
        if (null == wakeLock)
        {
            PowerManager pm = (PowerManager) myActivity.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.ON_AFTER_RELEASE | PowerManager.PARTIAL_WAKE_LOCK, "wakeLockUtil");
            // PARTIAL_WAKE_LOCK:保持CPU 运转，屏幕和键盘灯有可能是关闭的 -- 最常用,保持CPU运转
            // SCREEN_DIM_WAKE_LOCK：保持CPU 运转，允许保持屏幕显示但有可能是灰的，允许关闭键盘灯
            // SCREEN_BRIGHT_WAKE_LOCK：保持CPU 运转，允许保持屏幕高亮显示，允许关闭键盘灯
            // FULL_WAKE_LOCK：保持CPU 运转，保持屏幕高亮显示，键盘灯也保持亮度
            // ACQUIRE_CAUSES_WAKEUP：强制使屏幕亮起，这种锁主要针对一些必须通知用户的操作.
            // ON_AFTER_RELEASE：当锁被释放时，保持屏幕亮起一段时间
            if (null != wakeLock)
            {
                wakeLock.acquire(); // 立即获取电源锁
                // wakeLock.acquire(2000); // 2秒后获取电源锁
            }
        }
    }

    public static void releaseWakeLock(Activity myActivity)
    {
        if (null != wakeLock)
        {
            wakeLock.release();
            wakeLock = null;
        }
    }
}
