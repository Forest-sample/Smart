package fr.umlv.lastproject.smart;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fr.umlv.lastproject.smart.utils.SmartConstants;

/**
 * This class is the Home Activity, where all SMART fonctionnalities is
 * available
 * 
 * @author Fad's
 * 
 */
public class HomeActivity extends Activity {

	private ListView listView;
	private List<ListViewItem> listItem;
	private String[] items;
	private boolean enabled;

	// public static final Integer[] images = { R.drawable.smart,
	//             R.drawable.smart, R.drawable.orange, R.drawable.mixed };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);

		// Retry the mission status
		enabled = getIntent().getExtras().getBoolean("missionCreated");

		// Retry the list of functionalities names
		items = getResources().getStringArray(R.array.items);

		listItem = new ArrayList<ListViewItem>();
		for (int i = 0; i < items.length; i++) {
			ListViewItem item;

			switch (i) {
			case SmartConstants.CREATE_MISSION:
				if (enabled) {
					item = new ListViewItem(R.drawable.centermap,
							getString(R.string.stopMission), enabled);
				} else
					item = new ListViewItem(R.drawable.centermap, items[i],
							!enabled);
				break;

			case SmartConstants.POINT_SURVEY:
				item = new ListViewItem(R.drawable.centermap, items[i], enabled);
				break;

			case SmartConstants.LINE_SURVEY:
				item = new ListViewItem(R.drawable.centermap, items[i], enabled);
				break;

			case SmartConstants.POLYGON_SURVEY:
				item = new ListViewItem(R.drawable.centermap, items[i], enabled);
				break;
			default:
				item = new ListViewItem(R.drawable.centermap, items[i], true);
				break;
			}

			listItem.add(item);
			item.setImageId(R.drawable.smart);
		}

		listView = (ListView) findViewById(R.id.listView);
		SmartItemAdapter adapter = new SmartItemAdapter(this,
				R.layout.listview_home_items, listItem);
		listView.setAdapter(adapter);
		listView.setClickable(false);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Intent intentReturn = new Intent(HomeActivity.this,
						MenuActivity.class);
				intentReturn.putExtra("function", items[position]);
				intentReturn.putExtra("position", position);
				setResult(RESULT_OK, intentReturn);
				view.setEnabled(false);
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Option");
		menu.add(0, v.getId(), 0, R.string.addShortcut);
		menu.add(0, v.getId(), 0, R.string.removeShortcut);
	}

}