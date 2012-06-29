package fi.hamk.models;

import java.util.List;
import android.net.wifi.ScanResult;
import org.codehaus.jackson.*;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.node.*;

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
		try {
			return new ObjectMapper().valueToTree(this).toString();
		} catch (Exception e) {
			return null;
		}
	}
}