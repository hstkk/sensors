package fi.hamk.demo.sensors.models;

import java.util.ArrayList;
import java.util.List;

import fi.hamk.demo.sensors.Utils;

/**
 * Gyroscope model.
 * 
 * @author Pontus Vainionpaa
 */
public class Gyroscope {

	public Float x = null, y = null, z = null;

	/**
	 * Returns map of measured values.
	 * 
	 * @return map of measured values.
	 */
	public List<KeyValue> mapify() {
		List<KeyValue> map = new ArrayList<KeyValue>();
		map.add(new KeyValue("X", Utils.stringify(x)));
		map.add(new KeyValue("Y", Utils.stringify(y)));
		map.add(new KeyValue("Z", Utils.stringify(z)));
		return map;
	}
}
