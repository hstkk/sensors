package fi.hamk.demo.sensors.models;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import fi.hamk.demo.sensors.Utils;
import android.net.wifi.ScanResult;

/**
 * @author Sami Hostikka & Pontus Vainionpaa
 */
public class Sensor {

	public Date measured = null;
	public Location location = new Location();
	public Device device = new Device();
	public Network network = new Network();;
	public List<ScanResult> wifi = null;
	public Accelerometer accelerometer = new Accelerometer();
	public Proximity proximity = new Proximity();
	public Gravity gravity = new Gravity();
	public Gyroscope gyroscope = new Gyroscope();
	public Light light = new Light();
	public MagneticField magfield = new MagneticField();

	public Sensor() {
	}

	public String toJson() {
		try {
			return new ObjectMapper().valueToTree(this).toString();
		} catch (Exception e) {
			return null;
		}
	}

	public Map<String, String> mapify() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (measured != null)
			map.put("Measured", Utils.stringify(measured));
		if (proximity != null)
			map.put("Proximity", Utils.stringify(proximity.x));
		if (light != null)
			map.put("Light", Utils.stringify(light.x));
		return map;
	}

	public Map<String, String> mapifyWifi() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (wifi != null) {
			for (ScanResult scanResult : wifi) {
				map.put("SSID", Utils.stringify(scanResult.SSID));
				map.put("BSSID", Utils.stringify(scanResult.BSSID));
				map.put("Capabilities", Utils.stringify(scanResult.capabilities));
				map.put("Frequency", Utils.stringify(scanResult.frequency));
				map.put("Level", Utils.stringify(scanResult.level));
				map.put("", "");
			}
		}
		return map;
	}
}