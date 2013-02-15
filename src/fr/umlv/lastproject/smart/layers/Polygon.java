package fr.umlv.lastproject.smart.layers;

import java.util.ArrayList;

/**
 * This class represent the Polygon geometry to draw
 * 
 * @author Fad's
 * 
 */
public class Polygon extends Geometry {
	private ArrayList<Point> points;
	
	public Polygon(){
		points = new ArrayList<Point>();
		setType(GeometryType.POLYGON);
	}

	/**
	 * Polygon constructor
	 * 
	 * @param points
	 *            : list of points to draw
	 */
	public Polygon(ArrayList<Point> points) {
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
	public Polygon(double latitude, double longitude) {
		this.points = new ArrayList<Point>();
		this.points.add(new Point(latitude, longitude));
		setType(GeometryType.POLYGON);

	}

	/**
	 * Function which add point to the list
	 * 
	 * @param point
	 *            : point to add
	 */
	public void addPoint(Point point) {
		this.points.add(point);
	}

	/**
	 * Function which return all points contained in the geometry
	 * 
	 * @return the list of points
	 */
	public ArrayList<Point> getPoints() {
		return this.points;
	}
}
