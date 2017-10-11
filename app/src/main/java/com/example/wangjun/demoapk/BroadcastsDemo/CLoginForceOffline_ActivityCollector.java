package com.example.wangjun.demoapk.BroadcastsDemo;

/**
 * Created by wangjun on 2017/10/11.
 */
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class CLoginForceOffline_ActivityCollector {

    private static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}