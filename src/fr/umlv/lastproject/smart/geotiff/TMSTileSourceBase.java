package fr.umlv.lastproject.smart.geotiff;

import org.osmdroid.ResourceProxy.string;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.tilesource.BitmapTileSourceBase;

/**
 * 
 * @author Marc
 *
 */
public class TMSTileSourceBase extends BitmapTileSourceBase{

	public TMSTileSourceBase(String aName, string aResourceId,
			int aZoomMinLevel, int aZoomMaxLevel, int aTileSizePixels,
			String aImageFilenameEnding) {
		super(aName, aResourceId, aZoomMinLevel, aZoomMaxLevel, aTileSizePixels,
				aImageFilenameEnding);
		
	}
	
	/**
	 * Default implementation doesn't work with TMS specification
	 */
	@Override
	public String getTileRelativeFilenameString(MapTile tile) {
 		final StringBuilder sb = new StringBuilder();
		sb.append(pathBase());
		sb.append('/');
		sb.append(tile.getZoomLevel());
		sb.append('/');
		sb.append(tile.getX());
		sb.append('/');
 		int yTMS=(int) (Math.pow(2, tile.getZoomLevel()) - tile.getY() - 1);
		sb.append(yTMS);
		sb.append(imageFilenameEnding());
		return sb.toString();
	}
	

}
