package com.example.wangjun.demoapk.LayoutDemo;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangjun.demoapk.R;

public class TestLeftRightFragment_Right extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layoutdemo_testleftrightfragment_right, container, false);
        return view;
    }
}
