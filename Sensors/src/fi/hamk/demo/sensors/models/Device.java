package fi.hamk.demo.sensors.models;

import java.util.LinkedHashMap;
import java.util.Map;

import fi.hamk.demo.sensors.Utils;
import android.os.Build;

/**
 * @author Sami Hostikka
 */
public class Device {

	public String manufacturer = null;
	public String version = null;
	public String brand = null;
	public String model = null;

	public Device() {
		this.manufacturer = Build.MANUFACTURER;
		this.version = Build.VERSION.RELEASE;
		this.brand = Build.BRAND;
		this.model = Build.MODEL;
	}

	public Map<String, String> mapify() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("Manufacturer", Utils.stringify(manufacturer));
		map.put("Version", Utils.stringify(version));
		map.put("Brand", Utils.stringify(brand));
		map.put("Model", Utils.stringify(model));
		return map;
	}
}