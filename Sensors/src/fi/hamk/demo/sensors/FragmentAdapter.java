package fi.hamk.demo.sensors;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
	protected static final String[] TAB = new String[] { "NETWORK", "LOCATION",
			"HARDWARE", "DEVICE" };

	public FragmentAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int index) {
		return SensorFragment.newInstance(TAB[index]);
	}

	@Override
	public int getCount() {
		return TAB.length;
	}
}