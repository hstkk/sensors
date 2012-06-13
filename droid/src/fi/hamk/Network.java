package fi.hamk;

import java.util.List;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.ScanResult;

import android.widget.Toast;

public class Network {

	Context context;

	public Network(Context context) {
		this.context = context;
	}

	public void getWifi() {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (!wifiManager.isWifiEnabled())
			Toast.makeText(context, "FAIL", 5).show();
		List<ScanResult> wifis = wifiManager.getScanResults();
		for (ScanResult wifi : wifis)
			Toast.makeText(
					context,
					"SSID " + wifi.SSID + "\nBSSID " + wifi.BSSID + "\nlevel "
							+ wifi.level, 5).show();
	}
}
