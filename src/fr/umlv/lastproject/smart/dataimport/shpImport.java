package fr.umlv.lastproject.smart.dataimport;

import diewald_shapeFile.files.shp.shapeTypes.ShpPoint;
import diewald_shapeFile.files.shp.shapeTypes.ShpPolyLine;
import diewald_shapeFile.files.shp.shapeTypes.ShpPolygon;
import diewald_shapeFile.files.shp.shapeTypes.ShpShape;
import diewald_shapeFile.shapeFile.ShapeFile;

public class shpImport {

	public static Object getLayerFromShp(String file){

		try {
			ShapeFile shp = new ShapeFile("", file).READ();
			ShpShape.Type type = shp.getSHP_shapeType();

			switch(type){
				case 	Point :{
					return getLayerFromPointShp(shp); 
				}
				case PolyLine :{
					return getLayerFromPolylineShp(shp);
				}
				case Polygon :{
					return getLayerFromPolygonShp(shp) ;
				}
				default : return null ;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	private static Object getLayerFromPolygonShp(ShapeFile shp) {
		for(int i=0;i<shp.getSHP_shapeCount() ; i++){
			// creation d'une ligne 
			ShpPolygon polygon = shp.getSHP_shape(i);
			double[][] points = polygon.getPoints() ;
			// point.x = points[i][0]
			// point.y = points[i][1]
			//ligne.addPoint(point) ;
		}
		return null;
	}

	private static Object getLayerFromPointShp(ShapeFile shp){
		//creation du layer point
		for ( int i=0 ; i < shp.getSHP_shapeCount() ; i++){
			ShpPoint point = shp.getSHP_shape(i);
			double lat = point.getPoint()[0] ;
			double lon = point.getPoint()[1] ;
			// creation du point
			//ajout dans le layer
		}
		//return le layer
		
		return null;
	}
	
	
	private static Object getLayerFromPolylineShp(ShapeFile shp){
		// creation du layer ligne
		for(int i=0;i<shp.getSHP_shapeCount() ; i++){
			// creation d'une ligne 
			ShpPolyLine line = shp.getSHP_shape(i);
			double[][] points = line.getPoints() ;
			// point.x = points[i][0]
			// point.y = points[i][1]
			//ligne.addPoint(point) ;
		}
		return null;
	}


}
