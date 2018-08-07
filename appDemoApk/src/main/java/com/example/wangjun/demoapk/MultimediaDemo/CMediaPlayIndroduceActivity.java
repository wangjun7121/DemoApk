package com.example.wangjun.demoapk.MultimediaDemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.Tools.LogUtil;

public class CMediaPlayIndroduceActivity extends AppCompatActivity {
    Context m_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimediademo_cmediaplayindroduce);
        m_Context = this;
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.linearlayout);
        layout.setOrientation(LinearLayout.VERTICAL);

        ImageView tmpImageView = new ImageView(m_Context);
        tmpImageView.setImageResource(R.drawable.multimediademo_mediaplay_lifecycle);
        tmpImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        layout.addView(tmpImageView);


    }
}
