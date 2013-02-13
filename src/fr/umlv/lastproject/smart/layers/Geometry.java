package fr.umlv.lastproject.smart.layers;

/**
 * Class which define the Geometry whith their symbology
 * 
 * @author Fad's
 * 
 */
public class Geometry {

	// Type og geometry
	public enum GeometryType {
		POINT, LINE, POLYGON
	}

	private Symbology symbology;

	public Symbology getSymbology() {
		return this.symbology;
	}

	public void setSymbology(Symbology symbology) {
		this.symbology = symbology;
	}

}
