package fr.umlv.lastproject.smart.layers;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the Polygon geometry to draw
 * 
 * @author Fad's
 * 
 */
public class PolygonGeometry extends Geometry {
	private List<PointGeometry> points;
	
	public PolygonGeometry(){
		points = new ArrayList<PointGeometry>();
		setType(GeometryType.POLYGON);
	}

	/**
	 * Polygon constructor
	 * 
	 * @param points
	 *            : list of points to draw
	 */
	public PolygonGeometry(List<PointGeometry> points) {
		this.points = points;
		setType(GeometryType.POLYGON);

	}

	/**
	 * Polygon constructor
	 * 
	 * @param latitude
	 *            : latitude of the point
	 * @param longitude
	 *            : longitude of the point
	 */
	public PolygonGeometry(double latitude, double longitude) {
		this.points = new ArrayList<PointGeometry>();
		this.points.add(new PointGeometry(latitude, longitude));
		setType(GeometryType.POLYGON);

	}

	/**
	 * Function which add point to the list
	 * 
	 * @param point
	 *            : point to add
	 */
	public void addPoint(PointGeometry point) {
		this.points.add(point);
	}

	/**
	 * Function which return all points contained in the geometry
	 * 
	 * @return the list of points
	 */
	public List<PointGeometry> getPoints() {
		return this.points;
	}
}
