package fi.hamk.demo.sensors.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public List<KeyValue> mapify() {
		List<KeyValue> map = new ArrayList<KeyValue>();
		if (measured != null)
			map.add(new KeyValue("Measured", Utils.stringify(measured)));
		if (proximity != null)
			map.add(new KeyValue("Proximity", Utils.stringify(proximity.x)));
		if (light != null)
			map.add(new KeyValue("Light", Utils.stringify(light.x)));
		return map;
	}

	public List<KeyValue> mapifyWifi() {
		List<KeyValue> map = new ArrayList<KeyValue>();
		if (wifi != null) {
			for (ScanResult scanResult : wifi) {
				map.add(new KeyValue("SSID", Utils.stringify(scanResult.SSID)));
				map.add(new KeyValue("BSSID", Utils.stringify(scanResult.BSSID)));
				map.add(new KeyValue("Capabilities", Utils
						.stringify(scanResult.capabilities)));
				map.add(new KeyValue("Frequency", Utils
						.stringify(scanResult.frequency)));
				map.add(new KeyValue("Level", Utils.stringify(scanResult.level)));
				map.add(new KeyValue("", ""));
			}
		}
		return map;
	}
}