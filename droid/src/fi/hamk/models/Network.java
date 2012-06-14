package fi.hamk.models;

import java.security.MessageDigest;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

/**
 * @author Sami Hostikka
 */
public class Network {

	public String deviceId;
	public String operator;
	public String type;
	public boolean isNetworkRoaming;
	public Integer cell = null;

	public Network() {
	}

	public Network(final TelephonyManager telephonyManager) {
		GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager
				.getCellLocation();
		if (gsmCellLocation != null)
			cell = gsmCellLocation.getCid();
		this.deviceId = encrypt(telephonyManager.getDeviceId());
		this.operator = telephonyManager.getNetworkOperatorName();
		this.isNetworkRoaming = telephonyManager.isNetworkRoaming();
		switch (telephonyManager.getNetworkType()) {
		case TelephonyManager.NETWORK_TYPE_1xRTT:
			this.type = "1xRTT";
			break;
		case TelephonyManager.NETWORK_TYPE_CDMA:
			this.type = "CDMA";
			break;
		case TelephonyManager.NETWORK_TYPE_EDGE:
			this.type = "EDGE";
			break;
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
			this.type = "EVDO";
			break;
		case TelephonyManager.NETWORK_TYPE_GPRS:
			this.type = "GPRS";
			break;
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			this.type = "HSDPA";
			break;
		case TelephonyManager.NETWORK_TYPE_HSPA:
			this.type = "HSPA";
			break;
		case TelephonyManager.NETWORK_TYPE_HSUPA:
			this.type = "HSUPA";
			break;
		case TelephonyManager.NETWORK_TYPE_IDEN:
			this.type = "iDen";
			break;
		case TelephonyManager.NETWORK_TYPE_UMTS:
			this.type = "UMTS";
			break;
		default:
			this.type = "unknown";
			break;
		}
	}

	private String encrypt(String id) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.reset();
			messageDigest.update(id.getBytes("UTF-8"));
			return messageDigest.digest().toString();
		} catch (Exception e) {
			return null;
		}
	}
}
