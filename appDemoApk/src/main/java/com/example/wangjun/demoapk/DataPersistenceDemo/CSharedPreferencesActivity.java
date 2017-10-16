package com.example.wangjun.demoapk.DataPersistenceDemo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.R;

public class CSharedPreferencesActivity extends AppCompatActivity {

    private Button saveData;

    private Button restoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datapersistencedemo_cshared_preferences);

        saveData = (Button) findViewById(R.id.save_data);
        restoreData = (Button) findViewById(R.id.restore_data);

//        Android 中主要提供了三种方法用于得到 SharedPreferences 对象
//            1. Context 类中的 getSharedPreferences()方法
//            2. Activity 类中的 getPreferences()方法
//            3. PreferenceManager 类中的 getDefaultSharedPreferences()方法


//        操作步骤：
//          1. 调用 SharedPreferences 对象的 edit()方法来获取一个 SharedPreferences.Editor 对象。
//          2. 向 SharedPreferences.Editor 对象中添加数据，比如添加一个布尔型数据就使用
//              putBoolean 方法，添加一个字符串则使用 putString()方法，以此类推。
//          3. 调用 commit()方法将添加的数据提交，从而完成数据存储操作。


        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.commit();
            }
        });
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String name = pref.getString("name", "");
                int age = pref.getInt("age", 0);
                boolean married = pref.getBoolean("married", false);
                Log.d("CSharedPreferences", "name is " + name);
                Log.d("CSharedPreferences", "age is " + age);
                Log.d("CSharedPreferences", "married is " + married);
            }
        });

    }
}
