package fr.umlv.lastproject.smart.layers;

import java.util.ArrayList;

/**
 * This class represent the line geometry to draw
 * 
 * @author Fad's
 * 
 */
public class Line extends Geometry {
	private ArrayList<Point> points;

	/**
	 * Line constructor
	 * 
	 * @param points
	 *            : list of points to draw
	 */
	public Line(ArrayList<Point> points) {
		this.points = points;
	}

	/**
	 * Line constructor
	 * 
	 * @param latitude
	 *            : latitude of the point
	 * @param longitude
	 *            : longitude of the point
	 */
	public Line(double latitude, double longitude) {
		this.points = new ArrayList<Point>();
		this.points.add(new Point(latitude, longitude));
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
