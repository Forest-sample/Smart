package fr.umlv.lastproject.smart.dialog;

import org.osmdroid.views.MapView;

import fr.umlv.lastproject.smart.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class AlertZoomDialog extends AlertDialog.Builder {

	public AlertZoomDialog(final Context context, final boolean zoomIn,
			final MapView mapView) {
		super(context);

		// On instancie notre layout en tant que View
		final LayoutInflater factory = LayoutInflater.from(context);
		final View alertZoomView = factory.inflate(R.layout.alert_zoom, null);

		// On affecte la vue personnalis� que l'on a cr�e � notre AlertDialog
		setView(alertZoomView);

		// On donne un titre � l'AlertDialog
		setTitle("Tiles are missing for this zoom level");

		// On modifie l'ic�ne de l'AlertDialog pour le fun ;)
		setIcon(android.R.drawable.ic_dialog_alert);

		final TextView tv = (TextView) alertZoomView
				.findViewById(R.id.zoomView);
		if (zoomIn)
			tv.setText(R.string.zoomIn);
		else
			tv.setText(R.string.zoomOut);

		// On affecte un bouton "OK" � notre AlertDialog et on lui affecte un
		// �v�nement
		setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

//				if (zoomIn){
//					
//					mapView.getController().setZoom(mapView.getZoomLevel()-1);
//				}
//					
//				else
//					mapView.getController().zoomOut();

			}
		});

		// On cr�e un bouton "Annuler" � notre AlertDialog et on lui affecte un
		// �v�nement
		setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Lorsque l'on cliquera sur annuler on ne fait rien
				if(zoomIn)mapView.getController().setZoom(mapView.getZoomLevel()-1);
				else mapView.getController().setZoom(mapView.getZoomLevel()+1);

			}
		});

		setCancelable(false);
	}

}
