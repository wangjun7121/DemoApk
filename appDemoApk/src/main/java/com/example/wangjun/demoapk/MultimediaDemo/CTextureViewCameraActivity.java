package com.example.wangjun.demoapk.MultimediaDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.widget.FrameLayout;
import com.example.wangjun.demoapk.R;
public class CTextureViewCameraActivity extends Activity implements SurfaceTextureListener {
    private TextureView myTexture;
    private Camera mCamera;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimedia_ctextureviewcamera);
        myTexture = new TextureView(this);
        myTexture.setSurfaceTextureListener(this);
        setContentView(myTexture);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menudemo_cmenu, menu);
        return true;
    }
    @SuppressLint("NewApi")
    @Override
    // 在 SurfaceTexture 准备使用时调用
    public void onSurfaceTextureAvailable(SurfaceTexture arg0, int arg1,
                                          int arg2) {
        mCamera = Camera.open();
        Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
        myTexture.setLayoutParams(new FrameLayout.LayoutParams(
                previewSize.width, previewSize.height, Gravity.CENTER));
        try {
            // 这句是将 Camera 预览输出流设置到显示的位置
            mCamera.setPreviewTexture(arg0);

        } catch (IOException t) {
        }
        // 启动预览
        mCamera.startPreview();

        myTexture.setAlpha(1.0f);
        myTexture.setRotation(90.0f);
    }
    @Override
    // 当指定 SurfaceTexture 即将被销毁时调用。如果返回 true，则调用此方法后，表面纹理中不会发生渲染。如果返回 false，则客户端需要调用 release()。大多数应用程序应该返回 true
    public boolean onSurfaceTextureDestroyed(SurfaceTexture arg0) {
        mCamera.stopPreview();
        mCamera.release();
        return true;
    }
    @Override
    // 当 SurfaceTexture 缓冲区大小更改时调用
    public void onSurfaceTextureSizeChanged(SurfaceTexture arg0, int arg1,
                                            int arg2) {
        // TODO Auto-generated method stub
    }
    @Override
    // 当指定 SurfaceTexture 的更新时调用 updateTexImage()
    public void onSurfaceTextureUpdated(SurfaceTexture arg0) {
        // TODO Auto-generated method stub
    }
}