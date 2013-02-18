package fr.umlv.lastproject.smart.form;

import fr.umlv.lastproject.smart.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class CreateFormActivity extends Activity {

	private final static int TEXT_FIELD = 0;
	private final static int NUMERIC_FIELD = 1;
	private final static int BOOLEAN_FIELD = 2;
	private final static int LIST_FIELD = 3;
	private final static int PICTURE_FIELD = 4;
	private final static int HEIGHT_FIELD = 5;
	
	TableLayout layoutDynamic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_form);
		
		layoutDynamic = ( TableLayout ) findViewById(R.id.layoutDynamicCreateFormulaire);
		layoutDynamic.removeAllViewsInLayout();

		

		Button button = (Button) findViewById(R.id.buttonAdd);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CreateFormActivity.this, AddFieldtoFormActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		Button validate = (Button) findViewById(R.id.buttonValidate);
		validate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_form, menu);
		return true;
	}

}
