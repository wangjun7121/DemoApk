package com.example.wangjun.demoapk.WebDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.ActivityDemo.ActivityDemoActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLaunchModeActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLifeCycleActivity;
import com.example.wangjun.demoapk.R;

public class WebDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "WebDemo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webdemo);

        Button testWebViewBtn;
        testWebViewBtn = (Button) findViewById(R.id.testWebViewBtn);
        testWebViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WebDemoActivity.this, CWebViewActivity.class);
                startActivity(intent);
            }
        });

        Button testHttpURLConnBtn;
        testHttpURLConnBtn = (Button) findViewById(R.id.testHttpURLConnBtn);
        testHttpURLConnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WebDemoActivity.this, CHttpURLConnectionTestActivity.class);
                startActivity(intent);
            }
        });

        Button testHttpClientBtn;
        testHttpClientBtn = (Button) findViewById(R.id.testHttpClientBtn);
        testHttpClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WebDemoActivity.this, CHttpClientActivity.class);
                startActivity(intent);
            }
        });


    }
}
