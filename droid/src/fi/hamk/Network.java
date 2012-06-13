package fi.hamk;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.net.wifi.ScanResult;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

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

	public String getBluetooth() {
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled())
			return null;
		bluetoothAdapter.startDiscovery();
		while (bluetoothAdapter.isDiscovering())
			;
		BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
			List<BluetoothDevice> bluetoothDevices = new ArrayList<BluetoothDevice>();

			@Override
			public void onReceive(Context context, Intent intent) {
				bluetoothDevices.add((BluetoothDevice) intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE));
			}
		};
	}
}
