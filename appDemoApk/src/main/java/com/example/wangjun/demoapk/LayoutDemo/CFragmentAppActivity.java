package com.example.wangjun.demoapk.LayoutDemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.wangjun.demoapk.R;

public class CFragmentAppActivity extends Activity {

//    实现流程：
//        有两个 fragment ，第一个用于显示一个列表，显示 listview
//     第二个用于显示新闻内容。


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layoutdemo_cfragmentapp);
    }
}
