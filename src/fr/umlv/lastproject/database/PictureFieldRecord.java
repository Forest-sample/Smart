package fr.umlv.lastproject.database;

import fr.umlv.lastproject.smart.form.Field;

public class PictureFieldRecord extends FieldRecord {

	private String value;

	public PictureFieldRecord(Field field, String value) {
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
