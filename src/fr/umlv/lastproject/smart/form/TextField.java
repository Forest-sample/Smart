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
	private int length;

	public TextField(String label, int length) {
		super(label, 0);
		this.length = length;
	}

	public TextField(String label) {
		super(label, 0);
		this.length = -1;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
