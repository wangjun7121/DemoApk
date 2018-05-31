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
        setContentView(R.layout.multimediademo);


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

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Audio: 音频相关
        Button testMediaPlayBtn;
        testMediaPlayBtn = (Button) findViewById(R.id.testMediaPlayBtn);
        testMediaPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CMediaPlayActivity.class);
                startActivity(intent);
            }
        });

        Button testAutioTrackBtn;
        testAutioTrackBtn = (Button) findViewById(R.id.testAutioTrackBtn);
        testAutioTrackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testAutioTrackBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CAudioTrackRecordActivity.class);
                startActivity(intent);
            }
        });

        Button testAutioEffectBtn;
        testAutioEffectBtn = (Button) findViewById(R.id.testAutioEffectBtn);
        testAutioEffectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testAutioTrackBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CAudioEffectTestActivity.class);
                startActivity(intent);
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Explore: 文件浏览器
        Button testExploreBtn;
        testExploreBtn = (Button) findViewById(R.id.testExploreBtn);
        testExploreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CExploerActivity.class);
                startActivity(intent);
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Video: 视频相关
        Button testVideoViewBtn;
        testVideoViewBtn = (Button) findViewById(R.id.testVideoViewBtn);
        testVideoViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(MultimediaDemoActivity.this, CVideoViewActivity.class);
                startActivity(intent);
            }
        });

    }
}
