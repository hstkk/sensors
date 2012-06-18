package fi.hamk.models;

import android.os.Build;

/**
 * @author Sami Hostikka
 */
public class Device {

	public String manufacturer;
	public String version;
	public String brand;
	public String model;

	public Device() {
		this.manufacturer = Build.MANUFACTURER;
		this.version = Build.VERSION.RELEASE;
		this.brand = Build.BRAND;
		this.model = Build.MODEL;
	}
}