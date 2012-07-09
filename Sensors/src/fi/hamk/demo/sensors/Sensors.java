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
				getAccelerometer(event);
				break;
			case Sensor.TYPE_GRAVITY:
				getGravity(event);
				break;
			case Sensor.TYPE_GYROSCOPE:
				getGyroscope(event);
				break;
			case Sensor.TYPE_LIGHT:
				getLight(event);
				break;
			case Sensor.TYPE_MAGNETIC_FIELD:
				getMagneticField(event);
				break;
			case Sensor.TYPE_PROXIMITY:
				getProximity(event);
				break;
			}
		}
	};

	public void register() {
		sensorManager.registerListener(sensorEventListener,
				sensorManager.getDefaultSensor(Sensor.TYPE_ALL),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	public void unregister() {
		sensorManager.unregisterListener(sensorEventListener);
	}

	protected void getProximity(SensorEvent event) {
		android.widget.Toast.makeText(context, "TODO!",
				android.widget.Toast.LENGTH_LONG).show();
	}

	protected void getMagneticField(SensorEvent event) {
		android.widget.Toast.makeText(context, "TODO!",
				android.widget.Toast.LENGTH_LONG).show();
	}

	protected void getLight(SensorEvent event) {
		android.widget.Toast.makeText(context, "TODO!",
				android.widget.Toast.LENGTH_LONG).show();
	}

	protected void getGyroscope(SensorEvent event) {
		android.widget.Toast.makeText(context, "TODO!",
				android.widget.Toast.LENGTH_LONG).show();
	}

	protected void getGravity(SensorEvent event) {
		android.widget.Toast.makeText(context, "TODO!",
				android.widget.Toast.LENGTH_LONG).show();
	}

	protected void getAccelerometer(SensorEvent event) {
		android.widget.Toast.makeText(context, "TODO!",
				android.widget.Toast.LENGTH_LONG).show();
	}
}
