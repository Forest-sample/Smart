package com.example.smart_lot1;

import java.text.DecimalFormat;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import fr.umlv.smart.R;


import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;

public class MapViewActivity extends Activity {

	private MapView mapView;
	private GPS gps ;
	private MyPositionOverlay myPos;
	private MapController mapControler;
	private DecimalFormat df = new DecimalFormat("####0.00000");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_view);

		//mapView = new MapView(this, 256); //constructor
		mapView = (MapView)findViewById(R.id.mapview);
		mapControler = mapView.getController();
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setClickable(true);
		mapView.setMultiTouchControls(true);	
		
		mapControler.setZoom(15); //set initial zoom-level, depends on your need
		

		//mapView.setUseDataConnection(false); //keeps the mapView from loading online tiles using network connection.

		myPos = new MyPositionOverlay(this);
		mapView.getOverlayManager().add(myPos);
		ScaleBarOverlay scale = new ScaleBarOverlay(this);
		
		mapView.getOverlayManager().add(scale);

		gps = new GPS((LocationManager)getSystemService(Context.LOCATION_SERVICE));
		gps.start();
		
		gps.addGPSListener(new GPSListener() {

			@Override
			public void actionPerformed(GPSEvent event) {
				((TextView)findViewById(R.id.latitude)).setText("Latitude : " + df.format(event.getLatitude()));
				((TextView)findViewById(R.id.longitude)).setText("Longitude : " + df.format(event.getLongitude()));
				((TextView)findViewById(R.id.altitude)).setText("Altitude : " + (int)event.getAltitude() + "m");
				((TextView)findViewById(R.id.precision)).setText("Précision : " + (int)event.getAccurency() + "m");
				myPos.setLatitiude(event.getLatitude());
				myPos.setLongitude(event.getLongitude());
				myPos.setAccurency(event.getAccurency());
				mapControler.setCenter(new GeoPoint(event.getLatitude(), gps.getLongitude()));

			}
		});

		//setContentView(mapView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_map_view, menu);
		return true;
	}

}
