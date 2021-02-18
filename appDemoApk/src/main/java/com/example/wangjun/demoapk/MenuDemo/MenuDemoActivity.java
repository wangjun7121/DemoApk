package com.example.wangjun.demoapk.MenuDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.SensorDemo.SensorDemoActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class MenuDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "MenuDemoActivity";
    Context m_Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testMenuBtn = new Button(m_Context);
        testMenuBtn.setText("简单菜单显示");
        testMenuBtn.setAllCaps(false);
        layout.addView(testMenuBtn);
        testMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MenuDemoActivity.this, CMenuActivity.class);
                startActivity(intent);
            }
        });


    }

}
