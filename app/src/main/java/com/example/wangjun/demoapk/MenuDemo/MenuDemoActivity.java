package com.example.wangjun.demoapk.MenuDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.SensorDemoActivity;
import com.example.wangjun.demoapk.SensorDemo.TestAccelActivity;
import com.example.wangjun.demoapk.SensorDemo.TestAlsActivity;

public class MenuDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "MenuDemoActivity";
    // 定义按钮
    private Button testMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_demo);


        testMenuBtn = (Button) findViewById(R.id.testMenuBtn);
        testMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                // Intent intent = new Intent("android.intent.action.mylistview");
                Intent intent = new Intent(MenuDemoActivity.this, TestMenuActivity.class);
                startActivity(intent);
            }
        });
    }

}
