package com.example.wangjun.demoapk.ListViewDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.wangjun.demoapk.R;

public class ListViewDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "ListViewDemo";


    private Button testArrayAdapterBtn;
    private Button testSimpleCursorAdapterBtn;
    private Button testSimpleAdapterBtn;
    private Button testMyAdapterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_demo);
        findBtnViews();
        setBtnListeners();
    }
    private void findBtnViews() {
        // 查找对应按钮
        testArrayAdapterBtn = (Button) findViewById(R.id.mylistview_btn);
        testSimpleCursorAdapterBtn = (Button) findViewById(R.id.simpleCursorAdapter_btn);
        testSimpleAdapterBtn = (Button) findViewById(R.id.simpleAdapter_btn);
        testMyAdapterBtn = (Button) findViewById(R.id.myAdapter_btn);
    }

    private void setBtnListeners() {
        // 设置按键监听函数
        testArrayAdapterBtn.setOnClickListener(myListViewBtnListener);
        testSimpleCursorAdapterBtn.setOnClickListener(simpleCursorAdapterBtnListener);
        testSimpleAdapterBtn.setOnClickListener(simpleAdapterBtnListener);
        testMyAdapterBtn.setOnClickListener(myAdapterBtnListener);
    }

    // 按键处理函数：
    private View.OnClickListener myListViewBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v(sLogcatTAG, "testArrayAdapterBtn");
            // Intent intent = new Intent("android.intent.action.mylistview");
            Intent intent = new Intent(ListViewDemoActivity.this, TestArrayAdapter.class);
            startActivity(intent);

        }

    };
    private View.OnClickListener simpleCursorAdapterBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v(sLogcatTAG, "testSimpleCursorAdapterBtn");
            Intent intent = new Intent(ListViewDemoActivity.this, TestSimpleCursorAdapter.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener simpleAdapterBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v(sLogcatTAG, "testSimpleAdapterBtn");
            Intent intent = new Intent(ListViewDemoActivity.this, TestSimpleAdapter.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener myAdapterBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v(sLogcatTAG, "myAdapter");
            Intent intent = new Intent(ListViewDemoActivity.this, TestMyListView.class);
            startActivity(intent);
        }
    };






}
