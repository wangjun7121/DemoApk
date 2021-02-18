package com.example.wangjun.demoapk.DataPersistenceDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.BroadcastsDemo.BroadcastsDemoActivity;
import com.example.wangjun.demoapk.BroadcastsDemo.CBroadcastsIntroduceActivity;
import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.SensorDemo.CAlsActivity;
import com.example.wangjun.demoapk.SensorDemo.SensorDemoActivity;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class DataPersistenceDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "DataPersistenceDemo";
    Context m_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button testReadWriteFileBtn = new Button(m_Context);
        testReadWriteFileBtn.setText("读写文件测试");
        testReadWriteFileBtn.setAllCaps(false);
        layout.addView(testReadWriteFileBtn);
        testReadWriteFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(DataPersistenceDemoActivity.this, CReadWriteFileActivity.class);
                startActivity(intent);
            }
        });

        Button testSharedPreferenceBtn = new Button(m_Context);
        testSharedPreferenceBtn.setText("SharedPreference 读写测试");
        testSharedPreferenceBtn.setAllCaps(false);
        layout.addView(testSharedPreferenceBtn);
        testSharedPreferenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(DataPersistenceDemoActivity.this, CSharedPreferencesActivity.class);
                startActivity(intent);
            }
        });

        Button testDatabaseBtn = new Button(m_Context);
        testDatabaseBtn.setText("Database 操作");
        testDatabaseBtn.setAllCaps(false);
        layout.addView(testDatabaseBtn);
        testDatabaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(DataPersistenceDemoActivity.this, COperateDatabaseActivity.class);
                startActivity(intent);
            }
        });

    }
}
