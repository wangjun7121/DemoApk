package com.example.wangjun.demoapk.MultimediaDemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.Tools.LogUtil;
import com.example.wangjun.demoapk.WidgetDemo.CWidgetActivity;
import com.example.wangjun.demoapk.WidgetDemo.WidgetDemoActivity;

public class MultimediaDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "ActivityDemoActivity";
    Context m_Context;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_entry);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);


        Button notificationIntroduceBtn = new Button(m_Context);
        notificationIntroduceBtn.setText("Notification: 标准视图介绍");
        notificationIntroduceBtn.setAllCaps(false);
        layout.addView(notificationIntroduceBtn);
        notificationIntroduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CNotificationIntroduceActivity.class);
                startActivity(intent);
            }
        });


        Button testNotificationBtn = new Button(m_Context);
        testNotificationBtn.setText("Notification: 标准视图应用");
        testNotificationBtn.setAllCaps(false);
        layout.addView(testNotificationBtn);
        testNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CNotificationActivity.class);
                startActivity(intent);
            }
        });


        Button widthNotificationIntroduceBtn = new Button(m_Context);
        widthNotificationIntroduceBtn.setText("Notification: 宽视图介绍");
        widthNotificationIntroduceBtn.setAllCaps(false);
        layout.addView(widthNotificationIntroduceBtn);
        widthNotificationIntroduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CWidthNotificationIntroduceActivity.class);
                startActivity(intent);
            }
        });



        Button widthNotificationBtn = new Button(m_Context);
        widthNotificationBtn.setText("Notification: 宽视图应用");
        widthNotificationBtn.setAllCaps(false);
        layout.addView(widthNotificationBtn);
        widthNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CWidthNotificationActivity.class);
                startActivity(intent);
            }
        });


        Button testNotificationProgressBtn = new Button(m_Context);
        testNotificationProgressBtn.setText("Notification: 下载进度条");
        testNotificationProgressBtn.setAllCaps(false);
        layout.addView(testNotificationProgressBtn);
        testNotificationProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CNotificationProgressActivity.class);
                startActivity(intent);
            }
        });



        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // SMS: 短信相关
        Button testReceiveSMSBtn = new Button(m_Context);
        testReceiveSMSBtn.setText("SMS：收/发短信");
        testReceiveSMSBtn.setAllCaps(false);
        layout.addView(testReceiveSMSBtn);
        testReceiveSMSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CReceiveSendSMSActivity.class);
                startActivity(intent);
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Camera: 摄像头相关
        Button testCameraBtn = new Button(m_Context);
        testCameraBtn.setText("Camera: 系统 Camera 使用");
        testCameraBtn.setAllCaps(false);
        layout.addView(testCameraBtn);
        testCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CTakePhotoActivity.class);
                startActivity(intent);
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Video: 视频相关
        Button testMediaPlayIndroduceBtn = new Button(m_Context);
        testMediaPlayIndroduceBtn.setText("Video: MediaPlay 介绍");
        testMediaPlayIndroduceBtn.setAllCaps(false);
        layout.addView(testMediaPlayIndroduceBtn);
        testMediaPlayIndroduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CMediaPlayIndroduceActivity.class);
                startActivity(intent);
            }
        });

        Button testMediaPlayBtn = new Button(m_Context);
        testMediaPlayBtn.setText("Video: MediaPlay 播放音频");
        testMediaPlayBtn.setAllCaps(false);
        layout.addView(testMediaPlayBtn);
        testMediaPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CMediaPlayActivity.class);
                startActivity(intent);
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Audio: 音频相关



        Button testAutioTrackBtn = new Button(m_Context);
        testAutioTrackBtn.setText("Audio: AudioTrack/AudioRecord 测试");
        testAutioTrackBtn.setAllCaps(false);
        layout.addView(testAutioTrackBtn);
        testAutioTrackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CAudioTrackRecordActivity.class);
                startActivity(intent);
            }
        });


        Button testAutioEffectBtn = new Button(m_Context);
        testAutioEffectBtn.setText("AudioEffect 测试");
        testAutioEffectBtn.setAllCaps(false);
        layout.addView(testAutioEffectBtn);
        testAutioEffectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CAudioEffectTestActivity.class);
                startActivity(intent);
            }
        });



        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Explore: 文件浏览器

        Button testExploreBtn = new Button(m_Context);
        testExploreBtn.setText("Expore: 文件浏览器");
        testExploreBtn.setAllCaps(false);
        layout.addView(testExploreBtn);
        testExploreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CExploerActivity.class);
                startActivity(intent);
            }
        });


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Video: 视频相关
        Button testVideoViewBtn = new Button(m_Context);
        testVideoViewBtn.setText("VideoView: 播放视频");
        testVideoViewBtn.setAllCaps(false);
        layout.addView(testVideoViewBtn);
        testVideoViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CVideoViewActivity.class);
                startActivity(intent);
            }
        });

        Button testTextureViewBtn = new Button(m_Context);
        testTextureViewBtn.setText("TextureView: 预览 Camera");
        testTextureViewBtn.setAllCaps(false);
        layout.addView(testTextureViewBtn);
        testTextureViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("debug\n");
                Intent intent = new Intent(MultimediaDemoActivity.this, CTextureViewCameraActivity.class);
                startActivity(intent);
            }
        });


    }
}
