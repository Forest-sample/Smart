package fr.umlv.lastproject.database;

public class PointRecord {

	private int id;
	private double x;
	private double y;
	private double z;
	private int idGeometry;

	public PointRecord() {
	}

	public PointRecord(double x, double y, double z, int idGeometry) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.idGeometry = idGeometry;
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
