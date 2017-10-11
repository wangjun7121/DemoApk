package com.example.wangjun.demoapk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private static String sLogcatTAG = "DemoApk";
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
            "HelloWorld",
            "IntentDemo",
            "ListViewDemo",
            "LayoutDemo",
            "MenuDemo",
            "SensorDemo",
            "StatusBarDemo",
            "WidgetDemo"
    };


    private void expressItemClick(int position) throws ClassNotFoundException {
        Class<?> clTargetActivity ;
        String sTargetActivity ;

        sTargetActivity = "com.example.wangjun.demoapk."+saDemo[position]+"."+saDemo[position]+"Activity";
        Log.d(sLogcatTAG,sTargetActivity);

        // 使用反射从类路径获得类 class 对象
        clTargetActivity = Class.forName(sTargetActivity);
        //Intent intent = new Intent(MainActivity.this,HelloWorldActivity.class);
        Intent intent = new Intent(MainActivity.this,(Class)clTargetActivity);
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //Toast.makeText(MainActivity.this, "Current Item = "+id,Toast.LENGTH_SHORT).show();
                switch (parent.getId())
                {
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

    }




}
