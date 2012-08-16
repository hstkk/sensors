package fi.hamk.demo.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Sensor events.
 * 
 * @author Sami Hostikka & Pontus Vainionpaa
 */
public class Sensors {

	public Sensors(Context context) {
		sensor = new fi.hamk.demo.sensors.models.Sensor();
		this.context = context;
		sensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
	}

	fi.hamk.demo.sensors.models.Sensor sensor;
	Context context;
	SensorManager sensorManager = null;
	LocationManager locationManager = null;
	SensorEventListener sensorEventListener = new SensorEventListener() {
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// unused
		}

		/**
		 * Handles sensor changed event.
		 * 
		 * @param event
		 *            sensor event
		 */
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

	LocationListener locationListener = new LocationListener() {
		/**
		 * Handles location changed event.
		 * 
		 * @param current
		 *            location.
		 */
		public void onLocationChanged(Location location) {
			sensor.location.provider = location.getProvider();
			sensor.location.accuracy = location.getAccuracy();
			sensor.location.satellites = location.getExtras().getInt(
					"satellites");
			sensor.location.speed = location.getSpeed();
			sensor.location.altitude = location.getAltitude();
			sensor.location.latitude = location.getLatitude();
			sensor.location.longitude = location.getLongitude();
		}

		public void onProviderDisabled(String provider) {
			// unused
		}

		public void onProviderEnabled(String provider) {
			// unused
		}

		/**
		 * Initializes location when location status changes.
		 */
		public void onStatusChanged(String provider, int status, Bundle extras) {
			sensor.location.nullify();
		}
	};

	/**
	 * Registers sensor event listeners.
	 */
	protected void register() {
		for (int sensor : Conf.SENSORS)
			sensorManager.registerListener(sensorEventListener,
					sensorManager.getDefaultSensor(sensor),
					SensorManager.SENSOR_DELAY_UI);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, locationListener);
	}

	/**
	 * Unregisters sensor event listeners.
	 */
	protected void unregister() {
		sensorManager.unregisterListener(sensorEventListener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, locationListener);
	}

	/**
	 * Handles proximity event.
	 * 
	 * @param sensor
	 *            event
	 */
	private void getProximity(SensorEvent event) {
		sensor.proximity.x = event.values[0];
	}

	/**
	 * Handles magnetic field event.
	 * 
	 * @param sensor
	 *            event
	 */
	private void getMagneticField(SensorEvent event) {
		sensor.magfield.x = event.values[0];
		sensor.magfield.y = event.values[1];
		sensor.magfield.z = event.values[2];
	}

	/**
	 * Handles light event.
	 * 
	 * @param sensor
	 *            event
	 */
	private void getLight(SensorEvent event) {
		sensor.light.x = event.values[0];
	}

	/**
	 * Handles gyroscope event.
	 * 
	 * @param sensor
	 *            event
	 */
	private void getGyroscope(SensorEvent event) {
		sensor.gyroscope.x = event.values[0];
		sensor.gyroscope.y = event.values[1];
		sensor.gyroscope.z = event.values[2];
	}

	/**
	 * Handles gravity event.
	 * 
	 * @param sensor
	 *            event
	 */
	private void getGravity(SensorEvent event) {
		sensor.gravity.x = event.values[0];
		sensor.gravity.y = event.values[1];
		sensor.gravity.z = event.values[2];
	}

	/**
	 * Handles accelerometer event.
	 * 
	 * @param sensor
	 *            event
	 */
	private void getAccelerometer(SensorEvent event) {
		sensor.accelerometer.x = event.values[0];
		sensor.accelerometer.y = event.values[1];
		sensor.accelerometer.z = event.values[2];
	}
}
