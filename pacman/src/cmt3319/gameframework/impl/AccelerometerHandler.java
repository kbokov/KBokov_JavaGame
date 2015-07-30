package cmt3319.gameframework.impl;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerHandler implements SensorEventListener {

	float accelX;
	float accelY;
	float accelZ;
	
	public AccelerometerHandler(Context context) {
		SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		
		if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			
			Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			
			manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
		}
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// do nothing here
	}
	
	public void onSensorChanged(SensorEvent event) {
		accelX = event.values[0];
		accelY = event.values[1];
		accelZ = event.values[2];
	}
	
	public float getAccelX() {
		return this.accelX;
	}
	
	public float getAccelY() {
		return this.accelY;
	}
	
	public float getAccelZ() {
		return this.accelZ;
	}
}
