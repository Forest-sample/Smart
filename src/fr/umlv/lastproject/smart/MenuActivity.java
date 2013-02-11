package fr.umlv.lastproject.smart;


import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import android.app.Activity;
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



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart);
		initMap();

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
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setClickable(true);
		mapView.setMultiTouchControls(true);
		mapController.setZoom(15);
		overlayManager.add(new ScaleBarOverlay(this));

	}

}
