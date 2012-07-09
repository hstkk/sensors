package fi.hamk.demo.sensors;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import fi.hamk.demo.sensors.R;

public class SettingsActivity extends SherlockActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		// ActionBarSherlock settings
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("save").setIcon(R.drawable.ic_content_save)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		return true;
	}
}
