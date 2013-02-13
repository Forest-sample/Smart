package fr.umlv.lastproject.smart.layers;

import org.osmdroid.util.GeoPoint;

public class Point extends Geometry {
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

	public float getLatitude() {
		return coordinates.getLatitudeE6();
	}

	public float getLongitude() {
		return coordinates.getLongitudeE6();
	}

}
