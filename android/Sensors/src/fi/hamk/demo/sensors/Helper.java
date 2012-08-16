package fi.hamk.demo.sensors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.actionbarsherlock.app.ActionBar.Tab;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import fi.hamk.demo.sensors.models.KeyValue;
import fi.hamk.demo.sensors.models.Sensor;

/**
 * UI helper class.
 * 
 * @author Sami Hostikka
 */
public class Helper {
	Context context;
	NotificationManager notificationManager;
	SharedPreferences preferences;

	/**
	 * @param current
	 *            application context
	 */
	public Helper(Context context) {
		this.context = context;
		notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		preferences = context.getSharedPreferences(
				context.getString(R.string.preferences), 0);
	}

	/**
	 * Adds sensor objects to transmission queue and receives transmission
	 * statuses.
	 * 
	 * @param sensor
	 *            object for upload
	 */
	public void upload(Sensor sensor) {
		Handler handler = new Handler() {
			public void handleMessage(Message message) {
				String title = null, text = context.getString(R.string.queue);
				int id = message.what;
				if (id > 0) {
					title = context.getString(R.string.ok);
					text = context.getString(R.string.ok_text);
				} else if (id == Conf.STATUS_INTERNAL_SERVER_ERROR)
					title = context.getString(R.string.int_err);
				else if (id == Conf.STATUS_CONNECTION_ERROR)
					title = context.getString(R.string.connection_err);
				else if (id == Conf.STATUS_ERROR)
					title = context.getString(R.string.err);
				notification(id, title, text);
			}
		};
		new Connection(context, sensor, handler);
	}

	/**
	 * Adds notification to androids notification center.
	 * 
	 * @param notification
	 *            id, if greater than 0 then notification opens web page
	 * @param notification
	 *            title
	 * @param notification
	 *            text
	 */
	@SuppressWarnings("deprecation")
	private void notification(int id, String title, String text) {
		Notification notification = new Notification();
		notification.icon = R.drawable.ic_launcher;
		notification.tickerText = title;
		notification.when = System.currentTimeMillis();
		Intent intent = id > 0 ? new Intent(Intent.ACTION_VIEW, Uri.parse(Utils
				.urlify(context, preferences) + id)) : new Intent();
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, 0);
		notification.setLatestEventInfo(context, title, text, pendingIntent);
		notificationManager.notify(id, notification);
	}

	/**
	 * Shows transmission status in notification.
	 */
	public void status() {
		int queueStatus = ConnectionManager.getConnectionManager().status();
		if (queueStatus > 0)
			notification(Conf.STATUS_ERROR,
					context.getString(R.string.to_be_continued), queueStatus
							+ " " + context.getString(R.string.pipeline));
	}

	/**
	 * Shows sensor data in UI fragment.
	 * 
	 * @param tab
	 * @param fragmentTransaction
	 * @param sensor
	 *            object to show in UI
	 */
	public void fragmentify(Tab tab, FragmentTransaction fragmentTransaction,
			Sensor sensor) {
		if (sensor != null) {
			List<KeyValue> map = null;
			final List<String> tabs = Arrays.asList(Conf.TABS);
			if (tab.getPosition() == tabs.indexOf("etc"))
				map = sensor.mapify();
			else if (tab.getPosition() == tabs.indexOf("location"))
				map = sensor.location.mapify();
			else if (tab.getPosition() == tabs.indexOf("accelerometer"))
				map = sensor.accelerometer.mapify();
			else if (tab.getPosition() == tabs.indexOf("gravity"))
				map = sensor.gravity.mapify();
			else if (tab.getPosition() == tabs.indexOf("gyroscope"))
				map = sensor.gyroscope.mapify();
			else if (tab.getPosition() == tabs.indexOf("magnetic field"))
				map = sensor.magfield.mapify();
			else if (tab.getPosition() == tabs.indexOf("device"))
				map = sensor.device.mapify();
			else if (tab.getPosition() == tabs.indexOf("network"))
				map = sensor.network.mapify();
			else if (tab.getPosition() == tabs.indexOf("wifi"))
				map = sensor.mapifyWifi();
			TabFragment tabFragment = new TabFragment(context, map);
			fragmentTransaction.replace(android.R.id.content, tabFragment);
		}
	}
}
