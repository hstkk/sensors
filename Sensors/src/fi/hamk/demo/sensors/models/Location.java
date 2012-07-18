package fi.hamk.demo.sensors.models;

/**
 * @author Pontus Vainionpaa
 */
public class Location extends Id {

	public Double altitude = null, latitude = null, longitude = null;
	public Float accuracy = null, speed = null;
	public String provider = null;
	public Integer satellites = null;

	public Location() {
	}

	public void nullify() {
		altitude = null;
		latitude = null;
		longitude = null;
		accuracy = null;
		speed = null;
		provider = null;
		satellites = null;
	}
}