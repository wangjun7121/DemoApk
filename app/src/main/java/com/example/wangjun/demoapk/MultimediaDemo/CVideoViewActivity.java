package com.example.wangjun.demoapk.MultimediaDemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;
import java.io.File;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import android.app.Activity;

public class CVideoViewActivity extends Activity implements OnClickListener {

    private VideoView videoView;
    private Button choice;
    private Button play;
    private Button pause;
    private Button replay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimediademo_cvideoview);

        //        播放视频文件其实并不比播放音频文件复杂， 主要是使用 VideoView 类来实现的。 这个
        //        类将视频的显示和控制集于一身，使得我们仅仅借助它就可以完成一个简易的视频播放器。
        //        VideoView 的用法和 MediaPlayer 也比较类似，主要有以下常用方法：
        //            setVideoPath()          设置要播放的视频文件的位置。
        //            start()                 开始或继续播放视频。
        //            pause()                 暂停播放视频。
        //            resume()                将视频重头开始播放。
        //            seekTo()                从指定的位置开始播放视频。
        //            isPlaying()             判断当前是否正在播放视频。
        //            getDuration()           获取载入的视频文件的时长。

        choice = (Button) findViewById(R.id.choice);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        replay = (Button) findViewById(R.id.replay);
        videoView = (VideoView) findViewById(R.id.video_view);

        choice.setOnClickListener(this);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);


        //initVideoPath();
    }

    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choice:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                //intent.setType("image/*");//选择图片
                //intent.setType("audio/*"); //选择音频
                intent.setType("video/*"); //选择视频 （mp4 3gp 是android支持的视频格式）
                //intent.setType("video/*;image/*");//同时选择视频和图片
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,1);
                break;
            case R.id.play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.replay:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                Log.v("WJWind", uri.getPath().toString()); // URI = Wind: content://com.android.providers.media.documents/document/audio%3A10430
                Toast.makeText(this, "文件路径："+uri.getPath().toString(), Toast.LENGTH_SHORT).show();


                videoView.setVideoURI(uri);



            }
        }
    }

}
