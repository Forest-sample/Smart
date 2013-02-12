package fr.umlv.lastproject.smart;

public class GPSEvent {

	private double latitude;
	private double longitude;
	private double altitude;
	private float accuracy;
	private float bearing;
	private float speed;

	/**
	 * GPSEvent constructor
	 * 
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param accuracy
	 * @param bearing
	 * @param speed
	 */
	public GPSEvent(double latitude, double longitude, double altitude,
			float accuracy, float bearing, float speed) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.accuracy = accuracy;
		this.bearing = bearing;
		this.speed = speed;

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

	/**
	 * 
	 * @return the bearing
	 */
	public float getBearing() {
		return bearing;
	}

	/**
	 * 
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}

}
