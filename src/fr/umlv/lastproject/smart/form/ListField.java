package fr.umlv.lastproject.smart.form;

import java.util.ArrayList;
import java.util.List;

/**
 * Field of type List
 * 
 * @author Maelle Cabot
 * 
 */
public class ListField extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1340773531544440170L;
	private List<String> values;

	public ListField(String label, List<String> values) {
		super(label, 3);
		values = new ArrayList<String>();
		values.addAll(values);
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
