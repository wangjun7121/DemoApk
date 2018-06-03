package com.example.wangjun.demoapk.HardwareDemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.os.RemoteException;
import android.widget.TextView;
import android.widget.Button;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

import com.example.wangjun.demoapk.R;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//
//1.Android系统亮度调节
//
//        Android系统亮度调节全局性最高，常见于系统设置中的亮度设置项。Android中提供了获取和设置系统亮度值
//        （“手动模式下的亮度值”）的接口，具体如下：
//
//            // 获取系统亮度
//            Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
//            // 设置系统亮度
//            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS,systemBrightness);
//
//        其中，需要注意的是，返回的亮度值是处于0-255之间的整型数值。
//
//            Android 2.1以后的系统中，系统亮度调节中新增了“自动亮度”选项。“自动亮度”是依据外界光源来自
//            动的改变系统亮度，目前大部分手机中对“自动亮度”还可以进行小幅度的调节其值。与自动亮度相对应的
//            是“手动亮度”，当处于“手动亮度”下，设置拖动亮度进度条会大幅度的改变Android系统亮度。
//            “手动亮度”和“自动亮度”分别称之为Android系统的“亮度模式”。
//
//        与之相对应的，Android系统中也提供了获取和设置“亮度模式”的接口。
//
//            // 获取系统亮度模式
//            Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
//            // 设置系统亮度模式
//            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, systemMode);
//
//        可是遗憾的是，Android中并未提供处于“自动亮度”模式下的亮度值接口。上面所说的获取系统亮度值接口实
//        际上都是指“手动亮度”模式下的亮度值。
//
//        一般而言，通过手动亮度值以及设置系统亮度模式接口，可以满足常规的大部分针对Android系统亮度设置编码需求
//        ，以完成系统亮度调节。
//
//
//
//2.Android App亮度调节
//
//        与系统亮度不同的是，Android中并未直接提供针对于App层面的亮度调节方式。因此，对于需要进行App的亮
//        度调节，可以通过系统亮度调节或当前屏幕的亮度调节方式间接来实现。
//
//
//
//3.Android当前屏幕（Window）亮度调节
//
//        Android针对当前屏幕（Window）提供了设置亮度的接口，常见写法如下：
//
//                Window window = activity.getWindow();
//                WindowManager.LayoutParams lp = window.getAttributes();
//                lp.screenBrightness = brightness;
//                window.setAttributes(lp);
//
//        其中，需要注意的是此处的brightness是一个0.0-1.0之间的一个float类型数值。
//
//        默认情况下，当我们直接修改了系统亮度值后，当前Window中是可以即时反应出来亮度效果的，这是因
//        为默认情况下，WindowManager.LayoutParams的screenBrightness的默认值为 WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE。
//
//        即表示Window没有自己的亮度参数，将依随系统亮度效果的变化而变化。这也就是我们最常见的：
//        当调整系统亮度后，所有 Window都即时反应出系统亮度设置效果。
//
//        当时，实际项目中我们还会遇到此类需求：系统设置亮度时只针对当前Window或App内生效，而不影响到系统本身的亮度设置。
//
//        假设当前Window内有一个SeekBar，UI与系统亮度调节UI基本类似，用户可以滑动此SeekBar，使得当前Window亮度即时
//        发生变化，且不影响到系统亮度效果。如何实现呢？
//
//        此时我们需要启用WindowManager.LayoutParams的screenBrightness参数，使之具有自动的特定亮度值，设置此值后在
//        当前Window范围内，其将覆盖掉系统亮度设置。
//
//        因此，有必要将用户选择的亮度值转换为相应的Window亮度值（为与系统亮度值相一致，假设SeekBar的最大值为255）。
//
//            // 根据亮度值修改当前window亮度
//            public void changeAppBrightness(Context context, int brightness) {
//                Window window = ((Activity) context).getWindow();
//                WindowManager.LayoutParams lp = window.getAttributes();
//                if (brightness == -1) {
//                    lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
//                } else {
//                    lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
//                }
//                 window.setAttributes(lp);
//             }
//
//        其中，brightness形参为用户选择的亮度值。
//
//        那么，上述代码中为什么有一个"brightness == -1"的判断呢？在此主要是考虑到App设置项中可能出现的“跟随系统亮度”
//        或“恢复系统默认亮度”此类设置，当用户进行此类操作时，直接将screenBrightness参数还原成默认参数值即可。因为
//        由前文描述“自动亮度”模式下系统亮度值是不能直接得到的，那么当系统处于“自动亮度”模式下，此时brightness参数值
//        将无法准确确定，因此，将screenBrightness参数还原成默认参数值成为一个行之有效的方法。


