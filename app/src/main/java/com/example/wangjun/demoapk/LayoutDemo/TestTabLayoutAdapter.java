package com.example.wangjun.demoapk.LayoutDemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by wangjun on 2017/9/18.
 */


public class TestTabLayoutAdapter extends FragmentPagerAdapter {
    private int mCount=3;
    private int[] mColors=new int[]{android.R.color.holo_orange_dark,android.R.color.holo_green_dark,android.R.color.holo_red_dark};
    public TestTabLayoutAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return  TestTabLayoutFragment.newInstance(position,mColors[position]);
    }
    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page"+(position+1);
    }
}
