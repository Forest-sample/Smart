package fr.umlv.lastproject.smart.database;

import fr.umlv.lastproject.smart.form.Field;

/**
 * Class uses to model a record of boolean field
 * 
 * @author Maellou
 * 
 */
public class BooleanFieldRecord extends FieldRecord {

	private boolean value;

	public BooleanFieldRecord(Field field, boolean value) {
		
		super(field);
		this.value = value;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

}
