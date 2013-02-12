package fr.umlv.lastproject.smart;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;


/**
 * 
 * @author thibault
 * Description : This class is used to show to the user where he is located on the map
 *  We can see a point describing his position
 *  And a circle represent the buffer of the accurency
 */

public class MyPositionOverlay extends Overlay{

	/* coordonates of the users's positions */
	private double latitude ;
	private double longitude ;
	/* accurency of this position */
	private float accurency ;
	/* The Paint of the layer */
	private Paint paint = new Paint() ;
	
	public MyPositionOverlay(Context ctx) {
		super(ctx);
	}

	
	/* This function is called to draw this layer */
	@Override
	protected void draw(Canvas arg0, MapView arg1, boolean arg2) {
		paint.setColor(Color.RED) ;
		paint.setStyle(Style.FILL_AND_STROKE) ;
		paint.setStrokeWidth(5);
		paint.setAlpha(77);
		
		Point p = arg1.getProjection().toMapPixels(new GeoPoint(latitude, longitude), null);
		arg0.drawCircle(p.x, p.y, arg1.getProjection().metersToEquatorPixels(accurency), paint);
		paint.setStrokeWidth(5);
		

		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setColor(Color.BLUE);
		paint.setAlpha(255);
		arg0.drawCircle(p.x, p.y, 5, paint);

	}

	public void setLatitiude(double latitude){
		this.latitude = latitude;
	}
	
	public void setLongitude(double longitude){
		this.longitude = longitude;
	}
	
	public void setAccurency(float accurency){
		this.accurency = accurency;
	}


	public void updatePosition(GPSEvent event) {
		setAccurency(event.getAccuracy());
		setLatitiude(event.getLatitude()) ;
		setLongitude(event.getLongitude()) ;
		
	}


}
