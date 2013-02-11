package fr.umlv.lastproject.smart;

import java.text.DecimalFormat;

import android.view.View;
import android.widget.TextView;

public class InfoOverlay {
	private DecimalFormat locationFormat = new DecimalFormat("####0.00000");
	private DecimalFormat accuracyFormat = new DecimalFormat("####0.00");

	private double latitude;
	private double longitude;
	private double altitude;
	private float accuracy;

	private View infoView;

	/**
	 * InfoOverlay constructor
	 * 
	 * @param view
	 *            : Info view
	 */
	public InfoOverlay(final View view) {
		this.infoView = view;
	}

	/**
	 * Set the latitude
	 * 
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Set the longitude
	 * 
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Set the altitude
	 * 
	 * @param altitude
	 */
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	/**
	 * Set the accuracy
	 * 
	 * @param accuracy
	 */
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	/**
	 * Function which update the locations infos
	 */
	public void updateInfo() {
		((TextView) infoView.findViewById(R.id.latitude)).setText("Latitude : "
				+ locationFormat.format(latitude));
		((TextView) infoView.findViewById(R.id.longitude))
				.setText("Longitude : " + locationFormat.format(longitude));
		((TextView) infoView.findViewById(R.id.altitude)).setText("Altitude : "
				+ locationFormat.format(altitude));
		((TextView) infoView.findViewById(R.id.precision))
				.setText("Precision : " + accuracyFormat.format(accuracy) + "m");
	}
}
