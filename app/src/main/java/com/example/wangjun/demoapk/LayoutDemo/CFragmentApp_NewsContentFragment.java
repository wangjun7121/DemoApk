package com.example.wangjun.demoapk.LayoutDemo;

/**
 * Created by wangjun on 2017/10/2.
 */

import android.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

import com.example.wangjun.demoapk.R;

public class CFragmentApp_NewsContentFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layoutdemo_cfragmentapp_newscontentfrag, container, false);
        return view;
    }

//    refresh()方法， 这个方法就是用于将新闻的标题和内容显
//    示在界面上的。 可以看到， 这里通过 findViewById()方法分别获取到新闻的标题和内容控件，
//    然后将方法传递进来的参数设置进去
    public void refresh(String newsTitle, String newsContent) {
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newsTitleText = (TextView) view.findViewById(R.id.news_title);
        TextView newsContentText = (TextView) view.findViewById(R.id.news_content);
        newsTitleText.setText(newsTitle);
        newsContentText.setText(newsContent);
    }

}