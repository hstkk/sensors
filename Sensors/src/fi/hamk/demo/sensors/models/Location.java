package fi.hamk.demo.sensors.models;

import java.util.ArrayList;
import java.util.List;

import fi.hamk.demo.sensors.Utils;

/**
 * Location model.
 * 
 * @author Pontus Vainionpaa
 */
public class Location {

	public Double altitude = null, latitude = null, longitude = null;
	public Float accuracy = null, speed = null;
	public String provider = null;
	public Integer satellites = null;

	public Location() {
	}

	/**
	 * Initializes values.
	 */
	public void nullify() {
		altitude = null;
		latitude = null;
		longitude = null;
		accuracy = null;
		speed = null;
		provider = null;
		satellites = null;
	}

	/**
	 * Returns map of measured values.
	 * 
	 * @return map of measured values.
	 */
	public List<KeyValue> mapify() {
		List<KeyValue> map = new ArrayList<KeyValue>();
		map.add(new KeyValue("altitude", Utils.stringify(altitude)));
		map.add(new KeyValue("latitude", Utils.stringify(latitude)));
		map.add(new KeyValue("longitude", Utils.stringify(longitude)));
		map.add(new KeyValue("accuracy", Utils.stringify(accuracy)));
		map.add(new KeyValue("speed", Utils.stringify(speed)));
		map.add(new KeyValue("provider", Utils.stringify(provider)));
		map.add(new KeyValue("satellites", Utils.stringify(satellites)));
		return map;
	}
}