package com.example.wangjun.demoapk.WidgetDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.wangjun.demoapk.R;

public class TestWidgetSizeActivity extends AppCompatActivity {

    String source = "这只是一个测试，测试<u>下划线</u>、<i>斜体字</i>、<font color='red'>红色字</font>的格式";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_demo_testwidgetsize);
        TextView contentTV = (TextView) findViewById(R.id.textView);
        contentTV.setMovementMethod(ScrollingMovementMethod.getInstance());

        String strText = this.getString(R.string.WidgetDemo_TestWidgetSize_text);
        contentTV.setText(Html.fromHtml(strText));
        //contentTV.contentTV(String.valueOf(R.string.WidgetDemo_TestWidgetSize_text)));
        //contentTV.setText(Html.fromHtml(source));

        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        TextView tempTextView = (TextView) findViewById(R.id.textView1);
        tempTextView.setText("xdpi = "+xdpi+"  ydpi = "+ydpi);

    }
}
