package fr.umlv.lastproject.smart.form;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import fr.umlv.lastproject.smart.database.DbManager;
import fr.umlv.lastproject.smart.database.FormRecord;
import fr.umlv.lastproject.smart.database.GeometryRecord;
import fr.umlv.lastproject.smart.database.TextFieldRecord;
import fr.umlv.lastproject.smart.layers.Geometry;

/**
 * Object form associated at a mission
 * 
 * @author Maellou
 * 
 */
public class Form implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -30424477427478579L;
	private String name;
	private ArrayList<Field> fieldsList;

	public Form(String name) {
		this.name = name;
		this.fieldsList = new ArrayList<Field>();
		this.fieldsList.add(new TextField("commentaires"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Field> getFieldsList() {
		return fieldsList;
	}

	public void setFieldsList(ArrayList<Field> fieldsList) {
		this.fieldsList = fieldsList;
	}

	public void addField(Field f) {
		this.fieldsList.add(f);
	}

	/**
	 * Open a form after a survey and save the informations
	 * 
	 * @param context
	 *            of application
	 * @param g
	 *            geometry to insert
	 */
	public void openForm(final Context context, final Geometry g) {
		LayoutInflater factory = LayoutInflater.from(context);
		final View alertDialogView = factory.inflate(
				fr.umlv.lastproject.smart.R.layout.activity_formulaire_viewer,
				null);
		final AlertDialog.Builder adb = new AlertDialog.Builder(context);
		final Form form = this;
		adb.setView(alertDialogView);
		adb.setTitle("Formulaire");

		adb.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				EditText et = (EditText) alertDialogView
						.findViewById(fr.umlv.lastproject.smart.R.id.commentaires);
				DbManager dbManager = new DbManager();
				dbManager.open(context);
				int idGeometry = dbManager.insertGeometry(new GeometryRecord(g,
						Mission.getInstance().getId()));
				FormRecord formRecord = new FormRecord(form);

				TextFieldRecord fieldCommentaire = (TextFieldRecord) formRecord
						.getFields().get(0);

				fieldCommentaire.setValue(et.getText().toString());

				dbManager.insertFormRecord(formRecord, idGeometry);
				dbManager.close();

			}
		});

		adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		adb.show();
	}

}
