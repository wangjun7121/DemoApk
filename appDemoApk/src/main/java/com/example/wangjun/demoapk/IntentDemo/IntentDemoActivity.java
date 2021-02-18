package com.example.wangjun.demoapk.IntentDemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.SensorDemo.SensorDemoActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;


public class IntentDemoActivity extends AppCompatActivity {

    private static String sLogcatTAG = "IntentDemoActivity";
    Context m_Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testImplicitIntentBtn = new Button(m_Context);
        testImplicitIntentBtn.setText("隐式 Intent 跳转");
        testImplicitIntentBtn.setAllCaps(false);
        layout.addView(testImplicitIntentBtn);
        testImplicitIntentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent("com.example.activitytest.ACTION_START");
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivity(intent);
            }
        });

        Button testIntetBrowserBtn = new Button(m_Context);
        testIntetBrowserBtn.setText("Intent 监听跳转浏览器");
        testIntetBrowserBtn.setAllCaps(false);
        layout.addView(testIntetBrowserBtn);
        testIntetBrowserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        Button testIntentCallBtn = new Button(m_Context);
        testIntentCallBtn.setText("Intent 拨号: 10086");
        testIntentCallBtn.setAllCaps(false);
        layout.addView(testIntentCallBtn);
        testIntentCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        Button testIntentArgsBtn = new Button(m_Context);
        testIntentArgsBtn.setText("Intent 传参与回传");
        testIntentArgsBtn.setAllCaps(false);
        layout.addView(testIntentArgsBtn);
        testIntentArgsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                String data = "IntentDemo =args=> IntArgsActivity";
                Intent intent = new Intent(IntentDemoActivity.this, CIntentArgsActivity.class);
                intent.putExtra("extra_data", data);
                startActivityForResult(intent,1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FirstActivity", returnedData);
                    Toast.makeText(IntentDemoActivity.this, returnedData,
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}