package fi.hamk.demo.sensors.models;

import java.util.LinkedHashMap;
import java.util.Map;

import fi.hamk.demo.sensors.Utils;

/**
 * @author Sami Hostikka
 */
public class Network {

	public String operator = null;
	public String technology = null;
	public Boolean isNetworkRoaming = null;
	public Integer cell = null;

	public Map<String, String> mapify() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("Operator", Utils.stringify(operator));
		map.put("Technology", Utils.stringify(technology));
		map.put("Roaming", Utils.stringify(isNetworkRoaming));
		map.put("Cell", Utils.stringify(cell));
		return map;
	}
}
