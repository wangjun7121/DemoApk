package com.example.wangjun.demoapk.MultimediaDemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CMediaPlayActivity extends Activity implements OnClickListener {

    private Button choice;
    private Button play;
    private Button pause;
    private Button stop;

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimediademo_cmediaplay);

        choice = (Button) findViewById(R.id.choice);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);

        choice.setOnClickListener(this);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        context=getBaseContext();
        //        在 Android 中播放音频文件一般都是使用 MediaPlayer 类来实现的，它对多种格式的音
        //        频文件提供了非常全面的控制方法，从而使得播放音乐的工作变得十分简单。下表列出了
        //        MediaPlayer 类中一些较为常用的控制方法
        //            setDataSource()     设置要播放的音频文件的位置。
        //            prepare()           在开始播放之前调用这个方法完成准备工作。
        //            start()             开始或继续播放音频。
        //            pause()             暂停播放音频。
        //            reset()             将 MediaPlayer 对象重置到刚刚创建的状态。
        //            seekTo()            从指定的位置开始播放音频。
        //            stop()              停止播放音频。 调用这个方法后的 MediaPlayer 对象无法再播放音频。
        //            release()           释放掉与 MediaPlayer 对象相关的资源。
        //            isPlaying()         判断当前 MediaPlayer 是否正在播放音频。
        //            getDuration()       获取载入的音频文件的时长

//        1）如何获得MediaPlayer实例：
//            可以使用直接new的方式：
//            MediaPlayer mp = new MediaPlayer();
//            也可以使用create的方式，如：
//            MediaPlayer mp = MediaPlayer.create(this, R.raw.test);//这时就不用调用setDataSource了
//
//
//
//        2) 如何设置要播放的文件：
//            MediaPlayer要播放的文件主要包括3个来源：
//            a. 用户在应用中事先自带的resource资源
//                例如：MediaPlayer.create(this, R.raw.test);
//            b. 存储在SD卡或其他文件路径下的媒体文件
//                例如：mp.setDataSource("/sdcard/test.mp3");
//
//            c. 网络上的媒体文件
//                例如：mp.setDataSource("http://www.citynorth.cn/music/confucius.mp3");
//
//            MediaPlayer的setDataSource一共四个方法：
//            setDataSource (String path)
//            setDataSource (FileDescriptor fd)
//            setDataSource (Context context, Uri uri)
//            setDataSource (FileDescriptor fd, long offset, long length)
//
//            其中使用FileDescriptor时，需要将文件放到与res文件夹平级的assets文件夹里，然后使用：
//            AssetFileDescriptor fileDescriptor = getAssets().openFd("rain.mp3");
//            m_mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(), fileDescriptor.getLength());
//            来设置datasource
//
//        3）对播放器的主要控制方法：
//            Android通过控制播放器的状态的方式来控制媒体文件的播放，其中：
//            prepare()和prepareAsync() 提供了同步和异步两种方式设置播放器进入prepare状态，需要注意的是，如果MediaPlayer实例是由create方法创建的，那么第一次启动播放前不需要再调用prepare（）了，因为create方法里已经调用过了。
//            start()是真正启动文件播放的方法，
//            pause()和stop()比较简单，起到暂停和停止播放的作用，
//
//            seekTo()是定位方法，可以让播放器从指定的位置开始播放，需要注意的是该方法是个异步方法，也就是说该方法返回时并不意味着定位完成，尤其是播放的网络文件，真正定位完成时会触发
//            OnSeekComplete.onSeekComplete()，如果需要是可以调用setOnSeekCompleteListener(OnSeekCompleteListener)设置监听器来处理的。
//            release()可以释放播放器占用的资源，一旦确定不再使用播放器时应当尽早调用它释放资源。
//            reset()可以使播放器从Error状态中恢复过来，重新会到Idle状态。
//
//        4）设置播放器的监听器：
//            MediaPlayer提供了一些设置不同监听器的方法来更好地对播放器的工作状态进行监听，以期及时处理各种情况，
//            如： setOnCompletionListener(MediaPlayer.OnCompletionListener listener)、
//            setOnErrorListener(MediaPlayer.OnErrorListener listener)等,设置播放器时需要考虑到播放器可能出现的情况设置好监听和处理逻辑，以保持播放器的健壮性。

        //initMediaPlayer();
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choice:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                //intent.setType("image/*");//选择图片
                intent.setType("audio/*"); //选择音频
                //intent.setType("video/*"); //选择视频 （mp4 3gp 是android支持的视频格式）
                //intent.setType("video/*;image/*");//同时选择视频和图片
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,1);

                break;
            case R.id.play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                Log.v("WJWind", uri.getPath().toString()); // URI = Wind: content://com.android.providers.media.documents/document/audio%3A10430
                Toast.makeText(this, "文件路径："+uri.getPath().toString(), Toast.LENGTH_SHORT).show();

                try {
                    mediaPlayer.setDataSource(context,uri);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}