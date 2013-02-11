package fr.umlv.lastproject.smart;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends Activity {

	private GPS gps;
	private InfoOverlay infoOverlay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart);
		infoOverlay = new InfoOverlay(findViewById(R.id.table));

		gps = new GPS(
				(LocationManager) getSystemService(Context.LOCATION_SERVICE));

		gps.start(5000, 10);

		gps.addGPSListener(new IGPSListener() {

			@Override
			public void actionPerformed(GPSEvent event) {
				infoOverlay.setLatitude(event.getLatitude());
				infoOverlay.setLongitude(event.getLongitude());
				infoOverlay.setAltitude(event.getAltitude());
				infoOverlay.setAccuracy(event.getAccuracy());
				infoOverlay.updateInfo();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_smart, menu);
		menu.add(0, 1, 0, "Hide informations");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			if (findViewById(R.id.table).getVisibility() == View.INVISIBLE) {
				item.setTitle("Hide informations");
				findViewById(R.id.table).setVisibility(View.VISIBLE);
			} else {
				item.setTitle("Show informations");
				findViewById(R.id.table).setVisibility(View.INVISIBLE);

			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
