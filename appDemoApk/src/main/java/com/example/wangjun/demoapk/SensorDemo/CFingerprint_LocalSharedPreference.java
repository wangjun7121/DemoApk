package com.example.wangjun.demoapk.SensorDemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

//        Android 中主要提供了三种方法用于得到 SharedPreferences 对象
//            1. Context 类中的 getSharedPreferences()方法
//            2. Activity 类中的 getPreferences()方法
//            3. PreferenceManager 类中的 getDefaultSharedPreferences()方法


//        操作步骤：
//          1. 调用 SharedPreferences 对象的 edit()方法来获取一个 SharedPreferences.Editor 对象。
//          2. 向 SharedPreferences.Editor 对象中添加数据，比如添加一个布尔型数据就使用
//              putBoolean 方法，添加一个字符串则使用 putString()方法，以此类推。
//          3. 调用 commit()方法将添加的数据提交，从而完成数据存储操作。

public class CFingerprint_LocalSharedPreference {

    final String dataKeyName = "data";
    final String IVKeyName = "IV";
    private SharedPreferences preferences;

    CFingerprint_LocalSharedPreference(Context context) {
        preferences = context.getSharedPreferences("sample", Activity.MODE_PRIVATE);
    }

    String getData(String keyName) {
        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        return preferences.getString(keyName, "");
    }

    boolean storeData(String key, String data) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, data);
        return editor.commit();
    }

    boolean containsKey(String key) {
        return !TextUtils.isEmpty(getData(key));
    }
}
