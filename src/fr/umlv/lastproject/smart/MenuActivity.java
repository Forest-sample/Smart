package fr.umlv.lastproject.smart;

import java.util.LinkedList;
import java.util.List;
import java.io.IOException;

import org.osmdroid.events.MapAdapter;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.overlay.DirectedLocationOverlay;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;
import fr.umlv.lastproject.smart.database.DbManager;
import fr.umlv.lastproject.smart.database.FormRecord;
import fr.umlv.lastproject.smart.database.GeometryRecord;
import fr.umlv.lastproject.smart.database.TextFieldRecord;
import fr.umlv.lastproject.smart.form.BooleanField;
import fr.umlv.lastproject.smart.form.CreateFormActivity;
import fr.umlv.lastproject.smart.form.Form;
import fr.umlv.lastproject.smart.form.ListField;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import fr.umlv.lastproject.smart.GPSTrack.TRACK_MODE;
import fr.umlv.lastproject.smart.browser.utils.FileUtils;
import fr.umlv.lastproject.smart.dialog.AlertTrackDialog;
import fr.umlv.lastproject.smart.form.Mission;
import fr.umlv.lastproject.smart.form.NumericField;
import fr.umlv.lastproject.smart.form.PictureField;
import fr.umlv.lastproject.smart.form.TextField;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;
import fr.umlv.lastproject.smart.utils.SmartConstants;


public class MenuActivity extends Activity {

	/**
	 * 
	 * @author thibault Brun
	 * @author tanios Faddoul Description : This class contains the Menus
	 *         container
	 * 
	 */

	private static final int HOME_VIEW = 1;
	private static final int LAYERS_VIEW = 2;
	private SmartMapView mapView;
	
	private static final int CREATE_MISSION = 0;
	private static final int CREATE_FORM = 1;
	private static final int POINT_SURVAY = 2;
	private static final int LINE_SURVAY = 3;
	private static final int POLYGON_SURVAY = 4;

	private MapController mapController;
	private OverlayManager overlayManager;
	private GPS gps;
	private LocationManager locationManager;
	private InfoOverlay infoOverlay;
	private DirectedLocationOverlay directedLocationOverlay;
	private View centerMap;
	private boolean isMapTracked = true;
	private GeoPoint lastPosition = new GeoPoint(0, 0);
	private boolean missionCreated = false;

	private TextView formPath;
	private String missionName;
	
