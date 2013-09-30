package com.c77.esteban.sensors;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


public class ShowMagnetometer extends Activity implements SensorEventListener {

    private boolean state;
    private Sensor magnetometer;
    private TextView x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetometer);
        x = (TextView) findViewById(R.id.xValue);
        y = (TextView) findViewById(R.id.yValue);
        z = (TextView) findViewById(R.id.zValue);
    }

    protected void onResume() {
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
        if (sensors.size() > 0)
        {
            magnetometer = sensors. get(0);
            sm.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
        }
        this.state=true;
    }


    protected void onPause() {
        this.state = false;
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this, magnetometer);
        super.onPause();
    }

    protected void onStop() {
        this.state = false;
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener (this, magnetometer);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        this.x.setText("X = " + event.values[SensorManager.DATA_X]);
        this.y.setText("Y = " + event.values[SensorManager.DATA_Y]);
        this.z.setText("Z = " + event.values[SensorManager.DATA_Z]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_magnetometer, menu);
        return true;
    }

    public void setStopMagnetometer(View view) {
        if(this.state == true) {
            SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            sm.unregisterListener(this, magnetometer);
            this.state = false;
        }
    }

    public void setStartMagnetometer(View view) {
        if(this.state == false){
            SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            sm.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
            this.state = true;
        }
    }
}
