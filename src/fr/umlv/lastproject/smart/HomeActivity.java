package fr.umlv.lastproject.smart;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HomeActivity extends Activity {

	private ListView listView;
	private ArrayList<HashMap<String, String>> listItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);

		listView = (ListView) findViewById(R.id.listView);

		// ArrayList qui permet de remplir la ListView
		listItem = new ArrayList<HashMap<String, String>>();

		String[] items = getResources().getStringArray(R.array.items);
		int[] itemsIcon = new int[] { R.drawable.centermap, R.drawable.smart,
				R.drawable.layers, R.drawable.home };

		int index = 0;
		// Création des items pour la listView
		for (String item : items) {
			ListViewItem itemToAdd = new ListViewItem(item,
					String.valueOf(itemsIcon[index]));
			listItem.add(itemToAdd.getItem());
		}

		// Création d'un SimpleAdapter qui se chargera de mettre les items
		// présent dans notre list (listItem) dans la vue listview_items
		SimpleAdapter simpleAdapter = new SimpleAdapter(this.getBaseContext(),
				listItem, R.layout.listview_items,
				new String[] { "img", "name" }, new int[] { R.id.function_icon,
						R.id.function_name });

		// On attribut à notre listView l'adapter que l'on vient de créer
		listView.setAdapter(simpleAdapter);

		registerForContextMenu(listView);

		// Listener d'évènement sur notre listView
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				// on récupère la HashMap contenant les infos de notre item
				// (name, img)

				@SuppressWarnings("unchecked")
				HashMap<String, Drawable> map = (HashMap<String, Drawable>) listView
						.getItemAtPosition(position);

				Intent intentReturn = new Intent(HomeActivity.this,
						MenuActivity.class);
				intentReturn.putExtra("function",
						(CharSequence) map.get("name"));
				intentReturn.putExtra("position", position);
				setResult(RESULT_OK, intentReturn);
				finish();

				// AlertDialog.Builder adb = new AlertDialog.Builder(
				// HomeActivity.this);
				// adb.setTitle("Sélection Item");
				// adb.setMessage("Votre choix : " + map.get("name"));
				// adb.setPositiveButton("Ok", null);
				// adb.setOnKeyListener(new OnKeyListener() {
				//
				// @Override
				// public boolean onKey(DialogInterface dialog, int keyCode,
				// KeyEvent event) {
				//
				// Intent intent = new Intent(HomeActivity.this,
				// LayersActivity.class);
				// startActivity(intent);
				// return false;
				// }
				// });
				// adb.show();
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
