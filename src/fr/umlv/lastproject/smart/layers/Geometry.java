package fr.umlv.lastproject.smart.layers;

/**
 * Class which define the Geometry whith their symbology
 * 
 * @author Fad's
 * 
 */
public class Geometry {

	/**
	 * 
	 * @author thibault
	 *
	 */
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
	
	/**
	 * 
	 * @return the type of the geometry
	 */
	public GeometryType getType(){
		return type;
	}
	
	/**
	 * 
	 * @param type the type of the geometry
	 */
	public void setType(GeometryType type){
		this.type = type ;
	}

}
