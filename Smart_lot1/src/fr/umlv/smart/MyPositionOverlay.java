package fr.umlv.smart;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;

public class MyPositionOverlay extends Overlay {
	
	private double latitude;
	private double longitude;
	private float precision;

	public MyPositionOverlay(Context ctx) {
		super(ctx);
	}

	@Override
	protected void draw(Canvas arg0, MapView arg1, boolean arg2) {
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setStrokeWidth(5);
		paint.setAlpha(77);
		
		Point p = arg1.getProjection().toPixels(new GeoPoint(latitude, longitude), null);
		
		arg0.drawCircle(p.x, p.y, arg1.getProjection().metersToEquatorPixels(precision), paint);
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
		this.precision = accurency;
	}

}
