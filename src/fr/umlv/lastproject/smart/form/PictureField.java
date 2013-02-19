package fr.umlv.lastproject.smart.form;

/**
 * Field of type Picture
 * 
 * @author Maelle Cabot
 * 
 */
public class PictureField extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6925473200014882410L;
	private static int PICTURE_TYPE = 4;

	public PictureField(String name) {
		super("Picture", PICTURE_TYPE);
	}


}
