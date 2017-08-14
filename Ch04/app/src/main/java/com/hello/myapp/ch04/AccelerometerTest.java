package com.hello.myapp.ch04;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by Cloud on 2017-08-10.
 */

    public class AccelerometerTest extends Activity implements SensorEventListener {
    TextView textView;
    StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);
        SensorManager manager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);//?????????????????
        if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() ==0){
            textView.setText("No accelerometer installed");
        }else{
            Sensor accelerometer= manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            if(!manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)){
                textView.setText("Couldn't register sensor listener");
            }
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        stringBuilder.setLength(0);
        stringBuilder.append("X: ");
        stringBuilder.append(event.values[0]);
        stringBuilder.append(" , Y : ");
        stringBuilder.append(event.values[1]);
        stringBuilder.append(", Z : ");
        stringBuilder.append(event.values[2]);
        textView.setText(stringBuilder.toString());

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
