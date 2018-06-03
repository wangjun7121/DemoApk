package com.example.wangjun.demoapk.HardwareDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.wangjun.demoapk.R;
import com.example.wangjun.demoapk.Tools.LogUtil;

/**
 * Created by sunhuihui on 2017/10/25.
 */
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import android.view.View;
import android.view.Window;


public class CProximityTestActivity extends AppCompatActivity implements SensorEventListener {
    TextView mtitle;
    TextView mtext;
    Button mbutton;

    SensorManager sm;
    Sensor mSensor;

    protected static final String LOG_TAG = "ProximitySensorTest";

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.hardwaredemo_cproximitytest);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mtext = (TextView) findViewById(R.id.m0_t);
        mtext.setText("Proximity" + " X : 9.0");

        mbutton = (Button) findViewById(R.id.m0_b);
        mbutton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.m0_b: {
                        finish();
                        return;
                    }
                    default:
                        break;
                }
            }
        });
        mbutton.setText("OK");
//		if (EMflag == AutoDetect) {
//			EMtimer.schedule(EMtask, 15000);
//		}
    }

    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        Sensor sensor = event.sensor;
        synchronized (this) {
            if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
                mtext.setText("Proximity" + " X:  " + values[0]);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        sm.unregisterListener(this);
        super.onStop();
    }

    // @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }
}

