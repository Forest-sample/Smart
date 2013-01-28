package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class CreateFormulaire extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_formulaire);
		
		TextView view = (TextView) findViewById(R.layout.activity_create_formulaire);
		String textXML = new String();
		
		
		
		view.setText(textXML);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_formulaire, menu);
		return true;
	}

}
