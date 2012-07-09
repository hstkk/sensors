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
		sensor = new fi.hamk.demo.sensors.models.Sensor();
		this.context = context;
		sensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
	}

	fi.hamk.demo.sensors.models.Sensor sensor;
	Context context;
	SensorManager sensorManager = null;
	SensorEventListener sensorEventListener = new SensorEventListener() {
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
		}

		public void onSensorChanged(SensorEvent event) {
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				break;
			case Sensor.TYPE_AMBIENT_TEMPERATURE:
				break;
			case Sensor.TYPE_GRAVITY:
				break;
			case Sensor.TYPE_GYROSCOPE:
				break;
			case Sensor.TYPE_LIGHT:
				break;
			case Sensor.TYPE_LINEAR_ACCELERATION:
				break;
			case Sensor.TYPE_MAGNETIC_FIELD:
				break;
			case Sensor.TYPE_PRESSURE:
				break;
			case Sensor.TYPE_PROXIMITY:
				break;
			case Sensor.TYPE_RELATIVE_HUMIDITY:
				break;
			case Sensor.TYPE_ROTATION_VECTOR:
				break;
			}
		}
	};

	protected void register() {
		sensorManager.registerListener(sensorEventListener,
				sensorManager.getDefaultSensor(Sensor.TYPE_ALL),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	protected void unregister() {
		sensorManager.unregisterListener(sensorEventListener);
	}
}
