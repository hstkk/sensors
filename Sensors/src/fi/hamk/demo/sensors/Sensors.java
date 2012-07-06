package fi.hamk.demo.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * @author Sami Hostikka
 */
public class Sensors {
	public Sensors(Context context) {
		this.context = context;
	}

	Context context;
	SensorManager sensorManager = null;
	SensorEventListener sensorEventListener = new SensorEventListener() {
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
		}

		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub

		}
	};

}
