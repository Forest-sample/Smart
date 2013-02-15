package fr.umlv.lastproject.smart.layers;

import android.graphics.Color;

/**
 * This class represent the symbology of the Line
 * 
 * @author Fad's
 * 
 */
public class LineSymbology extends Symbology {

	private int thickness;

	/**
	 * Line default constructor
	 * 
	 * Color : Black / Thickness : 5
	 */
	public LineSymbology() {
		super(Color.BLACK);
		this.thickness = 5;
	}

	/**
	 * Line constructor
	 * 
	 * @param thickness
	 * @param color
	 */
	public LineSymbology(int thickness, int color) {
		super(color);
		this.thickness = thickness;
	}

	/**
	 * 
	 * @return the line thickness
	 */
	public int getThickness() {
		return this.thickness;
	}

	/**
	 * Function which set the Line thickness
	 * 
	 * @param thickness
	 */
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
}
