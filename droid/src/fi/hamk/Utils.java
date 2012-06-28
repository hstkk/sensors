package fi.hamk;

import java.util.List;

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
		return new Sensor(this);
	}

	// TODO try catch
	public List<ScanResult> getWifi() {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (!wifiManager.isWifiEnabled())
			return null;
		return wifiManager.getScanResults();
	}

	public Network getNetwork() {
		return new Network(
				(TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE));
	}

	public Device getDevice() {
		return new Device();
	}

	public Location getLocation() {
		return new Location(
				(LocationManager) context
						.getSystemService(Context.LOCATION_SERVICE));
	}

	public Double getLight() {
		return null;
	}
}
