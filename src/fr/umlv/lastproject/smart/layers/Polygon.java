package fr.umlv.lastproject.smart.layers;

import java.util.ArrayList;

import org.osmdroid.util.GeoPoint;

public class Polygon extends Geometry {
	private ArrayList<Point> points;
	
	public Polygon(){
		points = new ArrayList<Point>();
	}

	public Polygon(ArrayList<Point> points) {
		this.points = points;
	}

	public Polygon(double latitude, double longitude) {
		this.points = new ArrayList<Point>();
		this.points.add(new Point(latitude, longitude));
	}

	public Polygon(GeoPoint coordinates) {
		this.points = new ArrayList<Point>();
		this.points.add(new Point(coordinates));
	}

	public void addPoint(Point point) {
		this.points.add(point);
	}

	public ArrayList<Point> getPoints() {
		return this.points;
	}
}
