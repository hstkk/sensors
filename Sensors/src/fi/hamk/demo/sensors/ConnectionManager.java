package fi.hamk.demo.sensors;

import java.util.ArrayList;

/**
 * @author Sami Hostikka
 */
public class ConnectionManager {

	ArrayList<Runnable> running = new ArrayList<Runnable>();
	ArrayList<Runnable> queue = new ArrayList<Runnable>();
	static ConnectionManager connectionManager;
	final int workers = 5;

	public static ConnectionManager getConnectionManager() {
		if (connectionManager == null)
			connectionManager = new ConnectionManager();
		return connectionManager;
	}

	public void add(Runnable runnable) {
		queue.add(runnable);
		if (running.size() < workers)
			next();
	}

	public void flush() {
		queue.clear();
		running.clear();
	}

	private void next() {
		if (!queue.isEmpty()) {
			Runnable runnable = queue.get(0);
			queue.remove(runnable);
			running.add(runnable);
			new Thread(runnable).start();
		}
	}

	public void done(Runnable runnable) {
		running.remove(runnable);
		next();
	}
}
