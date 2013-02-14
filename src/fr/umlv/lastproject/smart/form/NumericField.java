package fr.umlv.lastproject.smart.form;

/**
 * Field of type Numeric
 * 
 * @author Maelle Cabot
 *
 */
public class NumericField extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5108601667354465487L;
	private int length;
	private int min;
	private int max;

	public NumericField(String label, int length, int min, int max) {
		super(label, 1);
		this.length = length;
		this.min = min;
		this.max = max;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

}
