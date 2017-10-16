package com.example.wangjun.demoapk.LayoutDemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangjun.demoapk.R;

public class CLeftRightFragment_Left extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layoutdemo_cleftrightfragment_left, container, false);
        return view;
    }
}