package fi.hamk.demo.sensors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;

/**
 * @author Sami Hostikka
 */
public class ConnectionManager {

	ArrayList<Runnable> running = new ArrayList<Runnable>();
	ArrayList<Runnable> queue = new ArrayList<Runnable>();
	static ConnectionManager connectionManager;

	public static ConnectionManager getConnectionManager() {
		if (connectionManager == null)
			connectionManager = new ConnectionManager();
		return connectionManager;
	}

	public void add(Runnable runnable) {
		queue.add(runnable);
		if (running.size() < Conf.CONNECTION_MANAGER_WORKERS)
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

	public void save(Context context) {
		save(context, Conf.FILE_QUEUE);
		save(context, Conf.FILE_RUNNING);
	}

	private void save(Context context, String file) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = context.openFileOutput(file,
					Context.MODE_PRIVATE);
			fileOutputStream.write(null);
		} catch (Exception e) {
			// unused
		} finally {
			try {
				fileOutputStream.close();
			} catch (Exception e) {
				// unused
			}
		}
	}

	public void load(Context context) {
		load(context, Conf.FILE_QUEUE);
		load(context, Conf.FILE_RUNNING);
	}

	private void load(Context context, String file) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = context.openFileInput(file);
			fileInputStream.read();
		} catch (Exception e) {
			// unused
		} finally {
			try {
				fileInputStream.close();
			} catch (Exception e) {
				// unused
			}
		}
	}
}
