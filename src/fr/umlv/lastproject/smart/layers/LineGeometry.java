package fr.umlv.lastproject.smart.layers;

import java.util.ArrayList;

/**
 * This class represent the line geometry to draw
 * 
 * @author Fad's
 * 
 */
public class LineGeometry extends Geometry {
	private ArrayList<PointGeometry> points;

	/**
	 * Line constructor
	 * 
	 * @param points
	 *            : list of points to draw
	 */
	public LineGeometry(ArrayList<PointGeometry> points) {
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
	public LineGeometry(double latitude, double longitude) {
		this.points = new ArrayList<PointGeometry>();
		this.points.add(new PointGeometry(latitude, longitude));
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
	public ArrayList<PointGeometry> getPoints() {
		return this.points;
	}

}
