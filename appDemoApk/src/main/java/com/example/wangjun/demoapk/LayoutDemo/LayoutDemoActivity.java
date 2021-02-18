package com.example.wangjun.demoapk.LayoutDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.SensorDemo.SensorDemoActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class LayoutDemoActivity extends AppCompatActivity {

    private static String sLogcatTAG = "LayoutDemoActivity";
    Context m_Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testTablayoutBtn = new Button(m_Context);
        testTablayoutBtn.setText("TabLayout 测试");
        testTablayoutBtn.setAllCaps(false);
        layout.addView(testTablayoutBtn);
        testTablayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(LayoutDemoActivity.this, CTabLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testLinearlayoutBtn = new Button(m_Context);
        testLinearlayoutBtn.setText("LinearLayout 测试");
        testLinearlayoutBtn.setAllCaps(false);
        layout.addView(testLinearlayoutBtn);
        testLinearlayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(LayoutDemoActivity.this, CLinearLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testRelativelayoutBtn = new Button(m_Context);
        testRelativelayoutBtn.setText("RelativeLayout 测试");
        testRelativelayoutBtn.setAllCaps(false);
        layout.addView(testRelativelayoutBtn);
        testRelativelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(LayoutDemoActivity.this, CRelativeLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testFramelayoutBtn = new Button(m_Context);
        testFramelayoutBtn.setText("FrameLayout 测试");
        testFramelayoutBtn.setAllCaps(false);
        layout.addView(testFramelayoutBtn);
        testFramelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(LayoutDemoActivity.this, CFrameLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testTablelayoutBtn = new Button(m_Context);
        testTablelayoutBtn.setText("TableLayout 测试");
        testTablelayoutBtn.setAllCaps(false);
        layout.addView(testTablelayoutBtn);
        testTablelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(LayoutDemoActivity.this, CTableLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testSimpleFramelayoutBtn = new Button(m_Context);
        testSimpleFramelayoutBtn.setText("Fragment 测试");
        testSimpleFramelayoutBtn.setAllCaps(false);
        layout.addView(testSimpleFramelayoutBtn);
        testSimpleFramelayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(LayoutDemoActivity.this, CLeftRightFragmentActivity.class);
                startActivity(intent);
            }
        });

        Button testFragmentLifeCycleBtn = new Button(m_Context);
        testFragmentLifeCycleBtn.setText("Fragment 生成周期(点击放大)");
        testFragmentLifeCycleBtn.setAllCaps(false);
        layout.addView(testFragmentLifeCycleBtn);
        testFragmentLifeCycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(LayoutDemoActivity.this, CFragmentLifeCycleActivity.class);
                startActivity(intent);
            }
        });

        Button testMultiLayoutBtn = new Button(m_Context);
        testMultiLayoutBtn.setText("平板/手机布局兼容");
        testMultiLayoutBtn.setAllCaps(false);
        layout.addView(testMultiLayoutBtn);
        testMultiLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(LayoutDemoActivity.this, CMultiLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button testFragmentAppBtn = new Button(m_Context);
        testFragmentAppBtn.setText("Fragment: 新闻应用");
        testFragmentAppBtn.setAllCaps(false);
        layout.addView(testFragmentAppBtn);
        testFragmentAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(LayoutDemoActivity.this, CFragmentAppActivity.class);
                startActivity(intent);
            }
        });


    }
}
