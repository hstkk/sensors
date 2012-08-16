package fi.hamk.demo.sensors;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;

/**
 * Connection manager dominates connections.
 * 
 * @author Sami Hostikka
 */
public class ConnectionManager {

	ArrayList<Runnable> running = new ArrayList<Runnable>();
	ArrayList<Runnable> queue = new ArrayList<Runnable>();
	static ConnectionManager connectionManager;

	/**
	 * Return connection manager instance.
	 * 
	 * @return connection manager instance.
	 */
	public static ConnectionManager getConnectionManager() {
		if (connectionManager == null)
			connectionManager = new ConnectionManager();
		return connectionManager;
	}

	/**
	 * Adds connections to transmission queue.
	 * 
	 * @param runnable
	 *            connection thread
	 */
	public void add(Runnable runnable) {
		queue.add(runnable);
		next();
	}

	/**
	 * Flushes transmission queue and current connections.
	 */
	public void flush() {
		queue.clear();
		running.clear();
	}

	/**
	 * Executes next transmission.
	 */
	private void next() {
		if (!queue.isEmpty()
				&& running.size() < Conf.CONNECTION_MANAGER_WORKERS) {
			Runnable runnable = queue.get(0);
			queue.remove(runnable);
			running.add(runnable);
			new Thread(runnable).start();
		}
	}

	/**
	 * Removes executed connection.
	 * 
	 * @param runnable
	 *            executed connection
	 */
	public void done(Runnable runnable) {
		running.remove(runnable);
		next();
	}

	/**
	 * Saves queues to file.
	 * 
	 * @param context
	 *            current application context
	 */
	public void save(Context context) {
		ObjectOutputStream objectOutputStream = null;
		try {
			FileOutputStream fileOutputStream = context.openFileOutput(
					Conf.FILE_QUEUE, Context.MODE_PRIVATE);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(queue.addAll(running));
		} catch (Exception e) {
			// unused
		} finally {
			try {
				objectOutputStream.close();
			} catch (Exception e) {
				// unused
			}
		}
	}

	/**
	 * Loads saved queue from file.
	 * 
	 * @param context
	 *            current application context.
	 */
	@SuppressWarnings("unchecked")
	public void load(Context context) {
		if (queue.isEmpty() && running.isEmpty()) {
			ObjectInputStream objectInputStream = null;
			try {
				FileInputStream fileInputStream = context
						.openFileInput(Conf.FILE_QUEUE);
				objectInputStream = new ObjectInputStream(fileInputStream);
				queue = (ArrayList<Runnable>) objectInputStream.readObject();
			} catch (Exception e) {
				// unused
			} finally {
				try {
					objectInputStream.close();
				} catch (Exception e) {
					// unused
				}
			}
		}
		next();
	}

	/**
	 * Returns current queue size
	 * 
	 * @return current queue size
	 */
	public int status() {
		return queue.size() + running.size();
	}
}
