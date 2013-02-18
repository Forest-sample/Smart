package fr.umlv.lastproject.smart.database;

import fr.umlv.lastproject.smart.form.Field;

/**
 * Class uses to model a record of picture field
 * 
 * @author Maellou
 * 
 */
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
