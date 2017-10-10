package com.example.wangjun.demoapk.LayoutDemo;

/**
 * Created by wangjun on 2017/10/2.
 */


import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.wangjun.demoapk.R;

public class CFragmentApp_NewsTitleFragment extends Fragment implements OnItemClickListener {

    private ListView newsTitleListView;

    private List<CFragmentApp_News> newsList;

    private CFragmentApp_NewsAdapter adapter;

    private boolean isTwoPane;

    @Override
    // 碎片与活动建立关联时调用
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews();
        // 初始化列表项，获得列表项布局: 只有一个 TextView 布局
        adapter = new CFragmentApp_NewsAdapter(activity, R.layout.layoutdemo_cfragmentapp_newsitem, newsList);
    }

    @Override
    // 为碎片创建视图(加载布局)时调用
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // 获得 ListView 布局，设置其适配器为自定义的，设置其点击回调函数
        View view = inflater.inflate(R.layout.layoutdewmo_cfragmentapp_newstitlefrag, container, false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    // 确保与碎片相关联的海选一定已经创建完毕时调用
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    @Override
    // ListView 列表项点击函数
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CFragmentApp_News news = newsList.get(position);
        if (isTwoPane) {
            CFragmentApp_NewsContentFragment newsContentFragment = (CFragmentApp_NewsContentFragment) getFragmentManager()
                    .findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(), news.getContent());
        } else {
        // 针对小屏手机：单击列表项时，通过 Intent 启动一个新的 Activity
            CFragmentApp_NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
        }
    }

    // 手动添加列表项中的项，返回一个 List
    private List<CFragmentApp_News> getNews() {
        List<CFragmentApp_News> newsList = new ArrayList<CFragmentApp_News>();
        CFragmentApp_News news1 = new CFragmentApp_News();
        news1.setTitle("Succeed in College as a Learning Disabled Student");
        news1.setContent("College freshmen will soon learn to live with a roommate, adjust to a new social scene and survive less-than-stellar dining hall food. Students with learning disabilities will face these transitions while also grappling with a few more hurdles.");
        newsList.add(news1);
        CFragmentApp_News news2 = new CFragmentApp_News();
        news2.setTitle("Google Android exec poached by China's Xiaomi");
        news2.setContent("China's Xiaomi has poached a key Google executive involved in the tech giant's Android phones, in a move seen as a coup for the rapidly growing Chinese smartphone maker.");
        newsList.add(news2);
        return newsList;
    }

    @Override
    // 当与碎片关联的视图被移除时调用
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    // 当碎片与活动解除关联的时候调用
    public void onDetach() {
        super.onDetach();
    }
}