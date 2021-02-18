package com.example.wangjun.demoapk.LocationDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.ActivityDemo.ActivityDemoActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLaunchModeActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLifeCycleActivity;
import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.SensorDemo.SensorDemoActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class LocationDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "LocationDemo";
    Context m_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testLocationManagerBtn = new Button(m_Context);
        testLocationManagerBtn.setText("LocationManager 测试");
        testLocationManagerBtn.setAllCaps(false);
        layout.addView(testLocationManagerBtn);
        testLocationManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(LocationDemoActivity.this, CLocationManagerActivity.class);
                startActivity(intent);
            }
        });


    }
}
