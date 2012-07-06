package fi.hamk.demo.sensors;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;
import android.widget.Toast;

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("refresh").setIcon(R.drawable.ic_action_refresh)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		menu.add("settings").setIcon(R.drawable.ic_action_settings)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		Toast.makeText(this, menuItem.toString(),
				Toast.LENGTH_SHORT).show();
		return true;
	}

	// @Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		textView.setText(tab.getText());
	}

	// @Override
	public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
	}

	// @Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
	}
}
