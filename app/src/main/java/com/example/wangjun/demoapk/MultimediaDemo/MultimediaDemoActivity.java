package com.example.wangjun.demoapk.MultimediaDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.R;

public class MultimediaDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "ActivityDemoActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificationdemo);


        Button notificationIntroduceBtn;
        notificationIntroduceBtn = (Button) findViewById(R.id.notificationIntroduceBtn);
        notificationIntroduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CNotificationIntroduceActivity.class);
                startActivity(intent);
            }
        });


        Button testNotificationBtn;
        testNotificationBtn = (Button) findViewById(R.id.testNotificationBtn);
        testNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CNotificationActivity.class);
                startActivity(intent);
            }
        });



        Button widthNotificationIntroduceBtn;
        widthNotificationIntroduceBtn = (Button) findViewById(R.id.widthNotificationIntroduceBtn);
        widthNotificationIntroduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CWidthNotificationIntroduceActivity.class);
                startActivity(intent);
            }
        });


        Button widthNotificationBtn;
        widthNotificationBtn = (Button) findViewById(R.id.widthNotificationBtn);
        widthNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CWidthNotificationActivity.class);
                startActivity(intent);
            }
        });

        Button testNotificationProgressBtn;
        testNotificationProgressBtn = (Button) findViewById(R.id.testNotificationProgressBtn);
        testNotificationProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CNotificationProgressActivity.class);
                startActivity(intent);
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // SMS: 短信相关
        Button testReceiveSMSBtn;
        testReceiveSMSBtn = (Button) findViewById(R.id.testReceiveSMSBtn);
        testReceiveSMSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CReceiveSendSMSActivity.class);
                startActivity(intent);
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Camera: 摄像头相关
        Button testCameraBtn;
        testCameraBtn = (Button) findViewById(R.id.testCameraBtn);
        testCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CTakePhotoActivity.class);
                startActivity(intent);
            }
        });


    }
}
