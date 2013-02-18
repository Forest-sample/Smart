package fr.umlv.lastproject.smart.database;

import fr.umlv.lastproject.smart.layers.PointGeometry;

/**
 * Object Point which can be stored in table "points"
 * 
 * @author Maellou
 * 
 */
public class PointRecord {

	private int id;
	private double x;
	private double y;
	private double z;
	private int idGeometry;

	public PointRecord() {
	}

	public PointRecord(PointGeometry p) {

		this.x = p.getLatitude() / 1E6;
		this.y = p.getLongitude() / 1E6;
		this.z = -1;

	}

	public PointRecord(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public int getIdGeometry() {
		return idGeometry;
	}

	public void setIdGeometry(int idGeometry) {
		this.idGeometry = idGeometry;
	}

}
