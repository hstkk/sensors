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
	
	public void magic(View view){
		Network n = Utils.getNetwork(this);
		Device d = Utils.getDevice();
		textView.setText("Operator " + n.operator + "\nCell " + n.cell
				+ "\nType " + n.type + "\nroaming " + n.isNetworkRoaming+"\n\nbrand " + d.brand + "\ndevice " + d.device + "\nmanufacturer " + d.manufacturer + "\nmodel " + d.model + "\nproduct " + d.product + "\nandroid " + d.version);
	}
}