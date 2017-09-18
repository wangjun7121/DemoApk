package com.example.wangjun.demoapk.LayoutDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.R;

public class LayoutDemoActivity extends AppCompatActivity {

    private static String sLogcatTAG = "LayoutDemoActivity";
    // 定义按钮
    private Button testTablayoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_demo);

        testTablayoutBtn = (Button) findViewById(R.id.testTablayoutBtn);
        testTablayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(LayoutDemoActivity.this, TestTabLayoutActivity.class);
                startActivity(intent);
            }
        });
    }
}
