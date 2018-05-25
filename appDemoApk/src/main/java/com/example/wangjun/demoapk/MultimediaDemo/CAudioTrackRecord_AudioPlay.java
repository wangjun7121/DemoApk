package com.example.wangjun.demoapk.MultimediaDemo;

/**
 * Created by wangjun on 2018/5/24.
 */

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.util.Log;

import java.util.LinkedList;

/**
 * @project SampleAndroid.workspace
 * @class AudioPlay
 * @description
 * @author yxmsw2007
 * @version
 * @email yxmsw2007@gmail.com
 * @data 2015-7-6 下午10:04:49
 */
public class CAudioTrackRecord_AudioPlay {

    public final static int PCM16_FRAME_SIZE = 320;
    public final static int DELAY_FRAMES = 50 * 10;


    //STREAM_ALARM：警告声
    //STREAM_MUSCI：音乐声，例如music等
    //STREAM_RING：铃声
    //STREAM_SYSTEM：系统声音
    //STREAM_VOCIE_CALL：电话声音

    public final static int STREAM_TYPE = AudioManager.STREAM_MUSIC;            // speaker 喇叭
    //public final static int STREAM_TYPE = AudioManager.STREAM_VOICE_CALL;   // receiver 听筒
    public final static int SAMPLE_RATE_IN_HZ = 8000;
    public final static int CHANNEL_CONFIG = AudioFormat.CHANNEL_OUT_MONO;
    public final static int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;

    private static final String TAG = CAudioTrackRecord_AudioPlay.class.getSimpleName();
    private LinkedList<byte[]> mAudioBuffer = new LinkedList<byte[]>();
    private boolean mIsRunning;

    public CAudioTrackRecord_AudioPlay() {
        mIsRunning = false;
    }

    private class PlayThread extends Thread {

        public PlayThread() {
            super("PlayThread");
        }

        public void run() {

            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);

            Log.d(TAG, "wind Start AudioTrack");

            int minBufferSize = android.media.AudioTrack.getMinBufferSize(SAMPLE_RATE_IN_HZ, CHANNEL_CONFIG, AUDIO_FORMAT);
            AudioTrack audioTrack = null;
            // 有极小的概率会初始化失败
            while (mIsRunning
                    && (audioTrack == null
                    || (audioTrack.getState() != AudioRecord.STATE_INITIALIZED))) {
                if (audioTrack != null) {
                    audioTrack.release();
                }
                audioTrack = new AudioTrack(STREAM_TYPE,
                        SAMPLE_RATE_IN_HZ, CHANNEL_CONFIG, AUDIO_FORMAT, minBufferSize, AudioTrack.MODE_STREAM);
                yield();
            }
            audioTrack.setStereoVolume(AudioTrack.getMaxVolume(), AudioTrack.getMaxVolume());
            audioTrack.play();
            while (mIsRunning) {
                synchronized (mAudioBuffer) {
                    if (!mAudioBuffer.isEmpty()) {
                        byte[] data = mAudioBuffer.remove(0);
                        audioTrack.write(data, 0, data.length);
                    }
                }
            }
            audioTrack.stop();
            audioTrack.release();

            Log.d(TAG, "wind Stop AudioTrack");
        }
    };

    public void startPlay() {
        if (mIsRunning) {
            return;
        }
        mIsRunning = true;
        synchronized (mAudioBuffer) {
            mAudioBuffer.clear();
        }
        PlayThread playThread = new PlayThread();
        playThread.start();
    }

    // 停止播放
    public void stopPlay() {
        mIsRunning = false;
    }

    public void playAudio(byte[] data) {
        if (!mIsRunning) {
            return;
        }
        synchronized (mAudioBuffer){
            mAudioBuffer.add(data);
        }
    }

    public boolean isPlaying() {
        return mIsRunning;
    }

}
