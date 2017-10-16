package com.example.wangjun.demoapk.ActivityDemo;



import com.example.wangjun.demoapk.R;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class CActivityLifeCycleNorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activitydemo_cactivitylifecyclenor);
    }

}
