package com.example.wangjun.demoapk.MultimediaDemo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Environment;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//        Android 5.0 对拍照 API 进行了全新的设计，新增了全新设计的 Camera v2 API，这些API不仅大幅提高了 Android 系统拍照的功能，还能支持 RAW 照片输出，甚至允许程序调整相机的对焦模式、曝光模式、快门等。
//        Android 5.0 的 Camera v2 主要涉及如下API。
//            Ø CameraManager：摄像头管理器。这是一个全新的系统管理器，专门用于检测系统摄像头、打开系统摄像头。除此之外，调用 CameraManager 的 getCameraCharacteristics(String) 方法即可获取指定摄像头的相关特性。
//                Ø CameraCharacteristics：摄像头特性。该对象通过 CameraManager 来获取，用于描述特定摄像头所支持的各种特性。
//                Ø CameraDevice：代表系统摄像头。该类的功能类似于早期的 Camera 类。
//                Ø CameraCaptureSession：这是一个非常重要的 API，当程序需要预览、拍照时，都需要先通过该类的实例创建 Session。而且不管预览还是拍照，也都是由该对象的方法进行控制的，其中控制预览
//                    的方法为 setRepeatingRequest()；控制拍照的方法为 capture()。
//                    为了监听 CameraCaptureSession 的创建过程，以及监听 CameraCaptureSession 的拍照过程，Camera v2 API 为 CameraCaptureSession 提供了 StateCallback、CaptureCallback 等内部类。
//
//                      Ø CameraRequest 和 CameraRequest.Builder： 当程序调用setRepeatingRequest()方法进行预览时，或调用capture()方法进行拍照时，都需要传入CameraRequest参数。
//                          CameraRequest 代表了一次捕获请求，用于描述捕获图片的各种参数设置，比如对焦模式、曝光模式……总之，程序需要对照片所做的各种控制，都通过CameraRequest参数进行设置。
//                          CameraRequest.Builder则负责生成CameraRequest对象。

//        控制拍照的步骤大致如下。
//            1. 调用 CameraManager 的 openCamera(String cameraId, CameraDevice.StateCallback callback, Handler handler) 方法打开指定摄像头。
//                该方法的第一个参数代表要打开的摄像头ID；
//                第二个参数用于监听摄像头的状态；
//                第三个参数代表执行callback的Handler，如果程序希望直接在当前线程中执行callback，则可将handler参数设为null。
//
//            2. 当摄像头被打开之后，程序即可获取CameraDevice—即根据摄像头ID获取了指定摄像头设备，
//              然后调用 CameraDevice的createCaptureSession(List<Surface> outputs, CameraCaptureSession. StateCallback callback，Handler handler) 方法来创建 CameraCaptureSession。
//                该方法的第一个参数是一个List集合，封装了所有需要从该摄像头获取图片的Surface，
//                第二个参数用于监听CameraCaptureSession的创建过程；
//                第三个参数代表执行callback的Handler，如果程序希望直接在当前线程中执行callback，则可将handler参数设为null。
//
//            3. 不管预览还是拍照，程序都调用 CameraDevice 的 createCaptureRequest(int templateType) 方法创建 CaptureRequest.Builder，
//                  该方法支持 TEMPLATE_PREVIEW（预览）、TEMPLATE_RECORD（拍摄视频）、TEMPLATE_STILL_CAPTURE（拍照）等参数。
//            4. 通过第 3 步所调用方法返回的 CaptureRequest.Builder 设置拍照的各种参数，比如对焦模式、曝光模式等。
//            5. 调用 CaptureRequest.Builder的build() 方法即可得到 CaptureRequest 对象，接下来程序可通过 CameraCaptureSession 的 setRepeatingRequest() 方法开始预览，或调用 capture() 方法拍照。

public class CCameraV2ApiActivity extends Activity implements View.OnClickListener {
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    private CCameraV2Api_AutoFitTextureView textureView;

    // 摄像头ID（通常 0 代表后置摄像头，1 代表前置摄像头）
    private String mCameraId = "0";

    // 定义代表摄像头的成员变量
    private CameraDevice cameraDevice;

    // 预览尺寸
    private Size previewSize;
    private CaptureRequest.Builder previewRequestBuilder;

    // 定义用于预览照片的捕获请求
    private CaptureRequest previewRequest;

    // 定义 CameraCaptureSession 成员变量
    private CameraCaptureSession captureSession;

