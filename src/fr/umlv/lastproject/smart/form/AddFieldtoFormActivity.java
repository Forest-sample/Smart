package fr.umlv.lastproject.smart.form;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.lastproject.smart.R;
import fr.umlv.lastproject.smart.R.layout;
import fr.umlv.lastproject.smart.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;

public class AddFieldtoFormActivity extends Activity {
	
	private final static int TEXT_FIELD = 0;
	private final static int NUMERIC_FIELD = 1;
	private final static int BOOLEAN_FIELD = 2;
	private final static int LIST_FIELD = 3;
	private final static int PICTURE_FIELD = 4;
	private final static int HEIGHT_FIELD = 5;
	
	private TableLayout layoutDynamic;
	private Spinner spin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_field_to_form);
		
		layoutDynamic = ( TableLayout ) findViewById(R.id.layoutDynamicAddField);
		layoutDynamic.removeAllViewsInLayout();
		
		spin = (Spinner)findViewById(R.id.spinner);
		String[] listeStrings = {"Texte","Numérique","Boolean","Liste","Photo","Hauteur"};
		spin.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listeStrings));
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				layoutDynamic = (TableLayout) findViewById(R.id.layoutDynamicAddField );
				layoutDynamic.removeAllViewsInLayout();
				
				List<EditText>	allEds =  new ArrayList<EditText>();
				
//				switch (position){
//					case TEXT_FIELD :
//						allEds.add(addChampsTextDynamic("Nom"));
//						allEds.add(addChampsNumericDynamic("Longueur"));
//						addButtons(TEXT_CHAMPS, allEds);
//						break;
//					case NUMERIC_FIELD :
//						allEds.add(addChampsTextDynamic("Nom"));
//						allEds.add(addChampsNumericDynamic("Longueur"));
//						allEds.add(addChampsNumericDynamic("Max"));
//						allEds.add(addChampsNumericDynamic("Min"));
//						addButtons(NUMERIC_CHAMPS, allEds);
//						break;
//					case BOOLEAN_FIELD :
//						allEds.add(addChampsTextDynamic("Nom"));
//						addButtons(BOOLEAN_CHAMPS, allEds);
//						break;
//					case LIST_FIELD :
//						allEds.add(addChampsTextDynamic("Nom"));
//						allEds.add(addChampsTextDynamic("Valeurs (séparées par un /)"));
//						addButtons(LISTE_CHAMPS, allEds);
//						break;
//					case PICTURE_FIELD :
//						addButtons(PHOTO_CHAMPS, allEds);
//						break;
//					case HEIGHT_FIELD :
//						addButtons(HAUTEUR_CHAMPS, allEds);
//						break;
//					default:
//				}

			}


			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_fieldto_form, menu);
		return true;
	}

}
