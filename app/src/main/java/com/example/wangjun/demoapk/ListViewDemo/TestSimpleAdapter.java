package com.example.wangjun.demoapk.ListViewDemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.example.wangjun.demoapk.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjun on 2017/9/15.
 */
public class TestSimpleAdapter extends ListActivity {


    // private List<String> data = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 列表项使用新布局的用法，在 getData() 中填充各个布局
        SimpleAdapter adapter = new SimpleAdapter(this,getData(), R.layout.list_view_demo_testsimpleadapter,
                new String[]{"title","info","img"},         // HashMap 文件 ID
                new int[]{R.id.title,R.id.info,R.id.img});  // 映射到布局文件对应的位置
        setListAdapter(adapter);


    }

    // 列表中表项数据的函数
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "G1");
        map.put("info", "google 1");
        map.put("img", R.drawable.listviewdemo_i1);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G2");
        map.put("info", "google 2");
        map.put("img", R.drawable.listviewdemo_i2);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("img", R.drawable.listviewdemo_i3);
        list.add(map);

        return list;
    }
}