	private GPSTrack gpsTrack;
	private static final int GPS_TRACK=5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_smart);
		initMap();
		initGps();

		ImageButton home = (ImageButton) findViewById(R.id.home);
		home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent homeActivity = new Intent(MenuActivity.this,
						HomeActivity.class);
				homeActivity.putExtra("missionCreated", missionCreated);
				startActivityForResult(homeActivity, SmartConstants.HOME_VIEW);
			}
		});

		ImageButton layers = (ImageButton) findViewById(R.id.layers);
		layers.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent layersActivity = new Intent(MenuActivity.this,
						LayersActivity.class);
				startActivityForResult(layersActivity,
						SmartConstants.LAYERS_VIEW);
			}
		});
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle(R.string.exit);
		alertDialog.setMessage(R.string.exitMsg);
		alertDialog.setNegativeButton(R.string.no, null);
		alertDialog.setPositiveButton(R.string.yes, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		alertDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_smart, menu);
		menu.add(0, 1, 0, R.string.hideInfoZone);
		menu.add(0, 2, 0, R.string.gpsSettings);
		return true;
	}

	/**
	 * This method is use to init the map
	 */
	public void initMap() {
		mapView = (SmartMapView) findViewById(R.id.mapview);
		mapController = mapView.getController();
		overlayManager = mapView.getOverlayManager();
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setClickable(true);
		mapView.setMultiTouchControls(true);
		mapController.setZoom(SmartConstants.DEFAULT_ZOOM);
		overlayManager.add(new ScaleBarOverlay(this));

		directedLocationOverlay = new DirectedLocationOverlay(this);
		directedLocationOverlay.setShowAccuracy(true);
		overlayManager.add(directedLocationOverlay);

		mapView.setMapListener(new MapAdapter() {
			@Override
			public boolean onScroll(ScrollEvent event) {
				
				isMapTracked = false;
				centerMap.setVisibility(View.VISIBLE);
				return super.onScroll(event);
			}
		});

		infoOverlay = new InfoOverlay(findViewById(R.id.table));
		// centerOverlay = new CenterOverlay(findViewById(R.id.centermap));
		centerMap = findViewById(R.id.centermap);

		centerMap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				isMapTracked = true;
				mapController.setCenter(lastPosition);
				centerMap.setVisibility(View.INVISIBLE);
			}
		});

		/**
		 * Exemple d'utilisation d'un shapefile
		 */
		/*
		 * GeometryLayer gltest = DataImport.importShapeFile(this,
		 * "/storage/sdcard0/Download/shp/TestPolygon.shp");
		 * 
		 * Log.d("layer retourne", "Layer retourne "+gltest.toString());
		 * 
		 * gltest.setSymbology(new PolygonSymbology(30, Color.BLACK));
		 * 
		 * overlayManager.add(gltest) ;
		 */

		/**
		 * Exemple d'utilisation d'une mission
		 */
		/*
		 * Mission.createMission("ma mission thibault yoyo",
		 * getApplicationContext(), mapView);
		 * Mission.getInstance().startMission();
		 * overlayManager.add(Mission.getInstance().getPolygonLayer() ) ;
		 * overlayManager.add(Mission.getInstance().getLineLayer() ) ;
		 * overlayManager.add(Mission.getInstance().getPointLayer() ) ;
		 * Mission.getInstance().startSurvey(GeometryType.POLYGON );
		 * Mission.getInstance().stopMission();
		 */

	}

	/**
	 * This method is use to connect the GPS to the positionOverlay
	 */
	public void initGps() {

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		gps = new GPS(locationManager);

		if (!gps.isEnabled(locationManager)) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle(R.string.gpsSettings);
			alertDialog.setMessage(R.string.gpsMessage);

			alertDialog.setNegativeButton(R.string.cancel, null);

			alertDialog.setPositiveButton(R.string.validate,
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							startActivity(intent);

						}
					});

			alertDialog.show();
		}

		gps.start(SmartConstants.GPS_REFRESH_TIME,
				SmartConstants.GPS_REFRESH_DISTANCE);
		gps.addGPSListener(new IGPSListener() {

			@Override
			public void actionPerformed(GPSEvent event) {
				/* Init Position Overlay */

				lastPosition = new GeoPoint(event.getLatitude(), event
						.getLongitude());

				if (isMapTracked) {
					mapController.setCenter(lastPosition);
				}
				/* Init Informations zone */
				infoOverlay.updateInfo(event);

				/* change position marker */
				directedLocationOverlay.setLocation(new GeoPoint(event
						.getLatitude(), event.getLongitude()));
				directedLocationOverlay.setAccuracy((int) event.getAccuracy());
				directedLocationOverlay.setBearing(event.getBearing());
				mapView.invalidate();

			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			if (findViewById(R.id.table).getVisibility() == View.INVISIBLE) {
				item.setTitle(R.string.hideInfoZone);
				findViewById(R.id.table).setVisibility(View.VISIBLE);
			} else {
				item.setTitle(R.string.showInfoZone);
				findViewById(R.id.table).setVisibility(View.INVISIBLE);

			}
			break;
		case 2:
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}

		return super.onOptionsItemSelected(item);
	}
	
	public void openMisisonDialog() {
		LayoutInflater inflater = LayoutInflater.from(this);
		final View createMissionDialog = inflater.inflate(
				R.layout.create_mission_dialog, null);

		final Context c = this;
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(createMissionDialog);
		builder.setTitle(R.string.mission);

		final Button openBrowser = (Button) createMissionDialog
				.findViewById(R.id.selectFormButton);
		formPath = (TextView) createMissionDialog.findViewById(R.id.formPath);

		final TextView textViewMissionName = ((TextView) createMissionDialog
				.findViewById(R.id.missionNameValue));

		RadioGroup radioForm = (RadioGroup) createMissionDialog
				.findViewById(R.id.radioForm);

		radioForm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (openBrowser.getVisibility() == View.GONE) {
					openBrowser.setVisibility(View.VISIBLE);
					formPath.setVisibility(View.VISIBLE);
				} else {
					openBrowser.setVisibility(View.GONE);
					formPath.setVisibility(View.GONE);
				}

			}
		});

		openBrowser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = FileUtils.createGetContentIntent(
						FileUtils.XML_TYPE,
						Environment.getExternalStorageDirectory() + "");
				startActivityForResult(intent, SmartConstants.BROWSER_ACTIVITY);
			}
		});

		builder.setPositiveButton(R.string.validate, new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				missionName = textViewMissionName.getText().toString();
				Form f = new Form("pouet");
				f.addField(new TextField("toto"));
				Mission.createMission(missionName, c,
						mapView, f);
				missionCreated = Mission.getInstance().startMission();
				overlayManager.add(Mission.getInstance().getPolygonLayer());
				overlayManager.add(Mission.getInstance().getLineLayer());
				overlayManager.add(Mission.getInstance().getPointLayer());
			}
		});
		builder.show();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case SmartConstants.HOME_VIEW:
				Integer index = (Integer) data.getSerializableExtra("position");

				switch (index) {
				case SmartConstants.CREATE_MISSION:
					if (missionCreated) {
						missionCreated = Mission.getInstance().stopMission();

					} else {
						openMisisonDialog();

					}
//					Form f = new Form("MonForm");
//					f.addField(new TextField("titi"));
//					f.addField(new NumericField("toto", 5, 10));
//					f.addField(new BooleanField("alors"));
//					List<String> l = new LinkedList<String>();
//					l.add("pouet");
//					l.add("pouet2");

					//f.addField(new ListField("liste", l));


					
					
//					Mission.createMission("ma mission thibault yoyo",
//							this, mapView, f);
//					Mission.getInstance().startMission();
//					overlayManager.add(Mission.getInstance().getPolygonLayer());
//					overlayManager.add(Mission.getInstance().getLineLayer());
//					overlayManager.add(Mission.getInstance().getPointLayer());

					break;
				case SmartConstants.CREATE_FORM:
					LayoutInflater factory = LayoutInflater.from(this);
					final View alertDialogView = factory.inflate(
							fr.umlv.lastproject.smart.R.layout.name_form,
							null);
					final AlertDialog.Builder adb = new AlertDialog.Builder(this);
				
					adb.setView(alertDialogView);
					adb.setTitle(getResources().getString(R.string.CreateForm));
					
					adb.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							
							EditText et = (EditText) alertDialogView
									.findViewById(fr.umlv.lastproject.smart.R.id.nameForm);
					
							
							Intent intent = new Intent(MenuActivity.this, CreateFormActivity.class);
							intent.putExtra("nameForm", et.getText().toString());
							
							startActivity(intent);	

						}
					});

					adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

						}
					});
					adb.show();
					
					break;
				case SmartConstants.POINT_SURVEY:
					Mission.getInstance().startSurvey(GeometryType.POINT);
					break;

				case SmartConstants.LINE_SURVEY:
					Mission.getInstance().startSurvey(GeometryType.LINE);
					break;

				case SmartConstants.POLYGON_SURVEY:
					Mission.getInstance().startSurvey(GeometryType.POLYGON);
					break;
				case GPS_TRACK:
					if(gpsTrack==null){
						final AlertTrackDialog trackDialog=new AlertTrackDialog(this);
						trackDialog.show();
						break;
					}
					else{
						try {
							gpsTrack.stopTrack();
							gpsTrack=null;
						} catch (IOException e) {
							gpsTrack=null;
							Toast.makeText(this, R.string.track_error, Toast.LENGTH_LONG).show();
						}
						
					}
					break;
				default:
					// Mission.getInstance().stopMission();
					break;
				}

			}
		}
	}
	public void createGPSTrack(final String name, final TRACK_MODE trackMode) {
		gpsTrack=new GPSTrack(trackMode, name, locationManager, mapView);
		gpsTrack.startTrack();
		
	}
}
