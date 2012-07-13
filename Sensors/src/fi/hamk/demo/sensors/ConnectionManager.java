package fi.hamk.demo.sensors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		next();
	}

	public void flush() {
		queue.clear();
		running.clear();
	}

	private void next() {
		if (!queue.isEmpty()
				&& running.size() < Conf.CONNECTION_MANAGER_WORKERS) {
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
		save(context, Conf.FILE_QUEUE, queue);
		save(context, Conf.FILE_RUNNING, running);
	}

	private void save(Context context, String file, Object object) {
		ObjectOutputStream objectOutputStream = null;
		try {
			FileOutputStream fileOutputStream = context.openFileOutput(file,
					Context.MODE_PRIVATE);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(object);
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

	public void load(Context context) {
		queue = load(context, Conf.FILE_QUEUE);
		queue.addAll(load(context, Conf.FILE_RUNNING));
		next();
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Runnable> load(Context context, String file) {
		ArrayList<Runnable> result = new ArrayList<Runnable>();
		ObjectInputStream objectInputStream = null;
		try {
			FileInputStream fileInputStream = context.openFileInput(file);
			objectInputStream = new ObjectInputStream(fileInputStream);
			result = (ArrayList<Runnable>) objectInputStream.readObject();
		} catch (Exception e) {
			// unused
		} finally {
			try {
				objectInputStream.close();
			} catch (Exception e) {
				// unused
			}
		}
		return result;
	}

	public int status(){
		return queue.size() + running.size();
	}
}
