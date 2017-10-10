package com.example.wangjun.demoapk.BroadcastsDemo;

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

public class BroadcastsDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "ActivityDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcastsdemo);


        Button tempBroadcastsIntroduceBtn;
        tempBroadcastsIntroduceBtn = (Button) findViewById(R.id.tempBroadcastsIntroduceBtn);
        tempBroadcastsIntroduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(BroadcastsDemoActivity.this, CBroadcastsIntroduceActivity.class);
                startActivity(intent);
            }
        });

        Button testRegisterReceiverBtn;
        testRegisterReceiverBtn = (Button) findViewById(R.id.testRegisterReceiverBtn);
        testRegisterReceiverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(BroadcastsDemoActivity.this, CRegisterReceiverActivity.class);
                startActivity(intent);
            }
        });

        Button testSendBroadcastBtn;
        testSendBroadcastBtn = (Button) findViewById(R.id.testSendBroadcastBtn);
        testSendBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(BroadcastsDemoActivity.this, CSendBroadcastActivity.class);
                startActivity(intent);
            }
        });


    }
}
