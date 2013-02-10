package fr.umlv.lastproject.smart;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart);
		MapView map = new MapView(this, 256);
		map.getController().setCenter(new GeoPoint(47.0,3.0));
		map.getController().setZoom(5);
		map.setClickable(true);
		setContentView(map);


		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_smart, menu);
		return true;
	}

}
