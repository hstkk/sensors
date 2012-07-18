package fi.hamk.demo.sensors.models;

import android.os.Build;

/**
 * @author Sami Hostikka
 */
public class Device extends Id {

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
}