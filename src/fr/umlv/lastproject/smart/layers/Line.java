package fr.umlv.lastproject.smart.layers;

import java.util.ArrayList;

import org.osmdroid.util.GeoPoint;

public class Line {
	private ArrayList<Point> points;

	public Line(ArrayList<Point> points) {
		this.points = points;
	}

	public Line(double latitude, double longitude) {
		this.points = new ArrayList<Point>();
		this.points.add(new Point(latitude, longitude));
	}

	public Line(GeoPoint coordinates) {
		this.points = new ArrayList<Point>();
		this.points.add(new Point(coordinates));
	}

	public void addPoint(Point point) {
		this.points.add(point);
	}
}
