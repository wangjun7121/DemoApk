package com.example.wangjun.demoapk.WidgetDemo;

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


public class WidgetDemoActivity extends AppCompatActivity {

    private static String sLogcatTAG = "ActivityDemoActivity";
    Context m_Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testWidgetBtn = new Button(m_Context);
        testWidgetBtn.setText("常用控件测试");
        layout.addView(testWidgetBtn);
        testWidgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(WidgetDemoActivity.this, CWidgetActivity.class);
                startActivity(intent);
            }
        });

        Button dynamicAddBtn = new Button(m_Context);
        dynamicAddBtn.setText("动态添加 Button 测试");
        layout.addView(dynamicAddBtn);
        dynamicAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(WidgetDemoActivity.this, CDynamidAddButtonActivity.class);
                startActivity(intent);
            }
        });

        Button widgetInheritBtn = new Button(m_Context);
        widgetInheritBtn.setText("Widget 继承关系图");
        layout.addView(widgetInheritBtn);
        widgetInheritBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(WidgetDemoActivity.this, CWidgetInheritActivity.class);
                startActivity(intent);
            }
        });

        Button customWidgetBtn = new Button(m_Context);
        customWidgetBtn.setText("自定义 Widget 测试");
        layout.addView(customWidgetBtn);
        customWidgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(WidgetDemoActivity.this, CCustomWidgetActivity.class);
                startActivity(intent);
            }
        });

        Button testWidgetSizeBtn = new Button(m_Context);
        testWidgetSizeBtn.setText("控件尺寸：dp/sp");
        layout.addView(testWidgetSizeBtn);
        testWidgetSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(WidgetDemoActivity.this, CWidgetSizeActivity.class);
                startActivity(intent);
            }
        });

        Button testTextViewBtn = new Button(m_Context);
        testTextViewBtn.setText("TextView 格式化");
        layout.addView(testTextViewBtn);
        testTextViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(WidgetDemoActivity.this, CTextViewActivity.class);
                startActivity(intent);
            }
        });



    }





}