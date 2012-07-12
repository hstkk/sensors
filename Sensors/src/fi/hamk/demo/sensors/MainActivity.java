package fi.hamk.demo.sensors;

import android.content.Context;
import android.content.Intent;
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

/**
 * @author Sami Hostikka
 */
public class MainActivity extends SherlockActivity implements
		ActionBar.TabListener {

	final String[] TABS = { "network", "device", "location" };
	TextView textView;
	Utils utils;
	Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = this;
		utils = new Utils(this);

		textView = (TextView) findViewById(R.id.textView);

		// ActionBarSherlock settings
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// ActionBarSherlock add tabs
		for (String title : TABS) {
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
		menu.add(getString(R.string.upload)).setIcon(R.drawable.ic_av_upload)
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
			upload();
		return true;
	}

	private void upload() {
		Handler handler = new Handler() {
			public void handleMessage(Message message) {
				android.widget.Toast.makeText(context, message.what,
						android.widget.Toast.LENGTH_LONG).show();
				switch (message.what) {
					case Connection.OK:
						break;
					case Connection.INTERNAL_SERVER_ERROR:
						break;
					case Connection.CONNECTION_ERROR:
						break;
					case Connection.ERROR:
						break;
				}
			}
		};
		new Connection("http://example.com", "asd", handler);
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
