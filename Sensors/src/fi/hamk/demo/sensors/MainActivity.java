package fi.hamk.demo.sensors;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textView);
	}

	public void magic(View view) {
		// new Utils(this).send();
		// textView.setText(new Utils(this).getSensor().toJson());
		new Utils(this).send();
	}
}
