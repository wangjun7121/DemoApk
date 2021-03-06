package com.example.wangjun.demoapk.LayoutDemo;

/**
 * Created by wangjun on 2017/10/2.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.wangjun.demoapk.R;

public class CFragmentApp_NewsContentActivity extends Activity {

    // 让外部函数调用，通过 Intent 启动本 Activity
    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, CFragmentApp_NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 加载了一个 fragment: CFragmentApp_NewsContentFragment
        setContentView(R.layout.layoutdemo_cfragmentapp_newscontent);
        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");

        // 获得 fragment 调用其 refresh() 更新显示
        CFragmentApp_NewsContentFragment newsContentFragment = (CFragmentApp_NewsContentFragment) getFragmentManager()
                .findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle, newsContent);
    }

}
