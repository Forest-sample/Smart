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
	private String picturePath;

	public PictureField(String pictureoPath) {
		super("Picture", PICTURE_TYPE);
		this.picturePath = pictureoPath;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPhotoPath(String picturePath) {
		this.picturePath = picturePath;
	}

}
