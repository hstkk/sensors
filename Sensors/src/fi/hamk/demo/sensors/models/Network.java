package fi.hamk.demo.sensors.models;

import java.util.ArrayList;
import java.util.List;

import fi.hamk.demo.sensors.Utils;

/**
 * @author Sami Hostikka
 */
public class Network {

	public String operator = null;
	public String technology = null;
	public Boolean isNetworkRoaming = null;
	public Integer cell = null;

	public List<KeyValue> mapify() {
		List<KeyValue> map = new ArrayList<KeyValue>();
		map.add(new KeyValue("Operator", Utils.stringify(operator)));
		map.add(new KeyValue("Technology", Utils.stringify(technology)));
		map.add(new KeyValue("Roaming", Utils.stringify(isNetworkRoaming)));
		map.add(new KeyValue("Cell", Utils.stringify(cell)));
		return map;
	}
}
