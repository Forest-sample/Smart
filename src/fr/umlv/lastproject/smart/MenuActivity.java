package fr.umlv.lastproject.smart;

import org.osmdroid.events.MapAdapter;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.DirectedLocationOverlay;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends Activity {

	/**
	 * 
	 * @author thibault Brun
	 * @author tanios Faddoul Description : This class contains the Menus
	 *         container
	 * 
	 */

	private MapView mapView;
	private MapController mapController;
	private OverlayManager overlayManager;
	private MyPositionOverlay myPositionOverlay;
	private GPS gps;
	private LocationManager locationManager;
	private InfoOverlay infoOverlay;
	private DirectedLocationOverlay dlo ;
	private CenterOverlay centerOverlay;
	private View centerMap;
	private boolean isMapTracked = true;
	private GeoPoint lastPosition = new GeoPoint(0, 0);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart);
		initMap();
		initGps();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_smart, menu);
		menu.add(0, 1, 0, "Hide informations");
		menu.add(0, 2, 0, "GPS Settings");
		return true;
	}

	/**
	 * This method is use to init the map
	 */
	public void initMap() {
		mapView = (MapView) findViewById(R.id.mapview);
		mapController = mapView.getController();
		myPositionOverlay = new MyPositionOverlay(this);
		overlayManager = mapView.getOverlayManager();
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setClickable(true);
		mapView.setMultiTouchControls(true);
		mapController.setZoom(15);
		overlayManager.add(new ScaleBarOverlay(this));
		dlo = new DirectedLocationOverlay(this);
		dlo.setShowAccuracy(true);
		overlayManager.add(dlo);
		overlayManager.add(myPositionOverlay);

		mapView.setMapListener(new MapAdapter() {
			@Override
			public boolean onScroll(ScrollEvent event) {
				isMapTracked = false;
				centerMap.setVisibility(View.VISIBLE);
				return super.onScroll(event);
			}
		});
		infoOverlay = new InfoOverlay(findViewById(R.id.table));
		// centerOverlay = new CenterOverlay(findViewById(R.id.centermap));
		centerMap = findViewById(R.id.centermap);
		centerMap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				isMapTracked = true;
				mapController.setCenter(lastPosition);
				centerMap.setVisibility(View.INVISIBLE);
			}
		});
	}

	/**
	 * This methode is use to connect the gps to the positionOverlay
	 */
	public void initGps() {

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		gps = new GPS(locationManager);

		if (!gps.isEnabled(locationManager)) {
			Intent intent = new Intent(
					android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}

		gps.start(5000, 10);
		gps.addGPSListener(new IGPSListener() {

			@Override
			public void actionPerformed(GPSEvent event) {
				/* Init Position Overlay */

				myPositionOverlay.updatePosition(event);
				lastPosition = new GeoPoint(event.getLatitude(), event
						.getLongitude());

				if (isMapTracked) {
					mapController.setCenter(lastPosition);
				}
				/* Init Informations zone */
				infoOverlay.updateInfo(event) ;
				
				/* change position marker */
				dlo.setLocation(new GeoPoint(event.getLatitude(), event.getLongitude()));
				dlo.setAccuracy((int) event.getAccuracy());
				dlo.setBearing(event.getBearing());
				
			}
		});
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
		case 2:
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}

		return super.onOptionsItemSelected(item);
	}

}
