package fi.hamk.demo.sensors;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			textView = (TextView) findViewById(R.id.textView);
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), 5000);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void magic(View view) {
		// new Utils(this).send();
		//textView.setText(new Utils(this).getSensor().toJson());
		new Utils(this).send();
	}
}
