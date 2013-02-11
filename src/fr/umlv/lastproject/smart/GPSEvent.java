package fr.umlv.lastproject.smart;

public class GPSEvent {

	private double latitude;
	private double longitude;
	private double altitude;
	private float accuracy;

	public GPSEvent(double latitude, double longitude, double altitude,
			float accuracy) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.accuracy = accuracy;
	}

	/**
	 * 
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * 
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * 
	 * @return the altitude
	 */
	public double getAltitude() {
		return altitude;
	}

	/**
	 * 
	 * @return the accuracy
	 */
	public float getAccuracy() {
		return accuracy;
	}

}
