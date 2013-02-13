package fr.umlv.lastproject.smart.layers;

public class PolygonSymbology extends Symbology {

	private int thickness;

	public PolygonSymbology(int thickness, int color) {
		super(color);
		this.thickness = thickness;
	}

	public int getThickness() {
		return this.thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

}
