package fi.hamk;

import java.util.List;

import fi.hamk.models.Device;
import fi.hamk.models.Network;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.ScanResult;
import android.telephony.TelephonyManager;

/**
 * @author Sami Hostikka
 */
public class Utils {

	public static List<ScanResult> getWifi(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (!wifiManager.isWifiEnabled())
			return null;
		return wifiManager.getScanResults();
	}

	public static Network getNetwork(Context context) {
		return new Network(
				(TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE));
	}

	public static Device getDevice() {
		return new Device();
	}

	public static void getLocation() {

	}
}
