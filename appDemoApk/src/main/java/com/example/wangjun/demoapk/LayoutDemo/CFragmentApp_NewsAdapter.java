package com.example.wangjun.demoapk.LayoutDemo;

/**
 * Created by wangjun on 2017/10/2.
 */
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wangjun.demoapk.R;

public class CFragmentApp_NewsAdapter extends ArrayAdapter<CFragmentApp_News> {

    private int resourceId;

    public CFragmentApp_NewsAdapter(Context context, int textViewResourceId, List<CFragmentApp_News> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CFragmentApp_News news = getItem(position);
        View view;

        // 获得列表项布局，如果获得过则使用保存的
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }

        // 进行显示更新
        TextView newsTitleText = (TextView) view.findViewById(R.id.news_title);
        newsTitleText.setText(news.getTitle());
        return view;
    }

}