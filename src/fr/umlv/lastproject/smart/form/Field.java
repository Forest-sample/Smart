package fr.umlv.lastproject.smart.form;

import java.io.Serializable;

/**
 * Field used for forms management
 * 
 * @author Maelle Cabot
 * 
 */
public abstract class Field implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1486832698681988189L;
	private String label;
	private int type;

	/*
	 * TEXT_FIELD = 0; NUMERIC_FIELD = 1; BOOLEAN_FIELD = 2; LIST_FIELD = 3;
	 * PICTURE_FIELD = 4; HEIGHT_FIELD = 5;
	 */

	public Field(String label, int type) {
		this.label = label;
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
