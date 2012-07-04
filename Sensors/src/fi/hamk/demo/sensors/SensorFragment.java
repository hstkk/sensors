package fi.hamk.demo.sensors;

import fi.hamk.demo.sensors.models.Sensor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public final class SensorFragment extends Fragment {
	// static Sensor sensor = new Sensor();

	public static SensorFragment newInstance(String content) {
		SensorFragment sensorFragment = new SensorFragment();
		return sensorFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater layoutInflater,
			ViewGroup viewGroup, Bundle savedInstanceState) {
		return layoutInflater.inflate(R.layout.network, null);
	}
}