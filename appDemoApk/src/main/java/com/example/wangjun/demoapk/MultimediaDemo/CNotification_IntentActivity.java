package com.example.wangjun.demoapk.MultimediaDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.NotificationManager;
import android.widget.Toast;

import com.example.wangjun.demoapk.R;

public class CNotification_IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimediademo_cnotificationintent);

        //通过获取MainActivity中设置的putExtra获取土司内容
        Toast.makeText(this, getIntent().getStringExtra("info"), Toast.LENGTH_SHORT).show();

        // 关闭通知
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
    }
}
