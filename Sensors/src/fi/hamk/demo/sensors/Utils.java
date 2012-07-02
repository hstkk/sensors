package fi.hamk.demo.sensors;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import fi.hamk.demo.sensors.models.*;
import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.net.wifi.ScanResult;
import android.telephony.TelephonyManager;

/**
 * @author Sami Hostikka
 */
public class Utils {

	public Utils(Context context) {
		this.context = context;
	}

	Context context;
	final String URI = "http://example.com"; // server
	final int TIMEOUT = 10000; // milliseconds

	public Sensor getSensor() {
		Sensor sensor = new Sensor();
		sensor.location = getLocation();
		sensor.device = getDevice();
		sensor.network = getNetwork();
		sensor.wifi = getWifi();
		sensor.light = getLight();
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

	private Double getLight() {
		return null;
	}

	public void send() {
		try {
			String json = getSensor().toJson();
			if (json != null) {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(URI);
				httpPost.setEntity(new StringEntity(json));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				System.out.println(httpResponse.getStatusLine());
			}
		} catch (Exception e) {
		}
	}
}
