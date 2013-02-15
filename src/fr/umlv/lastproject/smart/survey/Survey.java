package fr.umlv.lastproject.smart.survey;

import java.util.ArrayList;

import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import fr.umlv.lastproject.smart.layers.Geometry;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;
import fr.umlv.lastproject.smart.layers.GeometryLayer;
import fr.umlv.lastproject.smart.layers.GeometryLayerDoubleTapListener;
import fr.umlv.lastproject.smart.layers.GeometryLayerSingleTapListener;
import fr.umlv.lastproject.smart.layers.Line;
import fr.umlv.lastproject.smart.layers.Point;
import fr.umlv.lastproject.smart.layers.Polygon;


/**
 * 
 * This class is use to do a survey on the map
 * The survey could be a point, a line or a polygon
 * 
 * @author thibault B
 *
 */
public class Survey {
	
	private Geometry geometry ;
	private GeometryType type ; 
	private MapController mapController ;
	private ArrayList<SurveyStopListener> stopListeners = new ArrayList<SurveyStopListener>();
	private MapView mapView;
	/**
	 * 
	 * @param type the type of the geometry which will be surveyed
	 */
	public Survey(MapView mapview) {
		this.mapView = mapview ;
		
	}
	
	public void startSurvey(final GeometryLayer layer){
		
		layer.setEditable(true);

		if(layer.getType() == GeometryType.POINT){
			Point p  ;
			layer.addGeometryLayerSingleTapListener(new GeometryLayerSingleTapListener() {
				@Override
				public void actionPerformed(Point p) {
					p = p ;
					layer.addGeometry(p);
					for(int i = 0 ; i < stopListeners.size() ; i++){
						stopListeners.get(i).actionPerformed(p) ;
					}
					layer.setEditable(false);
					mapView.invalidate();

				}
			});
		}
		
		if(layer.getType() == GeometryType.LINE){
			final Line l = new Line();
			
			layer.addGeometryLayerSingleTapListener(new GeometryLayerSingleTapListener() {
				
				@Override
				public void actionPerformed(Point p) {
					layer.getGeometries().remove(l);
					l.addPoint(p);
					layer.addGeometry(l);
					mapView.invalidate();
				}
			});
			
			layer.addGeometryLayerDoubleTapListener(new GeometryLayerDoubleTapListener() {
				
				@Override
				public void actionPerformed(Point p) {
					layer.getGeometries().remove(l);
					l.addPoint(p);
					layer.addGeometry(l);
					for(int i = 0 ; i < stopListeners.size() ; i++){
						stopListeners.get(i).actionPerformed(l) ;
					}
					layer.setEditable(false);
					mapView.invalidate();

				}
			});
		}
		
		if(layer.getType() == GeometryType.POLYGON){
			final Polygon poly = new Polygon();
			
			layer.addGeometryLayerSingleTapListener(new GeometryLayerSingleTapListener() {
				
				@Override
				public void actionPerformed(Point p) {
					layer.getGeometries().remove(poly);
					poly.addPoint(p);
					layer.addGeometry(poly);
					mapView.invalidate();
				}
			});
			
			layer.addGeometryLayerDoubleTapListener(new GeometryLayerDoubleTapListener() {
				
				@Override
				public void actionPerformed(Point p) {
					layer.getGeometries().remove(poly);
					poly.addPoint(p);
					layer.addGeometry(poly);
					for(int i = 0 ; i < stopListeners.size() ; i++){
						stopListeners.get(i).actionPerformed(poly) ;
					}
					layer.setEditable(false);
					mapView.invalidate();

				}
			});
		}

	}
	
	public void addStopListeners(SurveyStopListener listener){
		stopListeners.add(listener);
	}
	
	public void removeStopListeners(SurveyStopListener listener){
		stopListeners.remove(listener);
	}
}
