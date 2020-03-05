package com.example.wangjun.demoapk.HardwareDemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.wangjun.demoapk.R;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;




public class CScreenCapTestActivity extends Activity {
    public static int height; //屏幕高
    public static int width ;    //屏幕的宽
    public static int deepth;//位深

    //ScreenShotFb fb;
    public static String TAG="weiqifa";
    final static String FB0FILE1 = "/dev/graphics/fb0";
    static File fbFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //getWindow().setFlags(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);  //设置全屏
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.hardware_cscreencaptest);
        Log.d(TAG,"com.example.camerajni onCreate");
        //getPiture();
        Button t;

        t=(Button)findViewById(R.id.screencapBtn);

        t.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                String strTmp = "点击Button01";
                getPiture();
                Log.d(TAG,strTmp);
            }
        });


        //fb.init(this);
        //fb.shoot();
    }

    public static int[] convertToColor(byte[] piex) throws Exception {
        switch (deepth) {
            case 2:
                return convertToColor_2byte(piex);
            case 3:
                return convertToColor_3byte(piex);
            case 4:
                return convertToColor_4byte(piex);
            default:
                Log.e(TAG, "wwwwwException error");
                return convertToColor_3byte(piex);
        }
    }
    public static int[] convertToColor_2byte(byte[] piex) {
        int[] colors = new int[width * height];
        int len = piex.length;
        for (int i = 0; i < len; i += 2) {
            int colour = (piex[i+1] & 0xFF) << 8 | (piex[i] & 0xFF);
            int r = ((colour & 0xF800) >> 11)*8;
            int g = ((colour & 0x07E0) >> 5)*4;
            int b = (colour & 0x001F)*8;
            int a = 0xFF;
            colors[i / 2] = (a << 24) + (r << 16) + (g << 8) + b;
        }
        return colors;
    }

    public static int[] convertToColor_3byte(byte[] piex) {
        int[] colors = new int[width * height];
        int len = piex.length;
        for (int i = 0; i < len; i += 3) {
            int r = (piex[i] & 0xFF);
            int g = (piex[i + 1] & 0xFF);
            int b = (piex[i + 2] & 0xFF);
            int a = 0xFF;
            colors[i / 3] = (a << 24) + (r << 16) + (g << 8) + b;
        }
        return colors;
    }

    public static int[] convertToColor_4byte(byte[] piex) {
        int[] colors = new int[width * height];
        int len = piex.length;
        for (int i = 0; i < len; i += 4) {
            int r = (piex[i] & 0xFF);
            int g = (piex[i + 1] & 0xFF);
            int b = (piex[i + 2] & 0xFF);
            int a = (piex[i + 3] & 0xFF);
            colors[i / 4] = (a << 24) + (r << 16) + (g << 8) + b;
        }
        return colors;
    }

    public  void getPiture()
    {
        FileInputStream buf = null;
        //获取屏幕大小：
        DisplayMetrics metrics =new DisplayMetrics();
        WindowManager WM = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        Display display = WM.getDefaultDisplay();
        display.getMetrics(metrics);
        height = metrics.heightPixels; //屏幕高
        width = metrics.widthPixels;    //屏幕的宽
        //		获取显示方式
        int pixelformat = display.getPixelFormat();
        PixelFormat localPixelFormat1 =new PixelFormat();
        PixelFormat.getPixelFormatInfo(pixelformat, localPixelFormat1);
        deepth = localPixelFormat1.bytesPerPixel;//位深
        //deepth=3;
        try{
            //InputStream stream =getInputStream();
            fbFile = new File(FB0FILE1);
            buf = new FileInputStream(fbFile);// 读取文件内容
            InputStream stream=new DataInputStream(buf);
            //byte[] piex = new byte[height * width * deepth];
            byte[] piex = new byte[height * (width+16) * deepth];
            DataInputStream dStream = new DataInputStream(stream);
            int i=0;

            //while(dStream.read(piex, 0, height * width * deepth) != -1){
            while(dStream.read(piex, 0, height * (width+16) * deepth) != -1){
                //int[] data = convertToColor(piex);
                //Bitmap bm = Bitmap.createBitmap(data, width, height, Bitmap.Config.RGB_565);
                // data = null;
                //FileOutputStream out = new FileOutputStream("/sdcard/test"+".png");
                //bm.compress(Bitmap.CompressFormat.PNG, 50,out);
                Log.d(TAG,"Debug"+"height="+height+"  width="+width);
                // 保存图片
                int[] colors = new int[height * width];
                int j=0;
                for(int m=0;m<piex.length;m++){

                    if(m%4 == 0){
                        int r = (piex[m] & 0xFF);
                        int g = (piex[m+1] & 0xFF);
                        int b = (piex[m+2] & 0xFF);
                        int a = (piex[m+3] & 0xFF);
                        colors[m/4]= (a << 24) + (r <<16) + (g <<8) + b;
                        Log.d(TAG,"m="+m+"  r=piex[m]="+r+"   g=piex[m+1]="+g+"  b=piex[m+2]="+b+"  a=piex[m+3]"+a+"  colors.length"+colors.length);
//							if(a==0xFF)
//							{
//								colors[j]= (a << 24) + (r <<16) + (g <<8) + b;
//							    j++;
//							}else{
//								//Log.d(TAG,"a="+a+"   m="+m);
//							}
                    }
                }
                //piex生成bitmap
                Bitmap bitmap = Bitmap.createBitmap(colors, width, height, Bitmap.Config.ARGB_8888);
                //bitmap保存为png格式：
                FileOutputStream out = new FileOutputStream("/sdcard/test"+".png");
                bitmap.compress(Bitmap.CompressFormat.PNG, 100,out);
                Log.d(TAG," height="+height+" width="+width+" deepth="+deepth+" piex.length="+piex.length+" colors.length="+colors.length);
            }
        }catch(Exception e){
            Log.e(TAG, "Exception error");
        }
    }
}
