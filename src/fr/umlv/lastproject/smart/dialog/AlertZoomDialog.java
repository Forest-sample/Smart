package fr.umlv.lastproject.smart.dialog;

import org.osmdroid.views.MapView;

import fr.umlv.lastproject.smart.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Alert Dialog telling user tiles are missing.
 * He can either confirm he wants to zoom or cancel zoom action
 * @author Marc
 *
 */
public class AlertZoomDialog extends AlertDialog.Builder {

	public AlertZoomDialog(final Context context, final boolean zoomIn,
			final MapView mapView) {
		super(context);

		if(mapView==null){
			throw new IllegalArgumentException();
		}
		final LayoutInflater factory = LayoutInflater.from(context);
		final View alertZoomView = factory.inflate(R.layout.alert_zoom, null);

		
		setView(alertZoomView);		
		setTitle(R.string.zoomTitle);

		
		setIcon(android.R.drawable.ic_dialog_alert);

		final TextView tv = (TextView) alertZoomView
				.findViewById(R.id.zoomView);
		if (zoomIn){
			tv.setText(R.string.zoomIn);
		}
			
		else{
			tv.setText(R.string.zoomOut);

		}

		
		setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				//Nothing to do

			}
		});

		
		setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//Readjust zoom level based on zoomIn/zoomOut
				if(zoomIn){
					mapView.getController().setZoom(mapView.getZoomLevel()-1);
				}
				else {
					mapView.getController().setZoom(mapView.getZoomLevel()+1);
				}

			}
		});

		setCancelable(false);
	}

}
