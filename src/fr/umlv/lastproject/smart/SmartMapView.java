package fr.umlv.lastproject.smart;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osmdroid.views.MapView;

import android.content.Context;
import android.util.AttributeSet;
import fr.umlv.lastproject.smart.geotiff.TMSOverlay;

/**
 * MapView with geoTIFFLayers notion
 * @author Marc
 *
 */
public class SmartMapView extends MapView {

//	private static final String WORLD_MAP_FOLDER = "Test";
//	private static final int WORLD_MAP_MIN_ZOOM = 0;
//	private static final int WORLD_MAP_MAX_ZOOM = 4;
//	private static final String WORLD_MAP_EXTENSION = ".png";
	


	
	public SmartMapView(final Context context, final AttributeSet set) {
		super(context, set);
		this.geoTIFFOverlays = new ArrayList<TMSOverlay>();
		//super.getOverlayManager().getTilesOverlay().setEnabled(false);

//		TMSOverlay worldOverlay;
//		
//			worldOverlay = DataImport.importGeoTIFFFileZIP(WORLD_MAP_FOLDER,
//					context, WORLD_MAP_MIN_ZOOM, WORLD_MAP_MAX_ZOOM,
//					WORLD_MAP_EXTENSION);
//			
//			addGeoTIFFOverlay(worldOverlay);
			
		

	}

	private final List<TMSOverlay> geoTIFFOverlays;

	/**
	 * Adds a {@link TMSOverlay} (Tile Map Service Overlay)
	 * @param overlay
	 */
	public void addGeoTIFFOverlay(final TMSOverlay overlay) {
		geoTIFFOverlays.add(overlay);
		getOverlays().add(0, overlay);

	}

	/**
	 * Removes a {@link TMSOverlay}
	 * @param overlay
	 */
	public void removeGeoTIFFOverlay(final TMSOverlay overlay) {
		getOverlays().remove(overlay);
	}

	/**
	 * Clears the map layers
	 */
	public void clear() {
		getOverlays().clear();
		geoTIFFOverlays.clear();
	}

	/**
	 * Gets an unmodifiable view of all the TMS Overlays 
	 * 
	 * 
	 * @return {@link List}<{@link TMSOverlay}> 
	 */
	public List<TMSOverlay> getGeoTIFFOverlays() {
		return Collections.unmodifiableList(this.geoTIFFOverlays);
	}
	


}
