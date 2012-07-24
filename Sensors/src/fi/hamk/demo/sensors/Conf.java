package fi.hamk.demo.sensors;

import android.hardware.Sensor;

/**
 * @author Sami Hostikka
 */
public class Conf {

	public static final int STATUS_OK = 200;
	public static final int STATUS_INTERNAL_SERVER_ERROR = -500;
	public static final int STATUS_ERROR = -1;
	public static final int STATUS_CONNECTION_ERROR = -2;
	public static final int CONNECTION_TIMEOUT = 10000; // milliseconds
	public static final int CONNECTION_SLEEP = 30000; // milliseconds
	public static final int CONNECTION_MANAGER_WORKERS = 5;
	public static final String DEFAULT_SERVER = "http://127.0.0.1";
	public static final int DEFAULT_SERVER_PORT = 9000;
	public static final String SERVER_PATH = "json";
	public static final String[] TABS = { "network", "device", "location" };
	public static final String FILE_QUEUE = "queue";
	public static final String FILE_RUNNING = "running";
	public static final int[] SENSORS = { Sensor.TYPE_ACCELEROMETER,
			Sensor.TYPE_GRAVITY, Sensor.TYPE_GYROSCOPE, Sensor.TYPE_LIGHT,
			Sensor.TYPE_MAGNETIC_FIELD, Sensor.TYPE_PROXIMITY };

}
