package fr.umlv.lastproject.smart;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SmartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart);
		System.out.println("bonjour");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_smart, menu);
		return true;
	}

}
