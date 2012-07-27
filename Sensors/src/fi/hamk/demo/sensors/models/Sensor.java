package fi.hamk.demo.sensors.models;

import java.util.Date;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
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
}