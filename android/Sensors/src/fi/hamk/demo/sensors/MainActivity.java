package fi.hamk.demo.sensors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import fi.hamk.demo.sensors.models.Sensor;

/**
 * Main view.
 * 
 * @author Sami Hostikka
 */
public class MainActivity extends SherlockFragmentActivity implements
		ActionBar.TabListener {

	ActionBar actionbar;
	Utils utils;
	Helper helper;
	Sensor sensor;

	/**
	 * Initializes view.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		utils = new Utils(this);
		helper = new Helper(this);

		// ActionBarSherlock settings
		actionbar = getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// ActionBarSherlock add tabs
		for (String title : Conf.TABS) {
			ActionBar.Tab tab = getSupportActionBar().newTab();
			tab.setText(title);
			tab.setTabListener(this);
			getSupportActionBar().addTab(tab);
		}
		helper.status();
	}

	/**
	 * Adds buttons to menu.
	 * 
	 * @return true
	 */
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

	/**
	 * Handles menus click events.
	 * 
	 * @param clicked
	 *            menuItem
	 * @return true
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		if (menuItem.getTitle().equals(getString(R.string.settings))) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		} else {
			sensor = utils.getSensor();
			actionbar.selectTab(actionbar.getSelectedTab());
			helper.upload(sensor);
		}
		return true;
	}

	/**
	 * Shows fragment when tab is selected.
	 */
	// @Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		helper.fragmentify(tab, fragmentTransaction, sensor);
	}

	/**
	 * Shows fragment when tab is reselected.
	 */
	// @Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
		helper.fragmentify(tab, fragmentTransaction, sensor);
	}

	/**
	 * Unregisters app
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		utils.unregister();
	}

	/**
	 * Registers app
	 */
	@Override
	protected void onResume() {
		super.onResume();
		utils.register();
		helper.status();
	}

	/**
	 * Unregisters app
	 */
	@Override
	protected void onPause() {
		super.onPause();
		utils.unregister();
	}

	// @Override
	public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
		// unused
	}
}
