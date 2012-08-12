package fi.hamk.demo.sensors.models;

import java.util.ArrayList;
import java.util.List;

import fi.hamk.demo.sensors.Utils;

/**
 * @author Pontus Vainionpaa
 */
public class Gyroscope {

	public Float x = null, y = null, z = null;

	public List<KeyValue> mapify() {
		List<KeyValue> map = new ArrayList<KeyValue>();
		map.add(new KeyValue("X", Utils.stringify(x)));
		map.add(new KeyValue("Y", Utils.stringify(y)));
		map.add(new KeyValue("Z", Utils.stringify(z)));
		return map;
	}
}
