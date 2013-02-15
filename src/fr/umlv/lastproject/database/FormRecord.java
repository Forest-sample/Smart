package fr.umlv.lastproject.database;

import java.util.List;

public class FormRecord {

	private String name;
	private List<FieldRecord> fields;

	public FormRecord(List<FieldRecord> fields, String name) {
		super();
		this.fields = fields;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FieldRecord> getFields() {
		return fields;
	}

	public void setFields(List<FieldRecord> fields) {
		this.fields = fields;
	}

	public void addField(FieldRecord f) {
		this.fields.add(f);
	}

}
