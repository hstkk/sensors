package fi.hamk.demo.sensors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import fi.hamk.demo.sensors.models.*;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.net.wifi.ScanResult;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * @author Sami Hostikka
 */
public class Utils extends Sensors implements Runnable {

	public Utils(Context context) {
		super(context);
		register();
		thread = new Thread(this, "KK");
		preferences = context.getSharedPreferences(
				context.getString(R.string.preferences), 0);
		transmissionQueue = new ArrayList<Sensor>();
	}

	Thread thread;
	SharedPreferences preferences;
	final int TIMEOUT = 10000; // milliseconds
	ArrayList<Sensor> transmissionQueue;

	private Sensor getSensor() {
		sensor.measured = new Date();
		sensor.location = getLocation();
		sensor.device = getDevice();
		sensor.network = getNetwork();
		sensor.wifi = getWifi();
		return sensor;
	}

	private List<ScanResult> getWifi() {
		try {
			WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			if (!wifiManager.isWifiEnabled())
				return null;
			return wifiManager.getScanResults();
		} catch (Exception e) {
			return null;
		}
	}

	private Network getNetwork() {
		return new Network(
				(TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE));
	}

	private Device getDevice() {
		return new Device();
	}

	private Location getLocation() {
		return new Location(
				(LocationManager) context
						.getSystemService(Context.LOCATION_SERVICE));
	}

	private void send(Sensor sensor) {
		try {
			android.util.Log.i("send", Thread.currentThread().getName());
			String json = sensor.toJson();
			if (json != null) {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(preferences.getString(
						context.getString(R.string.preferences_url),
						"http://127.0.0.1") + "/json");
				httpPost.setEntity(new StringEntity(json));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				String status = null;
				switch (httpResponse.getStatusLine().getStatusCode()) {
					case 200:
						status = context.getString(R.string.ok);
						transmissionQueue.remove(sensor);
						break;
					case 500:
						status = context.getString(R.string.int_err) + " "
								+ context.getString(R.string.queue);
						break;
					default:
						status = context.getString(R.string.err) + " "
								+ context.getString(R.string.queue);
						break;
				}
				toastify(status);
			}
		} catch (Exception e) {
			toastify(context.getString(R.string.connection_err) + " "
					+ context.getString(R.string.queue));
		}
	}

	private void toastify(final String message) {
		//Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	public void addToQueue() {
		if (!thread.isAlive())
			thread.start();
		transmissionQueue.add(getSensor());
	}

	public void flushQueue() {
		transmissionQueue.clear();
	}

	public void register() {
		super.register();
	}

	public void unregister() {
		super.unregister();
	}

	public void run() {
		toastify(""+transmissionQueue.size());
		while (!transmissionQueue.isEmpty())
			send(transmissionQueue.get(0));
	}
}
