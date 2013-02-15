package fr.umlv.lastproject.smart.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Form implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -30424477427478579L;
	private String name;
	private List<Field> fieldsList;

	public Form(String name) {
		this.name = name;
		this.fieldsList = new ArrayList<Field>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getFieldsList() {
		return fieldsList;
	}

	public void setFieldsList(List<Field> fieldsList) {
		this.fieldsList = fieldsList;
	}

	public void addField(Field f) {
		this.fieldsList.add(f);
	}

}
