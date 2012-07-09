package fi.hamk.demo.sensors;

import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class SettingsActivity extends SherlockActivity {
	EditText url;
	CheckBox shake;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		url = (EditText) findViewById(R.id.url);
		shake = (CheckBox) findViewById(R.id.shake);
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
			Toast.makeText(this, "Server is not valid", Toast.LENGTH_SHORT)
					.show();
		else {
			finish();
		}
		return true;
	}
}
