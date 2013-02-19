package fr.umlv.lastproject.smart.form;

import org.osmdroid.views.MapView;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import fr.umlv.lastproject.smart.database.DbManager;
import fr.umlv.lastproject.smart.database.GeometryRecord;
import fr.umlv.lastproject.smart.database.MissionRecord;
import fr.umlv.lastproject.smart.layers.Geometry;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;
import fr.umlv.lastproject.smart.layers.GeometryLayer;
import fr.umlv.lastproject.smart.layers.LineSymbology;
import fr.umlv.lastproject.smart.layers.PointSymbology;
import fr.umlv.lastproject.smart.layers.PolygonSymbology;
import fr.umlv.lastproject.smart.survey.Survey;
import fr.umlv.lastproject.smart.survey.SurveyStopListener;


/**
 * This class is used to create a mission 
 * 
 * @author thibault, maelle cabot
 *
 */
public class Mission {
	
	private static Mission mission = null  ;
		
	/* three type of survey are available : line, polygon, point */
	private  GeometryLayer pointLayer ;
	private  GeometryLayer lineLayer ;
	private  GeometryLayer polygonLayer ;
	
	private   Context context ;
	
	/* the name of the mission */
	private  int id ;
	private  String name ;
	
	/* the status (on / off) */
	private  boolean status = false ;
	
	private  MapView mapView;
	
	private  Form form ;
	
	
	private  Survey survey;

	/**
	 * 
	 * @param name of the mission
	 * @param context the context
	 */
	private Mission(String name,final Context context, final MapView mapview, Form f) {
		
		this.name = name ;
		this.context = context;
		this.mapView = mapview;
		this.form = f;

		pointLayer = new GeometryLayer(context) ;
		pointLayer.setType(GeometryType.POINT);
		pointLayer.setSymbology(new PointSymbology(10, Color.BLACK));
		
		lineLayer = new GeometryLayer(context) ;
		lineLayer.setType(GeometryType.LINE);
		lineLayer.setSymbology(new LineSymbology(10, Color.BLACK));
		
		polygonLayer = new GeometryLayer(context);
		polygonLayer.setType(GeometryType.POLYGON) ;
		polygonLayer.setSymbology(new PolygonSymbology(10, Color.BLACK));
		
		survey = new Survey(mapview);
		
		
		
	}
	
	public static  Mission getInstance(){
		return mission ;
	}
	
	/**
	 * 
	 * @param name of the mission
	 * @param context of the application
	 * @param mapview of the mission
	 * @return the new mission
	 */
	public static Mission createMission(String name, Context context, MapView mapview,  Form f){
		//if(Mission.getInstance().getStatus()) return null ;
		mission = new Mission(name, context, mapview, f);
		// ecriture en base 
		DbManager dbm = new DbManager() ;
		dbm.open(context);
		dbm.insertMission(new MissionRecord());
		dbm.close();

		return mission ;
	}

	private boolean getStatus() {
		return status;
	}

	/**
	 * 
	 * @return status of the mission
	 */
	public boolean startMission(){
		return status = true ;
	}
	
	/**
	 * 
	 * @return status of the mission
	 */
	public boolean stopMission(){
		return status = false ;
	}
	
	/**
	 * 
	 * @return the layer which containes the polygons of the mission
	 */
	public GeometryLayer getPolygonLayer(){
		return polygonLayer;
	}

	/**
	 * 
	 * @return the layer which contain the lines
	 */
	public GeometryLayer getLineLayer(){
		return lineLayer;
	}

	/**
	 * 
	 * @return the which contains the points
	 */
	public GeometryLayer getPointLayer(){
		return pointLayer;
	}
	
	/**
	 * 
	 * @return the form of this mission
	 */
	public Form getForm(){
		return form ;
	}
	
	/**
	 * 
	 * @param type of the survey to do
	 */
	public void startSurvey(GeometryType type){
		if(!status) return;
		switch (type) {
		case LINE:
			Log.d("", "mission survey line");
			survey.startSurvey(lineLayer);	
			survey.addStopListeners(new SurveyStopListener() {
				@Override
				public void actionPerformed(Geometry g) {
					form.openForm(context, g);

				}
			});

			break;
		case POINT :
			survey.startSurvey(pointLayer);
			survey.addStopListeners(new SurveyStopListener() {
				@Override
				public void actionPerformed(Geometry g) {
					form.openForm(context, g);

				}
			});

			break ;
		case POLYGON : 
			survey.startSurvey(polygonLayer);
			survey.addStopListeners(new SurveyStopListener() {
				@Override
				public void actionPerformed(Geometry g) {
					form.openForm(context, g);

				}
			});

			break ;
		default:
			break;
		}	
		
		survey.addStopListeners(new SurveyStopListener() {
			@Override
			public void actionPerformed(Geometry g) {
				
				Log.d("", "mission survey line db");

				DbManager dbManager = new DbManager() ;
				dbManager.open(context);
				dbManager.insertGeometry(new GeometryRecord(g,id));
				dbManager.close();
				survey.stop() ;
			}
		});

	}
	
	public String getTitle(){
		return name;
	}
	
	public void setId(int id){
		this.id = id;
		Log.d("", "id mission setid"+id);
	}
	
	public int getId(){
		return id;
	}
}