    private ImageReader imageReader;
    private final TextureView.SurfaceTextureListener mSurfaceTextureListener
            = new TextureView.SurfaceTextureListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture texture
                , int width, int height) {
            // 当 TextureView 可用时，打开摄像头
            openCamera(width, height);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture texture
                , int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture texture) {
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture texture) {
        }
    };
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        //  摄像头被打开时激发该方法
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onOpened(CameraDevice cameraDevice) {
            CCameraV2ApiActivity.this.cameraDevice = cameraDevice;
            // 创建 CameraCapureSession，开始预览
            createCameraPreviewSession();  // ②
        }

        // 摄像头断开连接时激发该方法
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onDisconnected(CameraDevice cameraDevice) {
            cameraDevice.close();
            CCameraV2ApiActivity.this.cameraDevice = null;
        }

        // 打开摄像头出现错误时激发该方法
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onError(CameraDevice cameraDevice, int error) {
            cameraDevice.close();
            CCameraV2ApiActivity.this.cameraDevice = null;
            CCameraV2ApiActivity.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimediademo_ccamerav2api);
        textureView = (CCameraV2Api_AutoFitTextureView) findViewById(R.id.texture);

        //  为该组件设置监听器，当 TextureView 初始化好时，会在监听里面打开 Camera
        textureView.setSurfaceTextureListener(mSurfaceTextureListener);
        findViewById(R.id.capture).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        // 点击拍照
        captureStillPicture();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void captureStillPicture() {
        try {
            if (cameraDevice == null) {
                return;
            }
            // 创建作为拍照的 CaptureRequest.Builder
            final CaptureRequest.Builder captureRequestBuilder =
                    cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);

            // 将 imageReader 的 surface 作为 CaptureRequest.Builder 的目标
            captureRequestBuilder.addTarget(imageReader.getSurface());

            // 设置自动对焦模式
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                    CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);

            // 设置自动曝光模式
            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                    CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);

            // 获取设备方向
            int rotation = getWindowManager().getDefaultDisplay().getRotation();

            // 根据设备方向计算设置照片的方向
            captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION
                    , ORIENTATIONS.get(rotation));

            // 停止连续取景
            captureSession.stopRepeating();

            // 捕获静态图像
            captureSession.capture(captureRequestBuilder.build()
                    , new CameraCaptureSession.CaptureCallback()  // ⑤
                    {
                        // 拍照完成时激发该方法, 重新进入预览方法
                        @Override
                        public void onCaptureCompleted(CameraCaptureSession session
                                , CaptureRequest request, TotalCaptureResult result) {
                            try {
                                // 重设自动对焦模式
                                previewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,
                                        CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
                                // 设置自动曝光模式
                                previewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                                        CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                                // 打开连续取景模式
                                captureSession.setRepeatingRequest(previewRequest, null,
                                        null);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    // 打开摄像头
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openCamera(int width, int height) {

        // 设置 camera 输出属性
        setUpCameraOutputs(width, height);

        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            // 打开摄像头
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            // 打开后摄时传入了一个 stateCallback 参数，该参数代表的对象可检测摄像头的状态改变
            //      当摄像头的状态发生改变时，程序将会自动回调该对象的相应方法
            //  null: 预览，拍照时没有传入 Handler 参数，这意味着程序直接在主线程中完成相应的 Callback 任务
            //      这样可能会导致程序响应变慢，实际应用中建议传入的
            manager.openCamera(mCameraId, stateCallback, null); // ①
        }
        catch (CameraAccessException e)
        {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createCameraPreviewSession()
    {
        try
        {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            texture.setDefaultBufferSize(previewSize.getWidth(), previewSize.getHeight());
            Surface surface = new Surface(texture);

            // 创建作为预览的 CaptureRequest.Builder
            previewRequestBuilder = cameraDevice
                    .createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

            ///////////////////////////////////////////////////////////////////////////
            // 将 textureView 的 surface 作为 CaptureRequest.Builder 的目标
            //      TextureView 与 Camera 显示绑定的位置
            //////////////////////////////////////////////////////////////////////////
            previewRequestBuilder.addTarget(new Surface(texture));

            // 创建 CameraCaptureSession，该对象负责管理处理预览请求和拍照请求
            cameraDevice.createCaptureSession(Arrays.asList(surface
                    , imageReader.getSurface()), new CameraCaptureSession.StateCallback() // ③
                    {
                        @Override
                        // 当 CameraCaptureSession 创建成功时将会自动回调该方法
                        public void onConfigured(CameraCaptureSession cameraCaptureSession)
                        {
                            // 如果摄像头为null，直接结束方法
                            if (null == cameraDevice)
                            {
                                return;
                            }

                            // 当摄像头已经准备好时，开始显示预览
                            captureSession = cameraCaptureSession;

                            ////////////////////////////////////////////////////////////////////////
                            // 设置了预览参数
                            ////////////////////////////////////////////////////////////////////////
                            try
                            {
                                // 设置自动对焦模式
                                previewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                                        CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);

                                // 设置自动曝光模式
                                previewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                                        CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);

                                // 开始显示相机预览
                                previewRequest = previewRequestBuilder.build();

                                // 设置预览时连续捕获图像数据
                                captureSession.setRepeatingRequest(previewRequest,
                                        null, null);  // ④
                            }
                            catch (CameraAccessException e)
                            {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession)
                        {
                            Toast.makeText(CCameraV2ApiActivity.this, "配置失败！"
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }, null
            );
        }
        catch (CameraAccessException e)
        {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setUpCameraOutputs(int width, int height)
    {

        // 获取 CameraManager 服务
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

//             // 获取设备上摄像头列表
//            String[] ids = manager.getCameraIdList();
//
//            // 创建一个空的 CameraInfo 对象，用于获取摄像头信息
//            Camera.CameraInfo camerainfo = new Camera.getCameraInfo();
//
//            // 获取指定摄像头的特性: ID = 0 表示打开后摄
//            CameraCharacteristics characteristics = manager.getCameraCharacteristics(mCameraId);


        try
        {


            // 获取指定摄像头的特性: ID = 0 表示打开后摄
            CameraCharacteristics characteristics
                    = manager.getCameraCharacteristics(mCameraId);

            // 获取摄像头支持的配置属性
            StreamConfigurationMap map = characteristics.get(
                    CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);

            // 获取摄像头支持的最大尺寸
            Size largest = Collections.max(
                    Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)),
                    new CompareSizesByArea());

            // 创建一个 ImageReader 对象，用于获取摄像头的图像数据
            imageReader = ImageReader.newInstance(largest.getWidth(), largest.getHeight(),
                    ImageFormat.JPEG, 2);
            imageReader.setOnImageAvailableListener(
                    new ImageReader.OnImageAvailableListener()
                    {
                        // 当照片数据可用时激发该方法
                        @Override
                        public void onImageAvailable(ImageReader reader)
                        {
                            // 获取捕获的照片数据
                            Image image = reader.acquireNextImage();
                            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                            byte[] bytes = new byte[buffer.remaining()];

                            // 使用 IO 流将照片写入指定文件
                            File file = new File(getExternalFilesDir(null), "pic.jpg");
                            buffer.get(bytes);
                            try (
                                    FileOutputStream output = new FileOutputStream(file))
                            {
                                output.write(bytes);
                                Toast.makeText(CCameraV2ApiActivity.this, "保存: "
                                        + file, Toast.LENGTH_SHORT).show();
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            finally
                            {
                                image.close();
                            }
                        }
                    }, null);

            // 获取最佳的预览尺寸
            previewSize = chooseOptimalSize(map.getOutputSizes(
                    SurfaceTexture.class), width, height, largest);

            // 根据选中的预览尺寸来调整预览组件（TextureView的）的长宽比
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                textureView.setAspectRatio(
                        previewSize.getWidth(), previewSize.getHeight());
            }
            else
            {
                textureView.setAspectRatio(
                        previewSize.getHeight(), previewSize.getWidth());
            }
        }
        catch (CameraAccessException e)
        {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            System.out.println("出现错误。");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static Size chooseOptimalSize(Size[] choices
            , int width, int height, Size aspectRatio)
    {
        // 收集摄像头支持的打过预览 Surface 的分辨率
        List<Size> bigEnough = new ArrayList<>();
        int w = aspectRatio.getWidth();
        int h = aspectRatio.getHeight();
        for (Size option : choices)
        {
            if (option.getHeight() == option.getWidth() * h / w &&
                    option.getWidth() >= width && option.getHeight() >= height)
            {
                bigEnough.add(option);
            }
        }
        // 如果找到多个预览尺寸，获取其中面积最小的。
        if (bigEnough.size() > 0)
        {
            return Collections.min(bigEnough, new CompareSizesByArea());
        }
        else
        {
            System.out.println("找不到合适的预览尺寸！！！");
            return choices[0];
        }
    }

    // 为 Size 定义一个比较器 Comparator
    static class CompareSizesByArea implements Comparator<Size>
    {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public int compare(Size lhs, Size rhs)
        {
            // 强转为 long 保证不会发生溢出
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                    (long) rhs.getWidth() * rhs.getHeight());
        }
    }
}