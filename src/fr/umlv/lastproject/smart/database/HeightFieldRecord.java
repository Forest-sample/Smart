package fr.umlv.lastproject.smart.database;

import fr.umlv.lastproject.smart.form.Field;

/**
 * Class uses to model a record of height field
 * 
 * @author Maellou
 * 
 */
public class HeightFieldRecord extends FieldRecord {

	private double value;

	public HeightFieldRecord(Field field, double value) {
		super(field);
		
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
