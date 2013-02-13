package fr.umlv.lastproject.smart.dataimport;

import android.content.Context;
import diewald_shapeFile.files.shp.shapeTypes.ShpPoint;
import diewald_shapeFile.files.shp.shapeTypes.ShpPolyLine;
import diewald_shapeFile.files.shp.shapeTypes.ShpPolygon;
import diewald_shapeFile.files.shp.shapeTypes.ShpShape;
import diewald_shapeFile.shapeFile.ShapeFile;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;
import fr.umlv.lastproject.smart.layers.GeometryLayer;
import fr.umlv.lastproject.smart.layers.Layer;
import fr.umlv.lastproject.smart.layers.Line;
import fr.umlv.lastproject.smart.layers.Point;
import fr.umlv.lastproject.smart.layers.Polygon;

public class shpImport {

	public static Layer getLayerFromShp(String file, Context context){

		try {
			ShapeFile shp = new ShapeFile("", file).READ();
			ShpShape.Type type = shp.getSHP_shapeType();

			switch(type){
				case 	Point :{
					return getLayerFromPointShp(shp,context); 
				}
				case PolyLine :{
					return getLayerFromPolylineShp(shp,context);
				}
				case Polygon :{
					return getLayerFromPolygonShp(shp,context) ;
				}
				default : return null ;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	private static Layer getLayerFromPolygonShp(ShapeFile shp, Context context) {
		
		GeometryLayer gl = new GeometryLayer(context);
		gl.setType(GeometryType.POLYGON);
		
		for(int i=0;i<shp.getSHP_shapeCount() ; i++){
			Polygon p = new Polygon() ;
					
			ShpPolygon polygon = shp.getSHP_shape(i);			
			double[][] points = polygon.getPoints() ;

			for(int j = 0 ; j < polygon.getNumberOfPoints();j++){
				p.addPoint(new Point(points[j][0], points[j][1]));
			}
			gl.addGeometry(p);
		}
		return gl;
	}

	private static Layer getLayerFromPointShp(ShapeFile shp, Context context){
		
		GeometryLayer gl = new GeometryLayer(context);
		
		gl.setType(GeometryType.POINT);
		
		for ( int i=0 ; i < shp.getSHP_shapeCount() ; i++){
			ShpPoint point = shp.getSHP_shape(i);
			double lat = point.getPoint()[0] ;
			double lon = point.getPoint()[1] ;
			gl.addGeometry(new fr.umlv.lastproject.smart.layers.Point(lat, lon));
		}
		
		return gl;
	}
	
	
	private static Layer getLayerFromPolylineShp(ShapeFile shp, Context context){

		GeometryLayer gl = new GeometryLayer(context);
		gl.setType(GeometryType.LINE);
		
		for(int i=0;i<shp.getSHP_shapeCount() ; i++){
			Line line = new Line();
			ShpPolyLine shpline = shp.getSHP_shape(i);
			double[][] points = shpline.getPoints() ;
			for(int j = 0 ; j < shpline.getNumberOfPoints();j++){
				line.addPoint(new Point(points[j][0], points[j][1]));
			}
		}
		return null;
	}


}
