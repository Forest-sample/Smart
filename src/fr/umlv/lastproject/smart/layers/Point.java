package fr.umlv.lastproject.smart.layers;

import org.osmdroid.util.GeoPoint;

public class Point {
	private GeoPoint coordinates;

	public Point(GeoPoint coordinate) {
		this.coordinates = coordinate;
	}

	public Point(double latitude, double longitude) {
		this.coordinates = new GeoPoint(latitude, longitude);
	}

	public GeoPoint getCoordinates() {
		return this.coordinates;
	}

	public double getLatitude() {
		return coordinates.getLatitudeE6();
	}

	public double getLongitude() {
		return coordinates.getLongitudeE6();
	}
}
