package fr.umlv.lastproject.smart.geotiff;

import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.views.overlay.TilesOverlay;
import android.content.Context;
import android.graphics.Color;

/**
 * Main class to import GeotTIFF Tiles
 * @author Marc
 *
 */
public class TMSOverlay extends TilesOverlay {

	private final int zoomLevelMax, zoomLevelMin;

	public TMSOverlay(final MapTileProviderBase aTileProvider,
			final Context aContext, final int minZoomLevel,
			final int maxZoomLevel) {
		super(aTileProvider, aContext);
		zoomLevelMax = maxZoomLevel;
		zoomLevelMin = minZoomLevel;
		if (zoomLevelMax < zoomLevelMin
				|| zoomLevelMax > OpenStreetMapTileProviderConstants.MAXIMUM_ZOOMLEVEL
				|| zoomLevelMin < OpenStreetMapTileProviderConstants.MINIMUM_ZOOMLEVEL)
			throw new IllegalArgumentException();
		setLoadingBackgroundColor(Color.TRANSPARENT);

	}

	public int getZoomLevelMax() {
		return zoomLevelMax;
	}

	public int getZoomLevelMin() {
		return zoomLevelMin;
	}
	
	
	
	
	

}
