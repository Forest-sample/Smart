package fr.umlv.lastproject.smart.database;

import fr.umlv.lastproject.smart.form.Field;

public class ListFieldRecord extends FieldRecord {

	private String value;

	public ListFieldRecord(Field field, String value) {
		super(field);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
