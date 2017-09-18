package com.example.wangjun.demoapk.SensorDemo;

import android.os.Bundle;

import com.example.wangjun.demoapk.R;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class TestAccelActivity extends Activity {

    private SensorManager sensorManager;
    private TextView curAccelX_TextView;
    private TextView curAccelY_TextView;
    private TextView curAccelZ_TextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_demo_testaccel);

        curAccelX_TextView = (TextView) findViewById(R.id.accel_x);
        curAccelY_TextView = (TextView) findViewById(R.id.accel_y);
        curAccelZ_TextView = (TextView) findViewById(R.id.accel_z);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEvListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(sensorEvListener);
        }
    }

    private SensorEventListener sensorEvListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 加速度可能会是负值，所以要取它们的绝对值
            float xValue = Math.abs(event.values[0]);
            float yValue = Math.abs(event.values[1]);
            float zValue = Math.abs(event.values[2]);

            curAccelX_TextView.setText("X  = " + xValue );
            curAccelY_TextView.setText("Y  = " + yValue );
            curAccelZ_TextView.setText("Z  = " + zValue );

            if (xValue > 15 || yValue > 15 || zValue > 15) {
                // 认为用户摇动了手机，触发摇一摇逻辑
                Toast.makeText(TestAccelActivity.this, "摇一摇", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

}
