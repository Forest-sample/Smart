package fr.umlv.lastproject.smart.form;

import org.osmdroid.views.MapView;

import android.content.Context;
import android.graphics.Color;
import fr.umlv.lastproject.smart.layers.Geometry;
import fr.umlv.lastproject.smart.layers.GeometryLayer;
import fr.umlv.lastproject.smart.layers.LineSymbology;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;
import fr.umlv.lastproject.smart.layers.PointSymbology;
import fr.umlv.lastproject.smart.layers.PolygonSymbology;
import fr.umlv.lastproject.smart.survey.Survey;
import fr.umlv.lastproject.smart.survey.SurveyStopListener;


/**
 * This class is used to create a mission 
 * 
 * @author thibault
 *
 */
public class Mission {
	
	/* three type of survey are available : line, polygon, point */
	private GeometryLayer pointLayer ;
	private GeometryLayer lineLayer ;
	private GeometryLayer polygonLayer ;
	
	/* the name of the mission */
	private String name ;
	
	/* the status (on / off) */
	private boolean status ;
	
	private MapView mapView;
	
	/*Form form ;*/
	
	
	Survey survey;

	/**
	 * 
	 * @param name of the mission
	 * @param context the context
	 */
	public Mission(String name, Context context, final MapView mapview) {
		
		this.name = name ;
		this.mapView = mapview;
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
	/*public Form getForm(){
		return form ;
	}*/
	
	/**
	 * 
	 * @param type of the survey to do
	 */
	public void startSurvey(GeometryType type){
		if(!status) return;
		switch (type) {
		case LINE:
			
			survey.startSurvey(lineLayer);
			survey.addStopListeners(new SurveyStopListener() {
				@Override
				public void actionPerformed(Geometry g) {
				}
			});
						
			break;
			
		case POINT :
			survey.startSurvey(pointLayer);
			survey.addStopListeners(new SurveyStopListener() {
				
				@Override
				public void actionPerformed(Geometry g) {
							
				}
			});
			
			break ;
			
		case POLYGON : 
			survey.startSurvey(polygonLayer);
			survey.addStopListeners(new SurveyStopListener() {
				
				@Override
				public void actionPerformed(Geometry g) {
					
				}
			});
			break ;

		default:
			break;
		}		
	}
}
