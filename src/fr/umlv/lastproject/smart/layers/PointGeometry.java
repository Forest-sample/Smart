package fr.umlv.lastproject.smart.layers;

import org.osmdroid.util.GeoPoint;

/**
 * This class represent the Point geometry to draw
 * 
 * @author Fad's
 * 
 */
public class PointGeometry extends Geometry {
	private GeoPoint coordinates;

	/**
	 * Point constructor
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public PointGeometry(double latitude, double longitude) {
		this.coordinates = new GeoPoint(latitude, longitude);
		setType(GeometryType.POINT);

	}

	/**
	 * Function which return the Point coordinates
	 * 
	 * @return coordinates
	 */
	public GeoPoint getCoordinates() {
		return this.coordinates;

	}

	/**
	 * Function which return the Point latitude
	 * 
	 * @return latitude
	 */
	public float getLatitude() {
		return coordinates.getLatitudeE6();
	}

	/**
	 * Function which return the Point longitude
	 * 
	 * @return longitude
	 */
	public float getLongitude() {
		return coordinates.getLongitudeE6();
	}

}
