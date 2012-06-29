package fi.hamk;

import fi.hamk.models.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DroidActivity extends Activity {
	TextView textView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textView = (TextView) findViewById(R.id.textView);
	}

	public void magic(View view) {
		new Utils(this).send();
		// textView.setText(sensor.toJson());
	}
}