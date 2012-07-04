package fi.hamk.demo.sensors;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {

	FragmentAdapter fragmentAdapter;
	ViewPager viewPager;
	PageIndicator pageIndicator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(fragmentAdapter);

		pageIndicator = (TabPageIndicator) findViewById(R.id.indicator);
		pageIndicator.setViewPager(viewPager);
		// textView = (TextView) findViewById(R.id.textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(this, item.getItemId(), 5000);
		return super.onOptionsItemSelected(item);
	}
}
