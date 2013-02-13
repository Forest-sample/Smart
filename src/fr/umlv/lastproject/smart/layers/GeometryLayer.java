package fr.umlv.lastproject.smart.layers;

import java.util.ArrayList;

import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;

public class GeometryLayer extends Layer {

	private GeometryType type;
	private ArrayList<Geometry> geometries;
	private Projection projection;
	private Paint paint;
	private Symbology symbology;
	private BoundingBoxE6 boundingBox;
	private BoundingBoxE6 boundingBoxPoints;

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
	 * Function which draw the geometry
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
				if (mapView.getBoundingBox().contains(
						new GeoPoint(pointGeometry.getLatitude(), pointGeometry
								.getLongitude()))) {

					// Transforme les coordonnées (lat/long) en pixels
					android.graphics.Point point = projection.toPixels(
							new GeoPoint(pointGeometry.getLatitude(),
									pointGeometry.getLongitude()), null);
					// Dessin du point
					canvas.drawCircle(point.x, point.y, radius, paint);
				}

				break;

			case LINE:
				// Récupération de la geometri et de sa symbologie
				Line lineGeometry = (Line) geometries.get(i);
				LineSymbology lineSymbology = (LineSymbology) symbology;
				paint.setStrokeWidth(lineSymbology.getThickness());

				ArrayList<Point> linePoints = lineGeometry.getPoints();

				for (int j = 0; j < linePoints.size() - 1; j++) {
					Point pointA = linePoints.get(j);
					Point pointB = linePoints.get(j + 1);

					android.graphics.Point pointT = projection.toPixels(
							new GeoPoint(pointA.getLatitude(), pointA
									.getLongitude()), null);

					android.graphics.Point pointD = projection.toPixels(
							new GeoPoint(pointB.getLatitude(), pointB
									.getLongitude()), null);

					canvas.drawLine(pointT.x, pointT.y, pointD.x, pointD.y,
							paint);
				}

				break;

			case POLYGON:
				// Récupération de la geometri et de sa symbologie
				Polygon polygonGeometry = (Polygon) geometries.get(i);
				PolygonSymbology polygonSymbology = (PolygonSymbology) symbology;
				paint.setStrokeWidth(polygonSymbology.getThickness());

				ArrayList<Point> polygonPoints = polygonGeometry.getPoints();

				for (int j = 0; j < polygonPoints.size(); j++) {
					Point pointA = polygonPoints.get(j % polygonPoints.size());
					Point pointB = polygonPoints.get((j + 1)
							% polygonPoints.size());

					android.graphics.Point pointT = projection.toPixels(
							new GeoPoint(pointA.getLatitude(), pointA
									.getLongitude()), null);

					android.graphics.Point pointD = projection.toPixels(
							new GeoPoint(pointB.getLatitude(), pointB
									.getLongitude()), null);

					canvas.drawLine(pointT.x, pointT.y, pointD.x, pointD.y,
							paint);

				}

				break;

			default:
				break;
			}
		}
	}
}
