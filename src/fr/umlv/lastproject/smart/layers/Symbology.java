package fr.umlv.lastproject.smart.layers;

/**
 * This class represent the geometry symbology
 * 
 * @author Fad's
 * 
 */
public abstract class Symbology {

	private int color;

	public Symbology(int color) {
		this.color = color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return this.color;
	}
}
