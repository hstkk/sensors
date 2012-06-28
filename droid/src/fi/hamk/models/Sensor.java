package fi.hamk.models;

import java.util.List;
import android.net.wifi.ScanResult;

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

	//TODO
	public String toJson(){
		return null;
	}
}