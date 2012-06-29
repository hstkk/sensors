package fi.hamk;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import fi.hamk.models.*;
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

	public Sensor getSensor() {
		Sensor sensor = new Sensor();
		sensor.location = getLocation();
		sensor.device = getDevice();
		sensor.network = getNetwork();
		sensor.wifi = getWifi();
		sensor.light = getLight();
		return sensor;
	}

	// TODO try catch
	private List<ScanResult> getWifi() {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (!wifiManager.isWifiEnabled())
			return null;
		return wifiManager.getScanResults();
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
}
