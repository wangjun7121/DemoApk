package com.example.wangjun.demoapk.ListViewDemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.SensorDemo.SensorDemoActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class ListViewDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "ListViewDemo";
    Context m_Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testArrayAdapterBtn = new Button(m_Context);
        testArrayAdapterBtn.setText("ArrayAdapter: 简单列表");
        testArrayAdapterBtn.setAllCaps(false);
        layout.addView(testArrayAdapterBtn);
        testArrayAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ListViewDemoActivity.this, CArrayAdapter.class);
                startActivity(intent);
            }
        });

        Button testSimpleCursorAdapterBtn = new Button(m_Context);
        testSimpleCursorAdapterBtn.setText("SimpleCursorAdapter: 读取联系人");
        testSimpleCursorAdapterBtn.setAllCaps(false);
        layout.addView(testSimpleCursorAdapterBtn);
        testSimpleCursorAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ListViewDemoActivity.this, CSimpleCursorAdapter.class);
                startActivity(intent);
            }
        });

        Button testSimpleAdapterBtn = new Button(m_Context);
        testSimpleAdapterBtn.setText("SimpleAdapter: 带图片的列表");
        testSimpleAdapterBtn.setAllCaps(false);
        layout.addView(testSimpleAdapterBtn);
        testSimpleAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ListViewDemoActivity.this, CSimpleAdapter.class);
                startActivity(intent);
            }
        });

        Button testMyAdapterBtn = new Button(m_Context);
        testMyAdapterBtn.setText("MyAdapter: 自定义列表");
        testMyAdapterBtn.setAllCaps(false);
        layout.addView(testMyAdapterBtn);
        testMyAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ListViewDemoActivity.this, CMyListView.class);
                startActivity(intent);
            }
        });

        Button testUIChatBtn = new Button(m_Context);
        testUIChatBtn.setText("聊天界面");
        testUIChatBtn.setAllCaps(false);
        layout.addView(testUIChatBtn);
        testUIChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(ListViewDemoActivity.this, CUIChatActivity.class);
                startActivity(intent);
            }
        });


    }




}
