package fi.hamk.demo.sensors;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity implements
		ActionBar.TabListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// ActionBarSherlock add tabs
		for (String title : Conf.TABS) {
			ActionBar.Tab tab = getSupportActionBar().newTab();
			tab.setText(title);
			tab.setTabListener(this);
			getSupportActionBar().addTab(tab);
		}
	}

	// @Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		TabFragment tabFragment;
		if (tab.getPosition() == 0)
			tabFragment = new TabFragment(R.layout.fragment_device);
		else
			tabFragment = new TabFragment(R.layout.fragment_network);
		ft.replace(android.R.id.content, tabFragment);
	}

	// @Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// unused
	}

	// @Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO unused
	}
}