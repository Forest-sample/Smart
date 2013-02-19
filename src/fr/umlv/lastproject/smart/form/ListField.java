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
	private static final int LIST_TYPE = 3;
	private List<String> values;

	public ListField(String label, List<String> list) {
		super(label, LIST_TYPE);
		values = new ArrayList<String>();
		values.addAll(list);
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
