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