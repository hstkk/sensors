package fi.hamk.models;

import java.util.List;
import android.net.wifi.ScanResult;
import org.json.*;

/**
 * @author Sami Hostikka
 */
public class Sensor {

	public Location location;
	public Device device;
	public Network network;
	public List<ScanResult> wifi;
	public Double light;

	public Sensor() {
	}

	public String toJson() {
		JSONArray json = new JSONArray();
		json.put(this);
		return json.toString();
	}
}