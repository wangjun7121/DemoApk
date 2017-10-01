package com.example.wangjun.demoapk.WidgetDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.wangjun.demoapk.R;

public class CCustomWidgetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.widgetdemo_ccustomwidget);
    }
}
