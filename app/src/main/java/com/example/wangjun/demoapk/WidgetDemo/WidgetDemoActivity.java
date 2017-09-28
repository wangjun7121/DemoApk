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
    private Button m_widgetInheritBtn;
    private Button m_customWidgetBtn;
    private Button m_testWidgetSizeBtn;
    private Button m_testTextViewBtn;

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

        m_widgetInheritBtn = (Button) findViewById(R.id.m_widgetInheritBtn);
        m_widgetInheritBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, TestWidgetInheritActivity.class);
                startActivity(intent);
            }
        });

        m_customWidgetBtn = (Button) findViewById(R.id.m_customWidgetBtn);
        m_customWidgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, TestCustomWidgetActivity.class);
                startActivity(intent);
            }
        });

        m_testWidgetSizeBtn = (Button) findViewById(R.id.m_testWidgetSizeBtn);
        m_testWidgetSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, TestWidgetSizeActivity.class);
                startActivity(intent);
            }
        });

        m_testTextViewBtn = (Button) findViewById(R.id.m_testTextViewBtn);
        m_testTextViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, TestTextViewActivity.class);
                startActivity(intent);
            }
        });

    }





}