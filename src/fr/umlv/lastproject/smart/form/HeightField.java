package fr.umlv.lastproject.smart.form;

/**
 * Field of type Height
 * 
 * @author Maelle Cabot
 * 
 */
public class HeightField extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int HEIGHT_TYPE = 5;
	private String name;

	public HeightField(String label) {
		super("Height", HEIGHT_TYPE);
		this.name = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
