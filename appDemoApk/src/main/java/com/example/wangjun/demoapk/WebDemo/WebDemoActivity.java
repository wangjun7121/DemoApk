package com.example.wangjun.demoapk.WebDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.HardwareDemo.HardwareDemoActivity;
import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class WebDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "WebDemo";
    Context m_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);




        Button testWebViewBtn = new Button(m_Context);
        testWebViewBtn.setText("WebView 测试");
        layout.addView(testWebViewBtn);
        testWebViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WebDemoActivity.this, CWebViewActivity.class);
                startActivity(intent);
            }
        });

        Button testHttpURLConnBtn = new Button(m_Context);
        testHttpURLConnBtn.setText("HttpURLConnection 应用");
        layout.addView(testHttpURLConnBtn);
        testHttpURLConnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WebDemoActivity.this, CHttpURLConnectionTestActivity.class);
                startActivity(intent);
            }
        });

        Button testHttpClientBtn = new Button(m_Context);
        testHttpClientBtn.setText("HttpClient 应用");
        layout.addView(testHttpClientBtn);
        testHttpClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WebDemoActivity.this, CHttpClientActivity.class);
                startActivity(intent);
            }
        });

        Button testXMLAnalysisBtn = new Button(m_Context);
        testXMLAnalysisBtn.setText("XML 解析：Dom/Sax/Pull");
        layout.addView(testXMLAnalysisBtn);
        testXMLAnalysisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WebDemoActivity.this, CXMLAnalysisActivity.class);
                startActivity(intent);
            }
        });

        Button testJSONObjectBtn = new Button(m_Context);
        testJSONObjectBtn.setText("JSON 解析：JSONObject");
        layout.addView(testJSONObjectBtn);
        testJSONObjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WebDemoActivity.this, CJSONObjectActivity.class);
                startActivity(intent);
            }
        });

        Button testGSONBtn = new Button(m_Context);
        testGSONBtn.setText("JSON 解析：GSON");
        layout.addView(testGSONBtn);
        testGSONBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WebDemoActivity.this, CGSONActivity.class);
                startActivity(intent);
            }
        });

        Button testSocketBtn = new Button(m_Context);
        testSocketBtn.setText("Socket 测试");
        layout.addView(testSocketBtn);
        testSocketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(WebDemoActivity.this, CSocketCSSwActivity.class);
                startActivity(intent);
            }
        });

    }
}
