package fi.hamk.models;

import android.os.Build;

/**
 * @author Sami Hostikka
 */
public class Device {

	public String device;
	public String manufacturer;
	public String version;
	public String brand;
	public String model;
	public String product;

	public Device() {
		this.device = Build.DEVICE;
		this.manufacturer = Build.MANUFACTURER;
		this.version = Build.VERSION.RELEASE;
		this.brand = Build.BRAND;
		this.model = Build.MODEL;
		this.product = Build.PRODUCT;
	}
}