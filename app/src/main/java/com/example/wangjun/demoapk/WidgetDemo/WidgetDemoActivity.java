package com.example.wangjun.demoapk.WidgetDemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wangjun.demoapk.ActivityDemo.ActivityDemoActivity;
import com.example.wangjun.demoapk.ActivityDemo.TestActivityLifeCycleActivity;
import com.example.wangjun.demoapk.R;


public class WidgetDemoActivity extends AppCompatActivity {

    private static String sLogcatTAG = "ActivityDemoActivity";
    // 定义按钮
    private Button m_testWidgetBtn;
    private Button m_dynamicAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_demo);

        m_testWidgetBtn = (Button) findViewById(R.id.m_testWidgetBtn);
        m_testWidgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, TestWidgetActivity.class);
                startActivity(intent);
            }
        });

        m_dynamicAddBtn = (Button) findViewById(R.id.m_dynamicAddBtn);
        m_dynamicAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, TestDynamidAddButtonActivity.class);
                startActivity(intent);
            }
        });


    }





}