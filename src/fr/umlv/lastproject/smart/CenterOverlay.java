package fr.umlv.lastproject.smart;

import android.util.Log;
import android.view.View;

/**
 * This class is used to set the option for center the map to actual position
 * 
 * @author Fad's
 * 
 */
public class CenterOverlay {
	private View centerMap;

	public CenterOverlay(View view) {
		this.centerMap = view;
		this.centerMap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.d("center", "je dois me centreeeeeeeeeeeer");

			}
		});
	}

}
