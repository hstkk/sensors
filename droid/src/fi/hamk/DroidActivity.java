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
		Sensor sensor= new Utils(this).getSensor();
		Network n = sensor.network;
		Device d = sensor.device;
		Location l = sensor.location;
		textView.setText("Operator " + n.operator + "\nCell " + n.cell
				+ "\nType " + n.type + "\nroaming " + n.isNetworkRoaming
				+ "\n\nbrand " + d.brand + "\nmanufacturer " + d.manufacturer
				+ "\nmodel " + d.model + "\nandroid " + d.version
				+ "\n\nAltitude " + l.altitude + "\nLatitude " + l.latitude
				+ "\nLongitude " + l.longitude);
	}
}