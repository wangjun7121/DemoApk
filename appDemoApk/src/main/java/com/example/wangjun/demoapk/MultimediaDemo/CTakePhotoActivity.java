package com.example.wangjun.demoapk.MultimediaDemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.os.Bundle;

import com.example.wangjun.demoapk.MainActivity;
import com.example.wangjun.demoapk.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;



//程序流程汇总：
//        1. 创建一个 File 对象用来保存图片
//        2. 将 File 对象转换成 Uri 对象
//        3. 创建一个 Intent 说明要调用像，附属参数为 Uri 即照片保存位置
//        4. startActivityForResult() 启动相机拍照
//        5. 拍照完成进入 onActivityResult()
//            进入 TAKE_PHOTO 分支：
//                再创建一个 Intent 裁剪图片
//                startActivityForResult() 启动裁剪程序
//        6. 裁剪完后再进入 onActivityResult()
//            进入分支 CROP_PHOTO:
//                将裁剪后的图片转码显示出来




public class CTakePhotoActivity extends Activity {

    public static final int TAKE_PHOTO = 1;

    public static final int CROP_PHOTO = 2;

    public static final int DISP_PHOTO = 3;

    private Button takePhoto;

    private Button chooseFromAlbum;

    private ImageView picture;

    private Uri photoURI;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimediademo_ctakephoto);
        takePhoto = (Button) findViewById(R.id.take_photo);
        chooseFromAlbum = (Button) findViewById(R.id.choose_from_album);
        picture = (ImageView) findViewById(R.id.picture);

        context=getBaseContext();

        // 拍照按钮处理：通过 Intent 跳转到拍照程序进行拍照
        takePhoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //imageUri = Uri.fromFile(outputImage);

                photoURI = FileProvider.getUriForFile(context, "com.example.wangjun.demoapk.MultimediaDemo.provider", outputImage);


                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        // 选择图片按钮处理：通过 Intent 跳转到图库进行图片选取
        chooseFromAlbum.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // 动态请求权限
                if(ContextCompat.checkSelfPermission(CTakePhotoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CTakePhotoActivity.this,new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }


                // 激活系统图库，选择一张图片，返回图片的 Uri
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,DISP_PHOTO);


//                File outputImage = new File(Environment.getExternalStorageDirectory(), "output_image.jpg");
//                try {
//                    if (outputImage.exists()) {
//                        outputImage.delete();
//                    }
//                    outputImage.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                photoURI = FileProvider.getUriForFile(context, "com.example.wangjun.demoapk.MultimediaDemo.provider", outputImage);
//                 // 不可用
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
//                intent.setType("image/*");
//                intent.putExtra("crop", "true");
//                intent.putExtra("scale", true);
//                intent.putExtra("return-data", false);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//                intent.putExtra("noFaceDetection", true); // no face detection
//                startActivityForResult(intent, CROP_PHOTO);


//                图库应用会将图片的URL路径和图片缩略图返回给我们的应用
                // 不可用
//                Intent intent = new Intent("android.intent.action.GET_CONTENT");
//                intent.setType("image/*");
//                intent.putExtra("crop", true);
//                intent.putExtra("scale", true);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(intent, CROP_PHOTO);






            }
        });
    }


    // 当拍完照或选择完图片返回时处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                // 拍完照片后，进行裁剪照片
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        //添加这一句表示对目标应用临时授权该Uri所代表的文件
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    intent.setDataAndType(photoURI, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                // 裁剪完照片，进行图片显示
                if (resultCode == RESULT_OK) {
                    // 得到图片的全路径
                    Uri uri = data.getData();

                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case DISP_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 得到图片的全路径
                    Uri uri = data.getData();

                    // 裁剪图片
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        //添加这一句表示对目标应用临时授权该Uri所代表的文件
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    intent.setDataAndType(uri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, CROP_PHOTO);



                    // 通过路径直接加载图片，不裁剪了
                    //这里省去了图片缩放操作，如果图片过大，可能会导致内存泄漏
                    //图片缩放的实现，请看：http://blog.csdn.net/reality_jie_blog/article/details/16891095
                    //picture.setImageURI(uri);
                }
                break;
            default:
                break;
        }
    }

}