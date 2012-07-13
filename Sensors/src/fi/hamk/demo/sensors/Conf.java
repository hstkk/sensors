package fi.hamk.demo.sensors;

/**
 * @author Sami Hostikka
 */
public class Conf {

	public static final int STATUS_OK = 200;
	public static final int STATUS_INTERNAL_SERVER_ERROR = 500;
	public static final int STATUS_ERROR = -1;
	public static final int STATUS_CONNECTION_ERROR = 2;
	public static final int CONNECTION_TIMEOUT = 10000; // milliseconds
	public static final int CONNECTION_SLEEP = 30000; // milliseconds
	public static final int CONNECTION_MANAGER_WORKERS = 5;
	public static final String DEFAULT_SERVER = "http://127.0.0.1";
	public static final String SERVER_PATH = "json";
	public static final int SERVER_PORT = 9000;
	public static final String[] TABS = { "network", "device", "location" };

}
