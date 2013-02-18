package fr.umlv.lastproject.smart.database;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.lastproject.smart.form.Field;
import fr.umlv.lastproject.smart.form.Form;

/**
 * Class uses to model a record of form's instance
 * 
 * @author Maellou
 * 
 */
public class FormRecord {

	private String name;
	private ArrayList<FieldRecord> fields;

	public FormRecord(ArrayList<FieldRecord> fields, String name) {
		super();
		this.fields = fields;
		this.name = name;
	}

	public FormRecord(Form f) {
		super();
		this.fields = new ArrayList<FieldRecord>();

		ArrayList<Field> fieldslist = f.getFieldsList();
		for (Field fld : fieldslist) {

			switch (fld.getType()) {
			case 0: // TEXT FIELD
				TextFieldRecord fr = new TextFieldRecord(fld, null);
				this.fields.add(fr);

				break;
			case 1: // NUMERIC FIELD
				NumericFieldRecord nf = new NumericFieldRecord(fld, 0);
				this.fields.add(nf);

				break;
			case 2: // BOOLEAN FIELD
				BooleanFieldRecord bf = new BooleanFieldRecord(fld, false);
				this.fields.add(bf);

				break;
			case 3: // LIST FIELD
				ListFieldRecord lf = new ListFieldRecord(fld, null);
				this.fields.add(lf);

				break;
			case 4: // PICTURE FIELD
				PictureFieldRecord pf = new PictureFieldRecord(fld, null);
				this.fields.add(pf);

				break;
			case 5: // HEIGHT FIELD
				HeightFieldRecord hf = new HeightFieldRecord(fld, 0);
				this.fields.add(hf);

				break;
			default:
				break;
			}
		}
		this.name = f.getName();
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

	public void setFields(ArrayList<FieldRecord> fields) {
		this.fields = fields;
	}

	public void addField(FieldRecord f) {
		this.fields.add(f);
	}

}
