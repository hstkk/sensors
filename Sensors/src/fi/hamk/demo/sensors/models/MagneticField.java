package fi.hamk.demo.sensors.models;

import java.util.LinkedHashMap;
import java.util.Map;

import fi.hamk.demo.sensors.Utils;

/**
 * @author Pontus Vainionpaa
 */
public class MagneticField {

	public Float x = null, y = null, z = null;

	public Map<String, String> mapify() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("X", Utils.stringify(x));
		map.put("Y", Utils.stringify(y));
		map.put("Z", Utils.stringify(z));
		return map;
	}
}
