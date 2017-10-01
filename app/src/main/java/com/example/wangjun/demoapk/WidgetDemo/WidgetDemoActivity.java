package com.example.wangjun.demoapk.WidgetDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.R;


public class WidgetDemoActivity extends AppCompatActivity {

    private static String sLogcatTAG = "ActivityDemoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widgetdemo);

        Button m_testWidgetBtn;
        m_testWidgetBtn = (Button) findViewById(R.id.m_testWidgetBtn);
        m_testWidgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, CWidgetActivity.class);
                startActivity(intent);
            }
        });

        Button m_dynamicAddBtn;
        m_dynamicAddBtn = (Button) findViewById(R.id.m_dynamicAddBtn);
        m_dynamicAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, CDynamidAddButtonActivity.class);
                startActivity(intent);
            }
        });

        Button m_widgetInheritBtn;
        m_widgetInheritBtn = (Button) findViewById(R.id.m_widgetInheritBtn);
        m_widgetInheritBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, CWidgetInheritActivity.class);
                startActivity(intent);
            }
        });

        Button m_customWidgetBtn;
        m_customWidgetBtn = (Button) findViewById(R.id.m_customWidgetBtn);
        m_customWidgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, CCustomWidgetActivity.class);
                startActivity(intent);
            }
        });

        Button m_testWidgetSizeBtn;
        m_testWidgetSizeBtn = (Button) findViewById(R.id.m_testWidgetSizeBtn);
        m_testWidgetSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, CWidgetSizeActivity.class);
                startActivity(intent);
            }
        });

        Button m_testTextViewBtn;
        m_testTextViewBtn = (Button) findViewById(R.id.m_testTextViewBtn);
        m_testTextViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WidgetDemoActivity.this, CTextViewActivity.class);
                startActivity(intent);
            }
        });

    }





}