package fr.umlv.lastproject.smart.dataimport;

import android.content.Context;
import android.util.Log;
import diewald_shapeFile.files.shp.shapeTypes.ShpPoint;
import diewald_shapeFile.files.shp.shapeTypes.ShpPolyLine;
import diewald_shapeFile.files.shp.shapeTypes.ShpPolygon;
import diewald_shapeFile.files.shp.shapeTypes.ShpShape;
import diewald_shapeFile.shapeFile.ShapeFile;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;
import fr.umlv.lastproject.smart.layers.GeometryLayer;
import fr.umlv.lastproject.smart.layers.LineGeometry;
import fr.umlv.lastproject.smart.layers.PointGeometry;
import fr.umlv.lastproject.smart.layers.PolygonGeometry;

public class ShpImport {

	public static GeometryLayer getLayerFromShp(String file, Context context) {

		try {
			Log.d("shapefile", "debut");
			String[] split = file.split("/");
			String path = "";
			for (int i = 0; i < split.length - 1; i++) {
				path += split[i] + "/";
			}

			// String filename = (split[split.length-1].split("."))[0] ;
			String fn = split[split.length - 1];
			fn = fn.replaceFirst(".shp", "");
			Log.d("", "path" + path);

			Log.d("", "path" + fn);

			ShapeFile shp = new ShapeFile(path, fn).READ();
			ShpShape.Type type = shp.getSHP_shapeType();

			switch (type) {
			case Point: {
				return getLayerFromPointShp(shp, context);
			}
			case PolyLine: {
				return getLayerFromPolylineShp(shp, context);
			}
			case Polygon: {
				return getLayerFromPolygonShp(shp, context);
			}
			default:
				return null;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private static GeometryLayer getLayerFromPolygonShp(ShapeFile shp,
			Context context) {

		GeometryLayer gl = new GeometryLayer(context);

		gl.setType(GeometryType.POLYGON);

		for (int i = 0; i < shp.getSHP_shapeCount(); i++) {
			PolygonGeometry p = new PolygonGeometry();

			ShpPolygon polygon = shp.getSHP_shape(i);
			double[][] points = polygon.getPoints();

			for (int j = 0; j < polygon.getNumberOfPoints(); j++) {
				p.addPoint(new PointGeometry(points[j][1], points[j][0]));
				Log.d("polygon", "coord " + points[j][1] + " " + points[j][0]);

			}
			gl.addGeometry(p);
		}

		return gl;
	}

	private static GeometryLayer getLayerFromPointShp(ShapeFile shp,
			Context context) {

		GeometryLayer gl = new GeometryLayer(context);

		gl.setType(GeometryType.POINT);

		for (int i = 0; i < shp.getSHP_shapeCount(); i++) {
			ShpPoint point = shp.getSHP_shape(i);
			double lat = point.getPoint()[1];
			double lon = point.getPoint()[0];
			gl.addGeometry(new fr.umlv.lastproject.smart.layers.PointGeometry(lat, lon));

		}

		return gl;
	}

	private static GeometryLayer getLayerFromPolylineShp(ShapeFile shp,
			Context context) {

		GeometryLayer gl = new GeometryLayer(context);
		gl.setType(GeometryType.LINE);

		for (int i = 0; i < shp.getSHP_shapeCount(); i++) {
			LineGeometry line = new LineGeometry();
			ShpPolyLine shpline = shp.getSHP_shape(i);
			double[][] points = shpline.getPoints();

			for (int j = 0; j < shpline.getNumberOfPoints(); j++) {
				line.addPoint(new PointGeometry(points[j][1], points[j][0]));
			}
			gl.addGeometry(line);
		}
		return gl;
	}

}