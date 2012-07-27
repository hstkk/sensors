package fi.hamk.demo.sensors;

import java.util.Date;
import java.util.List;
import fi.hamk.demo.sensors.models.*;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.net.wifi.WifiManager;
import android.net.wifi.ScanResult;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

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
		getNetwork();
		sensor.wifi = getWifi();
		getVolume();
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
			sensor.network.technology = "1xRTT";
			break;
		case TelephonyManager.NETWORK_TYPE_CDMA:
			sensor.network.technology = "CDMA";
			break;
		case TelephonyManager.NETWORK_TYPE_EDGE:
			sensor.network.technology = "EDGE";
			break;
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
			sensor.network.technology = "EVDO";
			break;
		case TelephonyManager.NETWORK_TYPE_GPRS:
			sensor.network.technology = "GPRS";
			break;
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			sensor.network.technology = "HSDPA";
			break;
		case TelephonyManager.NETWORK_TYPE_HSPA:
			sensor.network.technology = "HSPA";
			break;
		case TelephonyManager.NETWORK_TYPE_HSUPA:
			sensor.network.technology = "HSUPA";
			break;
		case TelephonyManager.NETWORK_TYPE_IDEN:
			sensor.network.technology = "iDen";
			break;
		case TelephonyManager.NETWORK_TYPE_UMTS:
			sensor.network.technology = "UMTS";
			break;
		default:
			sensor.network.technology = "unknown";
			break;
		}
	}

	private void getVolume() {
		MediaRecorder mediaRecorder = null;
		try {
			mediaRecorder = new MediaRecorder();
			mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
			mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			mediaRecorder.setOutputFile("/dev/null");
			mediaRecorder.prepare();
			mediaRecorder.start();
			sensor.volume = mediaRecorder.getMaxAmplitude();
		} catch (Exception e) {
			sensor.volume = null;
		} finally {
			try {
				mediaRecorder.reset();
				mediaRecorder.release();
				mediaRecorder = null;
			} catch (Exception e) {
			}
		}
	}

	public void register() {
		super.register();
		ConnectionManager.getConnectionManager().load(context);
	}

	public void unregister() {
		super.unregister();
		ConnectionManager.getConnectionManager().save(context);
	}

	public static String urlify(Context context, SharedPreferences preferences) {
		StringBuilder url = new StringBuilder();
		url.append(preferences.getString(
				context.getString(R.string.preferences_url),
				Conf.DEFAULT_SERVER));
		url.append(":");
		url.append(preferences.getInt(
				context.getString(R.string.preferences_port),
				Conf.DEFAULT_SERVER_PORT));
		url.append("/");
		return url.toString();
	}
}
