package fr.umlv.lastproject.smart.layers;

public class LineSymbology extends Symbology {

	private int thickness;

	public LineSymbology(int thickness, int color) {
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
