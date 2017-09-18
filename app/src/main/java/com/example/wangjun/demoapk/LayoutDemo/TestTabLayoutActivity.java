package com.example.wangjun.demoapk.LayoutDemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.example.wangjun.demoapk.R;

public class TestTabLayoutActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TestTabLayoutAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_layout_demo_testtablayout);
        mAdapter=new TestTabLayoutAdapter(getSupportFragmentManager());
        mViewPager= (ViewPager) findViewById(R.id.main_viewPager);
        mViewPager.setAdapter(mAdapter);
        mTabLayout= (TabLayout) findViewById(R.id.main_tab);
        //将ViewPager与TabLayout进行关联
        mTabLayout.setupWithViewPager(mViewPager);
        //设置是固定的，还可以设置为TabLayout.MODE_SCROLLABLE,
        //可滚动的，用于多个Tab
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}

