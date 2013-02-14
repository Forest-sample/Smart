package fr.umlv.lastproject.smart;

import java.util.ArrayList;

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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;
import fr.umlv.lastproject.smart.layers.GeometryLayer;
import fr.umlv.lastproject.smart.layers.LineGeometry;
import fr.umlv.lastproject.smart.layers.LineSymbology;
import fr.umlv.lastproject.smart.layers.PointGeometry;
import fr.umlv.lastproject.smart.layers.PointSymbology;
import fr.umlv.lastproject.smart.layers.PolygonGeometry;
import fr.umlv.lastproject.smart.layers.PolygonSymbology;

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
	private GPS gps;
	private LocationManager locationManager;
	private InfoOverlay infoOverlay;
	private DirectedLocationOverlay directedLocationOverlay;
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
	public void onBackPressed() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle(R.string.exit);
		alertDialog.setMessage(R.string.exitMsg);
		alertDialog.setNegativeButton(R.string.no, null);
		alertDialog.setPositiveButton(R.string.yes, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		alertDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_smart, menu);
		menu.add(0, 1, 0, R.string.hideInfoZone);
		menu.add(0, 2, 0, R.string.gpsSettings);
		return true;
	}

	/**
	 * This method is use to init the map
	 */
	public void initMap() {
		mapView = (MapView) findViewById(R.id.mapview);
		mapController = mapView.getController();
		overlayManager = mapView.getOverlayManager();
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setClickable(true);
		mapView.setMultiTouchControls(true);
		mapController.setZoom(15);
		overlayManager.add(new ScaleBarOverlay(this));
		directedLocationOverlay = new DirectedLocationOverlay(this);
		directedLocationOverlay.setShowAccuracy(true);
		overlayManager.add(directedLocationOverlay);

		// TODO dessin d'un polygon
		{
			PointGeometry point = new PointGeometry(0, 0);
			PointGeometry point2 = new PointGeometry(10, 20);
			PointGeometry point3 = new PointGeometry(20, 30);

			ArrayList<PointGeometry> points = new ArrayList<PointGeometry>();
			points.add(point);
			points.add(point2);
			points.add(point3);

			PolygonGeometry polygon = new PolygonGeometry(points);

			GeometryLayer geometryLayer = new GeometryLayer(this);
			geometryLayer.addGeometry(polygon);
			geometryLayer.setType(GeometryType.POLYGON);
			geometryLayer.setSymbology(new PolygonSymbology(10, Color.BLACK));
			overlayManager.add(geometryLayer);
		}
		// TODO fin test

		// TODO dessin d'un polygon
		{
			PointGeometry point = new PointGeometry(40, 20);
			PointGeometry point2 = new PointGeometry(40, 50);
			PointGeometry point3 = new PointGeometry(50, 60);

			ArrayList<PointGeometry> points = new ArrayList<PointGeometry>();
			points.add(point);
			points.add(point2);
			points.add(point3);

			LineGeometry line = new LineGeometry(points);

			GeometryLayer geometryLayer = new GeometryLayer(this);
			geometryLayer.addGeometry(line);
			geometryLayer.setType(GeometryType.LINE);
			geometryLayer.setSymbology(new LineSymbology(10, Color.GREEN));
			overlayManager.add(geometryLayer);
		}
		// TODO fin test

		// TODO dessin d'un polygon
		{

			PointGeometry point1 = new PointGeometry(60, 60);
			PointGeometry point2 = new PointGeometry(70, 70);
			PointGeometry point3 = new PointGeometry(80, 80);

			GeometryLayer geometryLayer = new GeometryLayer(this);
			geometryLayer.addGeometry(point1);
			geometryLayer.addGeometry(point2);
			geometryLayer.addGeometry(point3);
			geometryLayer.setType(GeometryType.POINT);
			geometryLayer.setSymbology(new PointSymbology(10, Color.RED));
			overlayManager.add(geometryLayer);
		}
		// TODO fin test

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
	 * This method is use to connect the GPS to the positionOverlay
	 */
	public void initGps() {

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		gps = new GPS(locationManager);

		if (!gps.isEnabled(locationManager)) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle(R.string.gpsSettings);
			alertDialog.setMessage(R.string.gpsMessage);

			alertDialog.setNegativeButton(R.string.cancel, null);

			alertDialog.setPositiveButton(R.string.validate,
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							startActivity(intent);

						}
					});

			alertDialog.show();
		}

		gps.start(5000, 10);
		gps.addGPSListener(new IGPSListener() {

			@Override
			public void actionPerformed(GPSEvent event) {
				/* Init Position Overlay */

				lastPosition = new GeoPoint(event.getLatitude(), event
						.getLongitude());

				if (isMapTracked) {
					mapController.setCenter(lastPosition);
				}
				/* Init Informations zone */
				infoOverlay.updateInfo(event);

				/* change position marker */
				directedLocationOverlay.setLocation(new GeoPoint(event
						.getLatitude(), event.getLongitude()));
				directedLocationOverlay.setAccuracy((int) event.getAccuracy());
				directedLocationOverlay.setBearing(event.getBearing());
				mapView.invalidate();
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			if (findViewById(R.id.table).getVisibility() == View.INVISIBLE) {
				item.setTitle(R.string.hideInfoZone);
				findViewById(R.id.table).setVisibility(View.VISIBLE);
			} else {
				item.setTitle(R.string.showInfoZone);
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
