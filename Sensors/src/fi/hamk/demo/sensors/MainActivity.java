package fi.hamk.demo.sensors;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends BaseActivity {
	private static final String[] CONTENT = new String[] { "Recent", "Artists",
			"Albums", "Songs", "Playlists", "Genres" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mAdapter = new GoogleMusicAdapter(getSupportFragmentManager());

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);

		mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
		// textView = (TextView) findViewById(R.id.textView);
	}

	/*
	 * public void magic(View view) { // new Utils(this).send(); //
	 * textView.setText(new Utils(this).getSensor().toJson()); // new
	 * Utils(this).send(); }
	 */

	class GoogleMusicAdapter extends TestFragmentAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return TestFragment.newInstance(MainActivity.CONTENT[position
					% MainActivity.CONTENT.length]);
		}

		@Override
		public int getCount() {
			return MainActivity.CONTENT.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return MainActivity.CONTENT[position
					% MainActivity.CONTENT.length].toUpperCase();
		}
	}
}
