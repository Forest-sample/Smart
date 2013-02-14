package fr.umlv.lastproject.smart.layers;

import java.util.ArrayList;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;
import org.osmdroid.views.overlay.Overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;

/**
 * This class represent the geometry layer and draw it if it is contained in the
 * screen
 * 
 * @author Fad's, thibault brun
 * 
 */
public class GeometryLayer extends Overlay {

	private GeometryType type;
	private ArrayList<Geometry> geometries;
	private Projection projection;
	private Paint paint;
	private Symbology symbology;

	/**
	 * 
	 * @param ctx
	 *            : context for the GeometryLayer
	 */
	public GeometryLayer(Context ctx) {
		super(ctx);
		this.geometries = new ArrayList<Geometry>();
		// TODO Auto-generated constructor stub
	}

	
	public ArrayList<Geometry> getGeometries(){ return geometries;}
	
	public void editSymbology() {

	}

	/**
	 * Funcion which add geometry to the geometries list
	 * 
	 * @param geometry
	 */
	public void addGeometry(Geometry geometry) {
		this.geometries.add(geometry);
	}

	/**
	 * Function which set a type to the geometry
	 * 
	 * @param type
	 */
	public void setType(GeometryType type) {
		this.type = type;
	}

	/**
	 * Function which set a symbology to the geometry
	 * 
	 * @param symbology
	 */
	public void setSymbology(Symbology symbology) {
		this.symbology = symbology;
	}

	/**
	 * Function which return the symbology for the geometry
	 * 
	 * @return the symbology of geometry
	 */
	public Symbology getSymbology() {
		return this.symbology;
	}

	/**
	 * Function which test if the geometry is contained in the boundingBox
	 * 
	 * @param clipBound
	 *            : boundingBox of the screen
	 * @param geometryBoundingBox
	 *            : boundingBox of the geometry
	 * @return true the geometry is contened in the screen else false
	 */
	public boolean isInBoundingBox(Rect clipBound, Rect geometryBoundingBox) {

		Log.d("clibBound", "" + clipBound);
		Log.d("geometryBoundingBox",
				"" + geometryBoundingBox + " "
						+ Rect.intersects(clipBound, geometryBoundingBox));

		if (clipBound.contains(geometryBoundingBox)
				|| geometryBoundingBox.contains(clipBound)) {
			return true;
		}
		return false;
	}

	/**
	 * Function which draw the geometry if it is containes in the boundingBox
	 */
	@Override
	protected void draw(Canvas canvas, MapView mapView, boolean b) {
		projection = mapView.getProjection();
		paint = new Paint();
		paint.setColor(getSymbology().getColor());

		for (int i = 0; i < geometries.size(); i++) {

			switch (type) {
			case POINT:
				// Récupération de la géometrie et de la symbologie
				Point pointGeometry = (Point) geometries.get(i);
				PointSymbology pointSymbology = (PointSymbology) symbology;
				int radius = pointSymbology.getRadius();

				// Si le point est contenu dans la boundinBox
				// Transforme les coordonnées (lat/long) en pixels
					android.graphics.Point point = projection.toPixels(pointGeometry.getCoordinates(), null);
					// Dessin du point
				// Si le point est contenu dans la boundinBox
				if (canvas.getClipBounds().contains(point.x, point.y)) {
					Log.d("dessin", "dessin du point " + i);
					canvas.drawCircle(point.x, point.y, radius, paint);
				
				}
				break;

			case LINE:
				// Récupération de la géometrie et de sa symbologie
				Line lineGeometry = (Line) geometries.get(i);
				LineSymbology lineSymbology = (LineSymbology) symbology;
				paint.setStrokeWidth(lineSymbology.getThickness());

				// Récupéartion de la liste de points de la géometrie
				ArrayList<Point> linePoints = lineGeometry.getPoints();

				for (int j = 0; j < linePoints.size() - 1; j++) {

					Point pointA = linePoints.get(j);
					Point pointB = linePoints.get(j + 1);

					android.graphics.Point pointT = projection.toPixels(
							pointA.getCoordinates(), null);
					// Projection des coordonnées en pixel
					android.graphics.Point pixelA = projection.toPixels(
							pointA.getCoordinates(), null);
					android.graphics.Point pixelB = projection.toPixels(
							pointB.getCoordinates(), null);

					android.graphics.Point pointD = projection.toPixels(
							pointB.getCoordinates(), null);
					// Dessine la geometrie si elle est contenue dans la
					// boundingBox
					if (isInBoundingBox(
							canvas.getClipBounds(),
							new Rect(Math.max(pixelA.x, pixelB.x), Math.max(
									pixelA.y, pixelB.y), Math.min(pixelA.x,
									pixelB.x), Math.min(pixelA.y, pixelB.y)))) {

					canvas.drawLine(pointT.x, pointT.y, pointD.x, pointD.y,
							paint);
					Log.d("coordonnee ecran", "cpprd"+pointT.x + " "+pointT.y);

						Log.d("dessin", "dessin de la ligne " + j);
						canvas.drawLine(pixelA.x, pixelA.y, pixelB.x, pixelB.y,
								paint);
					}
				}

				break;

			case POLYGON:
				// Récupération de la géometrie et de sa symbologie
				Polygon polygonGeometry = (Polygon) geometries.get(i);
				PolygonSymbology polygonSymbology = (PolygonSymbology) symbology;
				paint.setStrokeWidth(polygonSymbology.getThickness());

				// Récupéartion de la liste de points de la géometrie
				ArrayList<Point> polygonPoints = polygonGeometry.getPoints();

				for (int j = 0; j < polygonPoints.size(); j++) {

					Point pointA = polygonPoints.get(j % polygonPoints.size());
					Point pointB = polygonPoints.get((j + 1)
							% polygonPoints.size());

					// Projection des coordonnées en pixel
					android.graphics.Point pixelA = projection.toPixels(
							pointA.getCoordinates(), null);
					android.graphics.Point pixelB = projection.toPixels(
							pointB.getCoordinates(), null);

					
					// Dessine la geometrie si elle est contenue dans la
					// boundingBox
					if (isInBoundingBox(
							canvas.getClipBounds(),
							new Rect(Math.max(pixelA.x, pixelB.x), Math.max(
									pixelA.y, pixelB.y), Math.min(pixelA.x,
									pixelB.x), Math.min(pixelA.y, pixelB.y)))) {

						Log.d("dessin", "dessin du polygon " + j);
						canvas.drawLine(pixelA.x, pixelA.y, pixelB.x, pixelB.y,
								paint);
					}
				}
				break;

			default:
				break;
			}
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return type.toString();
	}
}
