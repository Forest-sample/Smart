package fr.umlv.lastproject.smart.form;

/**
 * Field of type Text
 * 
 * @author Maellou
 * 
 */
public class TextField extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5771639179711379906L;
	private static int TEXT_TYPE = 0;

	public TextField(String label) {
		super(label, TEXT_TYPE);
	}

	
}
