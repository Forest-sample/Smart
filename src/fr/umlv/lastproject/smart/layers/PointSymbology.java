package fr.umlv.lastproject.smart.layers;

public class PointSymbology extends Symbology {
	private int radius;

	public PointSymbology(int radius, int color) {
		super(color);

		this.radius = radius;
	}

	public int getRadius() {
		return this.radius;
	}

	public void setRadio(int radius) {
		this.radius = radius;
	}

}
