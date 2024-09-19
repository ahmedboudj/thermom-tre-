package com.example.projettemperature;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager manager;
    private Sensor tempSensor;
    private Thermometre t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = new Thermometre(this);
        setContentView(t);

        t.setTemp( 30.0f);

        t.setUnit("C");

        this.manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        this.tempSensor = this.manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        this.manager.registerListener(this, this.tempSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        t.setTemp(sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    }
}
