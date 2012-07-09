package fi.hamk.demo.sensors.models;

import java.util.Date;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import android.net.wifi.ScanResult;

/**
 * @author Sami Hostikka
 */
public class Sensor {

	public Date measured = null;
	public Location location = null;
	public Device device = null;
	public Network network = null;
	public List<ScanResult> wifi = null;
	public Double light = null;

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