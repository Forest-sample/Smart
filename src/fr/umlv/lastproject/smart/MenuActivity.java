package fr.umlv.lastproject.smart;


import org.metalev.multitouch.controller.MultiTouchController.PositionAndScale;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
public class MenuActivity extends Activity {

	/**
	 * 
	 * @author thibault Brun
	 * 
	 * Description : This class contains the Menus container
	 *
	 */

	private MapView mapView ;
	private MapController mapController ;
	private OverlayManager overlayManager;
	private MyPositionOverlay myPositionOverlay ;
	private GPS gps ;
	private GeoPoint lastPosition ;
	

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
		return true;
	}

	/**
	 * This method is use to init the map
	 */
	public void initMap(){
		mapView = (MapView) findViewById(R.id.mapview) ;
		mapController = mapView.getController() ;
		overlayManager = mapView.getOverlayManager();
		myPositionOverlay = new MyPositionOverlay(this);
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setClickable(true);
		mapView.setMultiTouchControls(true);
		mapController.setZoom(15);
		overlayManager.add(new ScaleBarOverlay(this));
		overlayManager.add(myPositionOverlay);
		
	}

	/**
	 * This methode is use to connect the gps to the positionOverlay
	 */
	public void initGps(){
		gps = new GPS((LocationManager) getSystemService(Context.LOCATION_SERVICE)) ;
		gps.start(5000, 10);
		gps.addGPSListener(new IGPSListener() {
			
			@Override
			public void actionPerformed(GPSEvent event) {
				myPositionOverlay.setAccurency(event.getAccuracy());
				myPositionOverlay.setLatitiude(event.getLatitude()) ;
				myPositionOverlay.setLongitude(event.getLongitude()) ;
				mapController.setCenter(new GeoPoint(event.getLatitude(), gps.getLongitude()));
			}
		});
	}
	

}
