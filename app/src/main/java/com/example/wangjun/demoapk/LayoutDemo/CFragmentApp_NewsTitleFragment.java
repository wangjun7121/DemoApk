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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews();
        adapter = new CFragmentApp_NewsAdapter(activity, R.layout.layoutdemo_cfragmentapp_newsitem, newsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.layoutdewmo_cfragmentapp_newstitlefrag, container, false);
        newsTitleListView = (ListView) view
                .findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        CFragmentApp_News news = newsList.get(position);
        if (isTwoPane) {
            CFragmentApp_NewsContentFragment newsContentFragment = (CFragmentApp_NewsContentFragment) getFragmentManager()
                    .findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(), news.getContent());
        } else {
            CFragmentApp_NewsContentActivity.actionStart(getActivity(), news.getTitle(),
                    news.getContent());
        }
    }

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

}