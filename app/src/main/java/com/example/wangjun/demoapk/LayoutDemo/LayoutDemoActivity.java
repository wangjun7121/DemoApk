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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutdemo);

        Button testTablayoutBtn;
        testTablayoutBtn = (Button) findViewById(R.id.testTablayoutBtn);
        testTablayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, CTabLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testLinearlayoutBtn;
        testLinearlayoutBtn = (Button) findViewById(R.id.testLinearlayoutBtn);
        testLinearlayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, CLinearLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testRelativelayoutBtn;
        testRelativelayoutBtn = (Button) findViewById(R.id.testRelativelayoutBtn);
        testRelativelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, CRelativeLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testFramelayoutBtn;
        testFramelayoutBtn = (Button) findViewById(R.id.testFramelayoutBtn);
        testFramelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, CFrameLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testTablelayoutBtn;
        testTablelayoutBtn = (Button) findViewById(R.id.testTablelayoutBtn);
        testTablelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, CTableLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testSimpleFramelayoutBtn;
        testSimpleFramelayoutBtn = (Button) findViewById(R.id.testSimpleFramelayoutBtn);
        testSimpleFramelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, CLeftRightFragmentActivity.class);
                startActivity(intent);
            }
        });

        Button testFragmentLifeCycleBtn;
        testFragmentLifeCycleBtn = (Button) findViewById(R.id.testFragmentLifeCycleBtn);
        testFragmentLifeCycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, CFragmentLifeCycleActivity.class);
                startActivity(intent);
            }
        });

        Button testMultiLayoutBtn;
        testMultiLayoutBtn = (Button) findViewById(R.id.testMultiLayoutBtn);
        testMultiLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, CMultiLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testFragmentAppBtn;
        testFragmentAppBtn = (Button) findViewById(R.id.testFragmentAppBtn);
        testFragmentAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, CMultiLayoutActivity.class);
                startActivity(intent);
            }
        });
    }
}
