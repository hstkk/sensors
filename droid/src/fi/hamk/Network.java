package fi.hamk;

import java.util.List;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.ScanResult;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class Network {

	Context context;

	public Network(Context context) {
		this.context = context;
	}

	public List<ScanResult> getWifi() {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (!wifiManager.isWifiEnabled())
			return null;
		return wifiManager.getScanResults();
	}

	// http://developer.android.com/reference/android/telephony/TelephonyManager.html#getNetworkType%28%29
	public void getNetwork() {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		Toast.makeText(context,
				"operator " + telephonyManager.getNetworkOperatorName()
						+ "\nType " + telephonyManager.getNetworkType()
						+ "\nRoaming" + telephonyManager.isNetworkRoaming(), 50);
	}
}
