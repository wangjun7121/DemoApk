package com.example.wangjun.demoapk.DataPersistenceDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.BroadcastsDemo.BroadcastsDemoActivity;
import com.example.wangjun.demoapk.BroadcastsDemo.CBroadcastsIntroduceActivity;
import com.example.wangjun.demoapk.R;

public class DataPersistenceDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "DataPersistenceDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datapersistencedemo);

        Button testReadWriteFileBtn;
        testReadWriteFileBtn = (Button) findViewById(R.id.testReadWriteFileBtn);
        testReadWriteFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(DataPersistenceDemoActivity.this, CReadWriteFileActivity.class);
                startActivity(intent);
            }
        });

        Button testSharedPreferenceBtn;
        testSharedPreferenceBtn = (Button) findViewById(R.id.testSharedPreferenceBtn);
        testSharedPreferenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(DataPersistenceDemoActivity.this, CSharedPreferencesActivity.class);
                startActivity(intent);
            }
        });
    }
}
