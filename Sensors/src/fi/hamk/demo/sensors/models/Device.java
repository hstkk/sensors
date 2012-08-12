package fi.hamk.demo.sensors.models;

import java.util.ArrayList;
import java.util.List;

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

	public List<KeyValue> mapify() {
		List<KeyValue> map = new ArrayList<KeyValue>();
		map.add(new KeyValue("Manufacturer", Utils.stringify(manufacturer)));
		map.add(new KeyValue("Version", Utils.stringify(version)));
		map.add(new KeyValue("Brand", Utils.stringify(brand)));
		map.add(new KeyValue("Model", Utils.stringify(model)));
		return map;
	}
}