public class CBackLightTestActivity extends AppCompatActivity {
    Button backbutton;
    int oldbrightness;
    int mOldBrightnessMode;
    boolean stop = false;

    private static final int BRIGHT = 1;
    private static final int DARK = 2;
    private static final int STOP = 3;
    private static final int BUTTON_BACKLIGHT_ON = 4;
    private static final int BUTTON_BACKLIGHT_OFF = 5;
    private static final int HALFBRIGHT = 4;


    protected static final String LOG_TAG = "EMODE_BacklightTest";
    private String mButtonLightPath = "/sys/class/leds/button-backlight/brightness";

    private void setButtonLightBrightness(String brightness){
        try{
            FileOutputStream os = new FileOutputStream(mButtonLightPath);
            os.write(brightness.getBytes());
            os.close();
        }catch(FileNotFoundException fe){
            fe.printStackTrace();
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BRIGHT:
//                    try {
//                        //if (!stop)
//                            //power.setTemporaryScreenBrightnessSettingOverride(255);
//                    } catch (RemoteException e) {
//                    }
                    Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS,255);
                    sendEmptyMessageDelayed(DARK, 3000);
                    break;
                case DARK:
//                    try {
//                        //if (!stop)
//                            //power.setTemporaryScreenBrightnessSettingOverride(30);
//                    } catch (RemoteException e) {
//                    }
                    Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS,30);
                    sendEmptyMessageDelayed(BRIGHT, 3000);
                    break;

                case STOP:
                    removeMessages(DARK);
                    removeMessages(BRIGHT);

//                    try {
//                       // power.setTemporaryScreenBrightnessSettingOverride(oldbrightness);
//                    } catch (RemoteException e) {
//                    }
                    restoreBrightnessMode();

                    break;
                case BUTTON_BACKLIGHT_ON:
                    if(!stop){
                        setButtonLightBrightness("255");
                        sendEmptyMessageDelayed(BUTTON_BACKLIGHT_OFF, 1500);
                    }else{
                        setButtonLightBrightness("0");
                    }
                    break;
                case BUTTON_BACKLIGHT_OFF:
                    if(!stop){
                        setButtonLightBrightness("0");
                        sendEmptyMessageDelayed(BUTTON_BACKLIGHT_ON, 1500);
                    }else{
                        setButtonLightBrightness("0");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private int getBrightnessMode(int defaultValue) {
        int brightnessMode = defaultValue;
        try {
            brightnessMode = Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (SettingNotFoundException snfe) {
            //Log.d(LOG_TAG,"SettingNotFoundException");
        }
        //Log.d(LOG_TAG,"brightnessMode=" + brightnessMode);
        return brightnessMode;
    }

    private void restoreBrightnessMode(){
        if(mOldBrightnessMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC){
            Settings.System.putInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.hardwaredemo_cbacklighttest);

        try {
            // 获得进入程序前的亮度，用于恢复使用
            oldbrightness = Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (SettingNotFoundException snfe) {
            oldbrightness = 102;
        }


        // 备份当前系统亮度模式
        mOldBrightnessMode = getBrightnessMode(Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

        // 如果是自动亮度模式，这里则修改为手动模式
        if(mOldBrightnessMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC){

            Settings.System.putInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        }

        handler.sendEmptyMessage(DARK);
        //if (getResources().getBoolean(R.bool.config_touch_button_has_backlight)) {
        if (false) {
            handler.sendEmptyMessage(BUTTON_BACKLIGHT_ON);
        } else {
            TextView tvTipKeypad = (TextView) findViewById(R.id.tv_tipKeypad);
            tvTipKeypad.setText("Please check to see how many times the phone shake!");
        }

//		if (EMflag != ManulDetect) {
//			EMtimer.schedule(EMtask, 7000);
//		}
    }

    @Override
    protected void onResume() {
        super.onResume();

        stop = false;
    }

    @Override
    protected void onPause() {
        super.onPause();

        stop = true;
//        try {
//            //power.setTemporaryScreenBrightnessSettingOverride(oldbrightness);
//        } catch (RemoteException e) {
//        }
        restoreBrightnessMode();
    }

    @Override
    protected void onStop() {
        super.onStop();

        handler.sendEmptyMessage(STOP);
    }
}
