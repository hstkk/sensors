package fi.hamk.demo.sensors;

import java.util.Date;
import java.util.List;
import fi.hamk.demo.sensors.models.*;
import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.net.wifi.ScanResult;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

/**
 * @author Sami Hostikka
 */
public class Utils extends Sensors {

	public Utils(Context context) {
		super(context);
		register();
	}

	public Sensor getSensor() {
		sensor.measured = new Date();
		sensor.location = getLocation();
		getNetwork();
		sensor.wifi = getWifi();
		return sensor;
	}

	private List<ScanResult> getWifi() {
		try {
			WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			if (wifiManager.isWifiEnabled())
				return wifiManager.getScanResults();
		} catch (Exception e) {
		}
		return null;
	}

	private void getNetwork() {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager
				.getCellLocation();
		if (gsmCellLocation != null)
			sensor.network.cell = gsmCellLocation.getCid();
		else
			sensor.network.cell = null;
		sensor.network.operator = telephonyManager.getNetworkOperatorName();
		sensor.network.isNetworkRoaming = telephonyManager.isNetworkRoaming();
		switch (telephonyManager.getNetworkType()) {
			case TelephonyManager.NETWORK_TYPE_1xRTT:
				sensor.network.type = "1xRTT";
				break;
			case TelephonyManager.NETWORK_TYPE_CDMA:
				sensor.network.type = "CDMA";
				break;
			case TelephonyManager.NETWORK_TYPE_EDGE:
				sensor.network.type = "EDGE";
				break;
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
			case TelephonyManager.NETWORK_TYPE_EVDO_B:
				sensor.network.type = "EVDO";
				break;
			case TelephonyManager.NETWORK_TYPE_GPRS:
				sensor.network.type = "GPRS";
				break;
			case TelephonyManager.NETWORK_TYPE_HSDPA:
				sensor.network.type = "HSDPA";
				break;
			case TelephonyManager.NETWORK_TYPE_HSPA:
				sensor.network.type = "HSPA";
				break;
			case TelephonyManager.NETWORK_TYPE_HSUPA:
				sensor.network.type = "HSUPA";
				break;
			case TelephonyManager.NETWORK_TYPE_IDEN:
				sensor.network.type = "iDen";
				break;
			case TelephonyManager.NETWORK_TYPE_UMTS:
				sensor.network.type = "UMTS";
				break;
			default:
				sensor.network.type = "unknown";
				break;
		}
	}

	private Location getLocation() {
		return new Location(
				(LocationManager) context
						.getSystemService(Context.LOCATION_SERVICE));
	}
}
