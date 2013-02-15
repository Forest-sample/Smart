package fr.umlv.lastproject.database;

import fr.umlv.lastproject.smart.form.Field;

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
