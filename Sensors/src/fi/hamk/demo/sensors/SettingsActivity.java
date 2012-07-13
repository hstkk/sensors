package fi.hamk.demo.sensors;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class SettingsActivity extends SherlockActivity {
	EditText url, port;
	SharedPreferences preferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		url = (EditText) findViewById(R.id.url);
		port = (EditText) findViewById(R.id.port);
		preferences = getSharedPreferences(getString(R.string.preferences), 0);
		url.setText(preferences.getString(getString(R.string.preferences_url),
				Conf.DEFAULT_SERVER));
		port.setText(preferences.getInt(getString(R.string.preferences_port),
				Conf.DEFAULT_SERVER_PORT) + "");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(getString(R.string.save)).setIcon(R.drawable.ic_content_save)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		int value = -1;
		try {
			value = Integer.parseInt(port.getText().toString());
		} catch (Exception e) {
			// unused
		}
		if (!URLUtil.isValidUrl(url.getText().toString()))
			Toast.makeText(this, getString(R.string.save_err),
					Toast.LENGTH_SHORT).show();
		else if (!(value >= 1 && value <= 65535))
			Toast.makeText(this, getString(R.string.port_err),
					Toast.LENGTH_SHORT).show();
		else {
			Editor editor = preferences.edit();
			editor.putString(getString(R.string.preferences_url), url.getText()
					.toString());
			editor.putInt(getString(R.string.preferences_port), value);
			editor.commit();
			finish();
		}
		return true;
	}

	public void flush(View view) {
		ConnectionManager.getConnectionManager().flush();
	}
}
