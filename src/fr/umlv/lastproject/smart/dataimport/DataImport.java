package fr.umlv.lastproject.smart.dataimport;

import fr.umlv.lastproject.smart.layers.GeometryLayer;
import fr.umlv.lastproject.smart.layers.Layer;
import android.content.Context;

public class DataImport {
	
	public static GeometryLayer importShapeFile(Context context, String filename){
		return ShpImport.getLayerFromShp(filename, context);
	}
	
	
	
	
	

}
