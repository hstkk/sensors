package fi.hamk.demo.sensors;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentAdapter fragmentAdapter = new FragmentAdapter(
				getSupportFragmentManager());

		ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(fragmentAdapter);

		PageIndicator pageIndicator = (TabPageIndicator) findViewById(R.id.tabPageIndicator);
		pageIndicator.setViewPager(viewPager);
	}
}
