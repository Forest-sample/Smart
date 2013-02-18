package fr.umlv.lastproject.smart.database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.util.Log;
import fr.umlv.lastproject.smart.form.Form;
import fr.umlv.lastproject.smart.form.Mission;

/**
 * Object Mission which can be stored in table "missions"
 * 
 * @author Maelle Cabot
 * 
 */
public class MissionRecord {

	private int id;
	private String title;

	// True if the mission is in progress
	private boolean status;
	private String date;
	private Form form;

	public MissionRecord() {
		this.title = Mission.getInstance().getTitle();
		Log.d("", "id" + this.title);
		this.status = true;
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss", Locale.FRENCH);
		this.date = dateFormat.format(new Date());
		this.form = Mission.getInstance().getForm();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		Mission.getInstance().setId(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

}
