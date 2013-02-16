package fr.umlv.lastproject.smart.survey;

import java.util.ArrayList;

import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import android.R.layout;
import android.graphics.drawable.LayerDrawable;

import fr.umlv.lastproject.smart.layers.Geometry;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;
import fr.umlv.lastproject.smart.layers.GeometryLayer;
import fr.umlv.lastproject.smart.layers.GeometryLayerDoubleTapListener;
import fr.umlv.lastproject.smart.layers.GeometryLayerSingleTapListener;
import fr.umlv.lastproject.smart.layers.LineGeometry;
import fr.umlv.lastproject.smart.layers.PointGeometry;
import fr.umlv.lastproject.smart.layers.PolygonGeometry;


/**
 * 
 * This class is use to do a survey on the map
 * The survey could be a point, a line or a polygon
 * 
 * @author thibault B
 *
 */
public class Survey {
	
	private ArrayList<SurveyStopListener> stopListeners = new ArrayList<SurveyStopListener>();
	private MapView mapView;
	private  GeometryLayer layer ; 
	private GeometryLayerDoubleTapListener dlistener ;
	private GeometryLayerSingleTapListener slistener ;	
	Survey survey = null ;
	
	/**
	 * 
	 * @param type the type of the geometry which will be surveyed
	 */
	public Survey(MapView mapview) {
		this.mapView = mapview ;
		
	}
	
	
	public void startSurvey(final GeometryLayer layer){
		this.layer = layer ;
		layer.setEditable(true);

		
		if(layer.getType() == GeometryType.POINT){
			slistener = 
					new GeometryLayerSingleTapListener() {
				@Override
				public void actionPerformed(PointGeometry p) {
					layer.addGeometry(p);
					for(int i = 0 ; i < stopListeners.size() ; i++){
						stopListeners.get(i).actionPerformed(p) ;
					}
					layer.setEditable(false);
					mapView.invalidate();
				}
			};
			layer.addGeometryLayerSingleTapListener(slistener);
		}
		
		
		if(layer.getType() == GeometryType.LINE){
			final LineGeometry l = new LineGeometry();
			slistener = new GeometryLayerSingleTapListener() {
				
				@Override
				public void actionPerformed(PointGeometry p) {
					layer.getGeometries().remove(l);
					l.addPoint(p);
					layer.addGeometry(l);
					mapView.invalidate();
				}
			};
			layer.addGeometryLayerSingleTapListener(slistener);
			
			dlistener = new GeometryLayerDoubleTapListener() {
				
				@Override
				public void actionPerformed(PointGeometry p) {
					layer.getGeometries().remove(l);
					l.addPoint(p);
					layer.addGeometry(l);
					for(int i = 0 ; i < stopListeners.size() ; i++){
						stopListeners.get(i).actionPerformed(l) ;
					}
					layer.setEditable(false);
					mapView.invalidate();

				}
			};
			layer.addGeometryLayerDoubleTapListener(dlistener);
		}
		
		if(layer.getType() == GeometryType.POLYGON){
			final PolygonGeometry poly = new PolygonGeometry();
			
			slistener = new GeometryLayerSingleTapListener() {
				
				@Override
				public void actionPerformed(PointGeometry p) {
					layer.getGeometries().remove(poly);
					poly.addPoint(p);
					layer.addGeometry(poly);
					mapView.invalidate();
				}
			};
			layer.addGeometryLayerSingleTapListener(slistener);
			
			dlistener = new   GeometryLayerDoubleTapListener() {
				
				@Override
				public void actionPerformed(PointGeometry p) {
					layer.getGeometries().remove(poly);
					poly.addPoint(p);
					layer.addGeometry(poly);
					for(int i = 0 ; i < stopListeners.size() ; i++){
						stopListeners.get(i).actionPerformed(poly) ;
					}
					layer.setEditable(false);
					mapView.invalidate();

				}
			};
			layer.addGeometryLayerDoubleTapListener(dlistener);
		}

	}
	
	public void addStopListeners(SurveyStopListener listener){
		stopListeners.add(listener);
	}
	
	public void removeStopListeners(SurveyStopListener listener){
		stopListeners.remove(listener);
	}

	public void stop() {
		layer.removeGeometryLayerDoubleTapListener(dlistener);
		layer.removeGeometryLayerSingleTapListener(slistener);
		stopListeners = new ArrayList<SurveyStopListener>();
		
	}
}
