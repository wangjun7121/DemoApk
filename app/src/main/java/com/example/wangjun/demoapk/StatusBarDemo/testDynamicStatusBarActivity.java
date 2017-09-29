package com.example.wangjun.demoapk.StatusBarDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangjun.demoapk.R;


public class testDynamicStatusBarActivity extends Activity implements View.OnClickListener {

    private LinearLayout m_Main;
    private TextView m_Textview1;
    private TextView m_Textview2;
    private Button m_Button1;
    private Button m_Button2;
    private Button m_Button3;
    private Button m_Button4;
    private Button m_Button5;
    private Button m_Button6;
    private Button m_Button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.statusbardemo_testdynamicstatusbar);
        assignViews();


        setOnClicks();
    }

    private void setOnClicks() {
        m_Button1.setOnClickListener(this);
        m_Button2.setOnClickListener(this);
        m_Button3.setOnClickListener(this);
        m_Button4.setOnClickListener(this);
        m_Button5.setOnClickListener(this);
        m_Button6.setOnClickListener(this);
        m_Button7.setOnClickListener(this);
    }



    private void assignViews() {
        m_Main = (LinearLayout) findViewById(R.id.main);
        m_Textview1 = (TextView) findViewById(R.id.textView1);
        m_Textview2 = (TextView) findViewById(R.id.textView2);
        m_Button1 = (Button) findViewById(R.id.button1);
        m_Button2 = (Button) findViewById(R.id.button2);
        m_Button3 = (Button) findViewById(R.id.button3);
        m_Button4 = (Button) findViewById(R.id.button4);
        m_Button5 = (Button) findViewById(R.id.button5);
        m_Button6 = (Button) findViewById(R.id.button6);
        m_Button7 = (Button) findViewById(R.id.button7);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                //Activity全屏显示，且状态栏被隐藏覆盖掉
                m_Main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                m_Textview1.setText("Activity全屏显示，且状态栏被隐藏覆盖掉\nView.SYSTEM_UI_FLAG_FULLSCREEN");
                m_Textview2.setText("Activity全屏显示，且状态栏被隐藏覆盖掉\nView.SYSTEM_UI_FLAG_FULLSCREEN");
                break;

            case R.id.button2:
                m_Main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                m_Textview1.setText("恢复到有状态的正常情况\nView.SYSTEM_UI_FLAG_VISIBLE");
                m_Textview2.setText("恢复到有状态的正常情况\nView.SYSTEM_UI_FLAG_VISIBLE");
                break;
            case R.id.button3:
                m_Main.setSystemUiVisibility(View.INVISIBLE);
                m_Textview1.setText("//隐藏状态栏，同时Activity会伸展全屏显示\nView.INVISIBLE");
                m_Textview2.setText("//隐藏状态栏，同时Activity会伸展全屏显示\nView.INVISIBLE");
                break;
            case R.id.button4:
                m_Main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                m_Textview1.setText("Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮\nView" +
                        ".SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN \n View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION");
                m_Textview2.setText("Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮\nView" +
                        ".SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN \n View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION");
                break;
            case R.id.button5:
                m_Main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                m_Textview1.setText("Activity全屏显示，状态栏透明\nView.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION");
                m_Textview2.setText("Activity全屏显示，状态栏透明\nView.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION");
                break;
            case R.id.button6:
                m_Main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                m_Textview1.setText("隐藏虚拟按键\nView.SYSTEM_UI_FLAG_HIDE_NAVIGATION");
                m_Textview2.setText("隐藏虚拟按键\nView.SYSTEM_UI_FLAG_HIDE_NAVIGATION");
                break;
            case R.id.button7:
                m_Main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                m_Textview1.setText("状态栏低能显示，有一些会被隐藏\nView.SYSTEM_UI_FLAG_LOW_PROFILE");
                m_Textview2.setText("状态栏低能显示，有一些会被隐藏\nView.SYSTEM_UI_FLAG_LOW_PROFILE");
                break;

            default:
                break;
        }
    }
}
