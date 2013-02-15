package fr.umlv.lastproject.smart.layers;

/**
 * Class which define the Geometry whith their symbology
 * 
 * @author Fad's
 * 
 */
public class Geometry {

	// Type of geometry
	public enum GeometryType {
		POINT, LINE, POLYGON
	}
	
	GeometryType type ;

	private Symbology symbology;

	/**
	 * 
	 * @return the symbology
	 */
	public Symbology getSymbology() {
		return this.symbology;
	}

	/**
	 * 
	 * @param symbology
	 *            : symbology to set
	 */
	public void setSymbology(Symbology symbology) {
		this.symbology = symbology;
	}
	
	public GeometryType getType(){
		return type;
	}
	
	public void setType(GeometryType type){
		this.type = type ;
	}

}
