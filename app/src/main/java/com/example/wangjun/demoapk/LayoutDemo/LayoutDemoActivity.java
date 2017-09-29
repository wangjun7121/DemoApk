package com.example.wangjun.demoapk.LayoutDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.R;

public class LayoutDemoActivity extends AppCompatActivity {

    private static String sLogcatTAG = "LayoutDemoActivity";
    // 定义按钮
    private Button testTablayoutBtn;
    private Button testLinearlayoutBtn;
    private Button testRelativelayoutBtn;
    private Button testFramelayoutBtn;
    private Button testTablelayoutBtn;
    private Button testSimpleFramelayoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutdemo);

        testTablayoutBtn = (Button) findViewById(R.id.testTablayoutBtn);
        testTablayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, TestTabLayoutActivity.class);
                startActivity(intent);
            }
        });

        testLinearlayoutBtn = (Button) findViewById(R.id.testLinearlayoutBtn);
        testLinearlayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, TestLinearLayoutActivity.class);
                startActivity(intent);
            }
        });

        testRelativelayoutBtn = (Button) findViewById(R.id.testRelativelayoutBtn);
        testRelativelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, TestRelativeLayoutActivity.class);
                startActivity(intent);
            }
        });

        testFramelayoutBtn = (Button) findViewById(R.id.testFramelayoutBtn);
        testFramelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, TestFrameLayoutActivity.class);
                startActivity(intent);
            }
        });

        testTablelayoutBtn = (Button) findViewById(R.id.testTablelayoutBtn);
        testTablelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, TestTableLayoutActivity.class);
                startActivity(intent);
            }
        });

        testSimpleFramelayoutBtn = (Button) findViewById(R.id.testSimpleFramelayoutBtn);
        testSimpleFramelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, TestLeftRightFragmentActivity.class);
                startActivity(intent);
            }
        });

    }
}
