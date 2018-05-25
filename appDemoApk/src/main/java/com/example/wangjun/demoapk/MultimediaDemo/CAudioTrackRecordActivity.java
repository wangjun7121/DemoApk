package com.example.wangjun.demoapk.MultimediaDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.util.LinkedList;
import com.example.wangjun.demoapk.R;


// 在同一个机器上边录边播可能会有啸叫,所以本程序是先将录制的声音放在一个buffer中,录完后再播

public class CAudioTrackRecordActivity extends Activity implements CAudioTrackRecord_IAudioRecCallback, OnClickListener {

    private static final String TAG = CAudioTrackRecord_AudioRec.class.getSimpleName();

    // 用来缓存录音数据的
    private LinkedList<byte[]> mAudioBuffer = new LinkedList<byte[]>();

    private CAudioTrackRecord_AudioPlay mAudioPlay = new CAudioTrackRecord_AudioPlay();

    private CAudioTrackRecord_AudioRec mAudioRec = new CAudioTrackRecord_AudioRec(this);

    private Button audio_act_record;
    private Button audio_act_play;
    private Button audio_act_read_buffer;
    private TextView audio_act_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimediademo_caudiotrackrecord);
        audio_act_record = (Button) findViewById(R.id.audio_act_record);
        audio_act_play = (Button) findViewById(R.id.audio_act_play);
        audio_act_read_buffer = (Button) findViewById(R.id.audio_act_read_buffer);
        audio_act_info = (TextView) findViewById(R.id.audio_act_info);

        audio_act_record.setOnClickListener(this);
        audio_act_play.setOnClickListener(this);
        audio_act_read_buffer.setOnClickListener(this);

        audio_act_info.setText("1. 录音线程开关(保存录音到主线程)\n" +
                                    "2. 播放线程开关\n" +
                                    "3. 打开播放线程，传递录音数据\n");
    }

    @Override
    // 回调函数，用于从 Record 录音机处保存录音数据
    public void read(byte[] data) {
        Log.d(TAG, "" + data.length);
        mAudioBuffer.add(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.audio_act_record:
                if (mAudioRec.isRecording()) {
                    mAudioRec.stopRecord();
                } else {
                    // 启动录音线程进行音频录音
                    mAudioRec.startRecord();
                }
                break;
            case R.id.audio_act_play:
                if (mAudioPlay.isPlaying()) {
                    mAudioPlay.stopPlay();
                } else {
                    // 启动播放线程进行音频播放
                    mAudioPlay.startPlay();
                }
                break;
            case R.id.audio_act_read_buffer:

                // 启动播放线程
                if (!mAudioPlay.isPlaying()) {
                    mAudioPlay.startPlay();
                }
                // 将录音数据传递给播放线程
                readBuffer();
                break;

            default:
                break;
        }
        updateView();
    }

    private void updateView() {
        audio_act_record.setText(mAudioRec.isRecording() ? "Record(On)" : "Record(Off)");
        audio_act_play.setText(mAudioPlay.isPlaying() ? "Play(On)" : "Play(Off)");
    }

    public void readBuffer() {
        for (int i = 0; i < mAudioBuffer.size(); i++) {
            mAudioPlay.playAudio(mAudioBuffer.remove(0));
        }
    }

}