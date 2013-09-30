package com.c77.esteban.sensors;

import java.util.List;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.TextView;
import android.hardware.SensorManager;
import android.view.View;

import static java.lang.Math.atan;


public class ShowAccelerometer extends Activity implements SensorEventListener{


    private TextView x,y,z,XZAngle,YZAngle;
    private Sensor accelerometer;
    private boolean state;
    private double angleXZ,angleYZ;
    private float xVal,yVal,zVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        x = (TextView) findViewById(R.id.xValue);
        y = (TextView) findViewById(R.id.yValue);
        z = (TextView) findViewById(R.id.zValue);
        XZAngle = (TextView) findViewById(R.id.XZAngle);
        YZAngle = (TextView) findViewById(R.id.YZAngle);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void onResume() {
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0)
        {
            accelerometer = sensors. get(0);
            sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
        this.state = true;
    }


    protected void onPause() {
        this.state = false;
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this, accelerometer);
        super.onPause();
    }

    protected void onStop() {
        this.state = false;
        SensorManager mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this, accelerometer);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        this.x.setText("X = " + event.values[SensorManager.DATA_X] + "m/s2");
        this.y.setText("Y = " + event.values[SensorManager.DATA_Y] + "m/s2");
        this.z.setText("Z = " + event.values[SensorManager.DATA_Z] + "m/s2");
        xVal = event.values[SensorManager.DATA_X];
        yVal = event.values[SensorManager.DATA_Y];
        zVal = event.values[SensorManager.DATA_Z];
        angleYZ = atan((double)yVal / (double)zVal);
        angleYZ = angleYZ*(57.2958);
        angleXZ = atan((double)xVal / (double)zVal);
        angleXZ = angleXZ*(57.2958);
        this.XZAngle.setText("XZ = " + String.format("%.2f",angleXZ) + "°");
        this.YZAngle.setText("YZ = " + String.format("%.2f",angleYZ) + "°");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_accelerometer, menu);
        return true;
    }

    public void setStopAccelerometer(View view) {
        if(this.state == true) {
            SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            sm.unregisterListener(this, accelerometer);
            this.state = false;
        }

    }

    public void setStartAccelerometer(View view) {
        if(this.state == false){
            SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
            this.state = true;
        }
    }
}
