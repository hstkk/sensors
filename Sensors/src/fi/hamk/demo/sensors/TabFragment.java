package fi.hamk.demo.sensors;

import java.util.List;

import fi.hamk.demo.sensors.models.KeyValue;

import android.widget.TableRow.LayoutParams;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TabFragment extends Fragment {

	TableLayout tableLayout;
	Context context;
	List<KeyValue> map;

	public TabFragment(Context context, List<KeyValue> map) {
		this.context = context;
		this.map = map;
	}

	@Override
	public View onCreateView(LayoutInflater layoutInflater,
			ViewGroup viewGroup, Bundle bundle) {
		View view = layoutInflater.inflate(R.layout.fragment_tab, viewGroup,
				false);
		tableLayout = (TableLayout) view.findViewById(R.id.table);
		if (map != null)
			addRow(map);
		else
			addRow("N/A", "");
		return view;
	}

	private void addRow(List<KeyValue> map) {
		for (KeyValue keyValue : map)
			addRow(keyValue.key, keyValue.value);
	}

	private void addRow(String header, String value) {
		TableRow tableRow = new TableRow(context);
		tableRow.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		TextView cell1 = new TextView(context);
		cell1.setLayoutParams(new LayoutParams(0));
		cell1.setTypeface(null, Typeface.BOLD);
		cell1.setText(header);
		tableRow.addView(cell1);
		TextView cell2 = new TextView(context);
		cell2.setLayoutParams(new LayoutParams(1));
		cell2.setText(value);
		tableRow.addView(cell2);
		tableLayout.addView(tableRow);
	}
}