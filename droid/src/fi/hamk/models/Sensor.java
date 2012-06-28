package fi.hamk.models;

import java.util.List;
import fi.hamk.Utils;
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
	
	public Sensor(Utils utils) {
		this.location = utils.getLocation();
		this.device = utils.getDevice();
		this.network = utils.getNetwork();
		this.wifi = utils.getWifi();
		this.light = utils.getLight();
	}
}