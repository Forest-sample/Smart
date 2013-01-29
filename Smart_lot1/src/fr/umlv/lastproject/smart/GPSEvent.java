package fr.umlv.lastproject.smart;

public class GPSEvent {
	
	private double latitude;
	private double longitude;
	private double altitude;
	private float precision;
	
	public GPSEvent(double latitude, double longitude, double altitude,	float accuracy) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.precision = accuracy;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public double getAltitude() {
		return altitude;
	}

	public float getAccurency() {
		return precision;
	}

	

}
