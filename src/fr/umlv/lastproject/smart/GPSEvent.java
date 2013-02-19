package fr.umlv.lastproject.smart;

import java.util.Date;

public class GPSEvent {

	private final double latitude;
	private final double longitude;
	private final double altitude;
	private final float accuracy;
	private final float bearing;
	private final float speed;
	private final Date time;

	/**
	 * GPSEvent constructor
	 * 
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param accuracy
	 * @param bearing
	 * @param speed
	 * @param time
	 */
	public GPSEvent(double latitude, double longitude, double altitude,
			float accuracy, float bearing, float speed, Date time) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.accuracy = accuracy;
		this.bearing = bearing;
		this.speed = speed;
		this.time=time;

	}

	public Date getTime() {
		return time;
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