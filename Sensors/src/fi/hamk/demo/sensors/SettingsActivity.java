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
	EditText url;
	SharedPreferences preferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		url = (EditText) findViewById(R.id.url);
		preferences = getSharedPreferences(getString(R.string.preferences), 0);
		url.setText(preferences.getString(getString(R.string.preferences_url),
				Conf.DEFAULT_SERVER));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(getString(R.string.save)).setIcon(R.drawable.ic_content_save)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		if (!URLUtil.isValidUrl(url.getText().toString()))
			Toast.makeText(this, getString(R.string.save_err),
					Toast.LENGTH_SHORT).show();
		else {
			Editor editor = preferences.edit();
			editor.putString(getString(R.string.preferences_url), url.getText()
					.toString());
			editor.commit();
			finish();
		}
		return true;
	}

	public void flush(View view){
		setResult(RESULT_OK);
	}
}
