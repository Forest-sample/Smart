package fr.umlv.lastproject.smart;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import android.content.Context;
import android.graphics.Canvas;

import android.widget.ImageView;

public class CenterOverlay extends Overlay{

	private ImageView imageTOto;
	
	public CenterOverlay(Context c) {
		super(c);
		imageTOto = new ImageView(null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void draw(Canvas arg0, MapView arg1, boolean arg2) {
		
		//TODO
	}
	
	public void isMaelleAndTanios(){
		int marc = 20 ;
	}
	
	

}
