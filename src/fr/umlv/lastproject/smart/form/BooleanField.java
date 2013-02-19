package fr.umlv.lastproject.smart.form;

/**
 * Field of type boolean
 * 
 * @author Maelle Cabot
 * 
 */
public class BooleanField extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2750533583120339444L;
	private static int BOOLEAN_TYPE = 2;

	public BooleanField(String label) {
		super(label, BOOLEAN_TYPE);
	}

}
