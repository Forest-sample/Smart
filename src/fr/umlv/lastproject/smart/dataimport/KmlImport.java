package fr.umlv.lastproject.smart.dataimport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import fr.umlv.lastproject.smart.layers.Geometry;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;
import fr.umlv.lastproject.smart.layers.GeometryLayer;
import fr.umlv.lastproject.smart.layers.LineSymbology;
import fr.umlv.lastproject.smart.layers.PointSymbology;
import fr.umlv.lastproject.smart.layers.PolygonSymbology;
import fr.umlv.lastproject.smart.layers.Symbology;

/**
 * 
 * @author Thibault Douilly
 * 
 * @Description : Import a Kml file
 * 
 */
public class KmlImport {

	/**
	 * Return a list of GeometryLayer with all the geometry type in the kml
	 * 
	 * @param contexte
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */

	public static List<GeometryLayer> getLayersFromKML(String file,
			Context contexte) throws XmlPullParserException, IOException {
		Kml kml = new Kml(file);
		List<GeometryLayer> overlays = new ArrayList<GeometryLayer>();
		kml.readKml();
		for (GeometryType type : GeometryType.values()) {
			ArrayList<Geometry> geometries = (ArrayList<Geometry>) kml
					.getGeometries().get(type);
			GeometryLayer overlay = new GeometryLayer(contexte);
			for (Geometry geom : geometries) {
				overlay.addGeometry(geom);
			}

			Symbology symbology = null;
			if (type == GeometryType.POINT)
				symbology = new PointSymbology();
			else if (type == GeometryType.LINE)
				symbology = new LineSymbology();
			else if (type == GeometryType.POLYGON)
				symbology = new PolygonSymbology();
			overlay.setSymbology(symbology);
			overlay.setType(type);
			overlays.add(overlay);
		}
		return overlays;
	}

	/**
	 * Return a GeometryLayer of the kml with only the geometry type specified
	 * on params
	 * 
	 * @param contexte
	 * @param type
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static GeometryLayer getLayerFromKML(String file, GeometryType type,
			Context contexte) throws XmlPullParserException, IOException {
		Kml kml = new Kml(file);
		kml.readKml();
		ArrayList<Geometry> geometries = (ArrayList<Geometry>) kml
				.getGeometry(type);
		GeometryLayer overlay = new GeometryLayer(contexte);
		for (Geometry geom : geometries) {
			overlay.addGeometry(geom);
		}

		Symbology symbology = null;
		if (type == GeometryType.POINT)
			symbology = new PointSymbology();
		else if (type == GeometryType.LINE)
			symbology = new LineSymbology();
		else if (type == GeometryType.POLYGON)
			symbology = new PolygonSymbology();
		overlay.setSymbology(symbology);
		overlay.setType(type);

		return overlay;
	}
}