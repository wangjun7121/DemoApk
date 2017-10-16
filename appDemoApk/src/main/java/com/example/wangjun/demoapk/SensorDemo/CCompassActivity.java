package com.example.wangjun.demoapk.SensorDemo;


import android.os.Bundle;

import com.example.wangjun.demoapk.R;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class CCompassActivity extends Activity {

    private SensorManager sensorManager;

    private ImageView compassImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensordemo_ccompass);
        compassImg = (ImageView) findViewById(R.id.compass_img);

        // 获得传感器服务
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // 获得地磁与加速度传感器
        Sensor magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // 设置传感器上报速率
        sensorManager.registerListener(listener, magneticSensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(listener, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
    }

    private SensorEventListener listener = new SensorEventListener() {

        float[] accelerometerValues = new float[3];

        float[] magneticValues = new float[3];

        private float lastRotateDegree;

        @Override
        public void onSensorChanged(SensorEvent event) {

            // 判断当前是加速度传感器还是地磁传感器：分别记录着加速度传感器和地磁传感器输出的值
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                // 注意赋值时要调用clone()方法
                accelerometerValues = event.values.clone();
            } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                // 注意赋值时要调用clone()方法
                magneticValues = event.values.clone();
            }

            float[] values = new float[3];
            float[] R = new float[9];

            // 得到一个包含旋转矩阵的 R 数组
            SensorManager.getRotationMatrix(R, null, accelerometerValues,magneticValues);

            // 计算手机的旋转数据
            // values[0]: 记录着手机围绕 Z 轴的旋转弧度
            // values[1]: 记录着手机围绕 X 轴的旋转弧度
            // values[0]: 记录着手机围绕 Y 轴的旋转弧度
            SensorManager.getOrientation(R, values);

            // 弧度转角度
            float rotateDegree = -(float) Math.toDegrees(values[0]);
            if (Math.abs(rotateDegree - lastRotateDegree) > 1) {

                RotateAnimation animation = new RotateAnimation(
                        lastRotateDegree, rotateDegree,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setFillAfter(true);

                // 设置罗盘旋转
                compassImg.startAnimation(animation);
                lastRotateDegree = rotateDegree;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    };

}
