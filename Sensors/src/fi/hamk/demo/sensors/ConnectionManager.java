package fi.hamk.demo.sensors;

import java.util.ArrayList;

/**
 * @author Sami Hostikka
 */
public class ConnectionManager {

	ArrayList<Runnable> running = new ArrayList<Runnable>();
	ArrayList<Runnable> queue = new ArrayList<Runnable>();
	static ConnectionManager connectionManager;
	public static int workers = 5;

	public ConnectionManager() {
	}

	public ConnectionManager(int workers) {
		ConnectionManager.workers = workers;
	}

	public static ConnectionManager getConnectionManager() {
		if (connectionManager == null)
			connectionManager = new ConnectionManager(workers);
		return connectionManager;
	}

	public void add(Runnable runnable) {
		queue.add(runnable);
		if (running.size() < workers)
			next();
	}

	public void flush() {
		queue.clear();
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
