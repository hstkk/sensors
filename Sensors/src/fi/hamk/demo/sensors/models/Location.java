package fi.hamk.demo.sensors.models;

import java.util.LinkedHashMap;
import java.util.Map;

import fi.hamk.demo.sensors.Utils;

/**
 * @author Pontus Vainionpaa
 */
public class Location {

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

	public Map<String, String> mapify() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("altitude", Utils.stringify(altitude));
		map.put("latitude", Utils.stringify(latitude));
		map.put("longitude", Utils.stringify(longitude));
		map.put("accuracy", Utils.stringify(accuracy));
		map.put("speed", Utils.stringify(speed));
		map.put("provider", Utils.stringify(provider));
		map.put("satellites", Utils.stringify(satellites));
		return map;
	}
}