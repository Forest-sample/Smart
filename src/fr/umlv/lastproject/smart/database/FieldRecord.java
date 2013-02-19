package fr.umlv.lastproject.smart.database;

import fr.umlv.lastproject.smart.form.Field;

/**
 * Class uses to model a record of field
 * 
 * @author Maellou
 * 
 */
public class FieldRecord {

	private Field field;

	public FieldRecord(Field field) {
		super();
		this.field = field;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}


}
