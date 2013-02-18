package fr.umlv.lastproject.smart.database;

import fr.umlv.lastproject.smart.form.Field;

/**
 * Class uses to model a record of list field
 * 
 * @author Maellou
 * 
 */
public class ListFieldRecord extends FieldRecord {

	private String value;

	public ListFieldRecord(Field field, String value) {
		super(field);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
