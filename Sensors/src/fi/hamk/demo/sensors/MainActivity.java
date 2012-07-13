package fi.hamk.demo.sensors;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import fi.hamk.demo.sensors.models.Sensor;

/**
 * @author Sami Hostikka
 */
public class MainActivity extends SherlockActivity implements
		ActionBar.TabListener {

	TextView textView;
	Utils utils;
	NotificationManager notificationManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		notificationManager = (NotificationManager) this
				.getSystemService(NOTIFICATION_SERVICE);

		utils = new Utils(this);
		textView = (TextView) findViewById(R.id.textView);

		// ActionBarSherlock settings
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// ActionBarSherlock add tabs
		for (String title : Conf.TABS) {
			ActionBar.Tab tab = getSupportActionBar().newTab();
			tab.setText(title);
			tab.setTabListener(this);
			getSupportActionBar().addTab(tab);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		utils.unregister();
	}

	@Override
	protected void onResume() {
		super.onResume();
		utils.register();
	}

	@Override
	protected void onPause() {
		super.onPause();
		utils.unregister();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(getString(R.string.refresh))
				.setIcon(R.drawable.ic_action_refresh)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		menu.add(getString(R.string.settings))
				.setIcon(R.drawable.ic_action_settings)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		if (menuItem.getTitle().equals(getString(R.string.settings))) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivityForResult(intent, 0);
		} else
			upload(utils.getSensor());
		return true;
	}

	private void upload(Sensor sensor) {
		Handler handler = new Handler() {
			public void handleMessage(Message message) {
				String title = null, text = getString(R.string.queue);
				int id = -1;
				switch (message.what) {
					case Conf.STATUS_OK:
						title = getString(R.string.ok);
						text = getString(R.string.ok_text);
						id = Conf.STATUS_OK;
						break;
					case Conf.STATUS_INTERNAL_SERVER_ERROR:
						title = getString(R.string.int_err);
						break;
					case Conf.STATUS_CONNECTION_ERROR:
						title = getString(R.string.connection_err);
						break;
					case Conf.STATUS_ERROR:
						title = getString(R.string.err);
						break;
				}
				notification(id, title, text);
			}
		};
		new Connection(this, sensor, handler);
	}

	@SuppressWarnings("deprecation")
	private void notification(int id, String title, String text) {
		Notification notification = new Notification();
		notification.icon = R.drawable.ic_launcher;
		notification.tickerText = title;
		notification.when = System.currentTimeMillis();
		Intent intent = id > 0 ? new Intent(this, SettingsActivity.class)
				: new Intent();
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, 0);
		notification.setLatestEventInfo(this, title, text, pendingIntent);
		notificationManager.notify(id, notification);
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (resultCode == RESULT_OK)
			ConnectionManager.getConnectionManager().flush();
	}

	// @Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		textView.setText(tab.getText());
	}

	// @Override
	public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
		// unused
	}

	// @Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
		// unused
	}
}
