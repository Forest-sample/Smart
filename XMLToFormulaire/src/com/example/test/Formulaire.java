package com.example.test;

import java.io.IOException;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Formulaire extends Activity {

	private static int PADDING = 10;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulaire);

		TextView datecurrent = (TextView) findViewById(R.id.datecourante);
		datecurrent.setText(getCurrentDate());

		LinearLayout layoutOfDynamicContent = ( LinearLayout ) findViewById(R.id.layoutOfDynamicContent );
		layoutOfDynamicContent.removeAllViewsInLayout();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc;
			doc = db.parse(new InputSource(getResources().openRawResource(R.raw.doc)));
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("champ");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Element node = (Element)nodeList.item(i);
				String type = node.getAttribute("type");
				if(type.equals("text")){
					String name = node.getAttribute("name");
					TableRow row = new TableRow(this);
					row.addView(createTextView(name));
					row.addView(createEditText(node));

					layoutOfDynamicContent.addView(row);
				} else if(type.equals("numeric")){
					String name = node.getAttribute("name");
					

					TableRow row = new TableRow(this);
					row.addView(createTextView(name));
					row.addView(createEditTextNumeric(node));
					
					layoutOfDynamicContent.addView(row);

				}

			}


		} catch (NotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TextView commentaires = new TextView(this);
		commentaires.setText("Commentaires");
		int px = dipToPixel(PADDING);
		commentaires.setPadding(px,px,px,px);
		
		layoutOfDynamicContent.addView(commentaires);
		
		EditText editCommentaires = new EditText(this);
		editCommentaires.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		editCommentaires.setPadding(px,px,px,px);
		editCommentaires.setSingleLine(false);
		
		layoutOfDynamicContent.addView(editCommentaires);
		


	}


	public String getCurrentDate(){
		final Calendar c = Calendar.getInstance();        
		int mYear = c.get(Calendar.YEAR);        
		int mMonth = c.get(Calendar.MONTH);        
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		int hours = c.get(Calendar.HOUR);
		int minutes = c.get(Calendar.MINUTE);
		int seconds = c.get(Calendar.SECOND);
		//on récupère le numéro du jour dans la semaine
		//int cDay=c.get(Calendar.DAY_OF_WEEK);	
		String date = new String();
		date = mDay+"/"+(mMonth+1)+"/"+mYear+"  "+hours+":"+minutes+":"+seconds;
		return date;

	}

	public View createTextView(String value){
		TextView view = new TextView(this);
		int px = dipToPixel(PADDING);
		view.setPadding(px,px,px,px);
		view.setText(value);
		return view;
	}


	public View createEditText(Element node){
		EditText edit = new EditText(this);
		String length = node.getAttribute("length");
		if(!length.equals("")){
			InputFilter[] FilterArray = new InputFilter[1];
			FilterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(length));
			edit.setFilters(FilterArray);
		}
		edit.setMinimumWidth(dipToPixel(200));
		return edit;
	}
	
	public View createEditTextNumeric(Element node){
		EditText edit = new EditText(this);
		String length = node.getAttribute("length");
		if(!length.equals("")){
			InputFilter[] FilterArray = new InputFilter[1];
			FilterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(length));
			edit.setFilters(FilterArray);
		}
		String max = node.getAttribute("max");
		if(!max.equals("")){
			//Vérifier avec un TextWatcher
		}
		String min = node.getAttribute("min");
		if(!min.equals("")){
			//Vérifier avec un TextWatcher
		}
		edit.setMinimumWidth(dipToPixel(200));
		edit.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
		return edit;
	}


	public int dipToPixel(int dip){
		return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_formulaire, menu);
		return true;
	}

}
