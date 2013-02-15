package fr.umlv.lastproject.smart.dataimport;

import java.io.File;
import java.io.IOException;

import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.views.overlay.Overlay;


import fr.umlv.lastproject.smart.geotiff.TMSOverlay;
import fr.umlv.lastproject.smart.geotiff.TMSTileSourceBase;
import fr.umlv.lastproject.smart.layers.GeometryLayer;
import fr.umlv.lastproject.smart.utils.ZIPUtils;

import android.content.Context;
import android.util.Log;

public class DataImport {

	/**
	 * Used to import geoTIFF files previously cached and imported in
	 * /mnt/sdcard/osmdroid
	 * 
	 * The zipFile must contain a folder and in this folder subdirectories with
	 * images (png, jpg) Tiles should be generated with MapTiler
	 * 
	 * @param pathName
	 *            name of the folder containing the tiles (folder hierarchy MUST
	 *            be TMS Like)
	 * @param context
	 *            the context of the activity calling
	 * @param tileNameExtension
	 *            the format of tile images e.g png
	 * @return {@link Overlay}
	 */

	private static final int TILE_SIZE = 256;

	/**
	 * Import Tiles giving a path containing the tiles
	 * @param pathName
	 * @param context
	 * @return
	 * @throws IOException
	 */
	public static TMSOverlay importGeoTIFFFileFolder(final String pathName,
			final Context context)
			throws IOException {

		if (pathName == null)
			throw new IllegalArgumentException(
					"pathName null in importGeoTIFFFile");
		final File f = new File(pathName);

		if (!f.exists())
			throw new IllegalArgumentException("pathName doesn't exist");
		final ZIPUtils zip = new ZIPUtils();
		final Object[] metadata = zip.compress(pathName);
		final String folderName = (String) metadata[0];
		final String tileExtension = (String) metadata[1];
		final int minZoom = Integer.parseInt(metadata[2].toString());
		final int maxZoom = Integer.parseInt(metadata[3].toString());
		return importGeoTIFFFileZIP(folderName, context, minZoom, maxZoom, tileExtension);

	}
	
	/**
	 * Import tiles when a zip file already exists
	 * @param folderName
	 * @param context
	 * @param tileNameExtension
	 * @return
	 */
	public static TMSOverlay importGeoTIFFFileZIP(final String folderName,
			final Context context, final int minZoom, final int maxZoom, final String tileExtension){
		
		final MapTileProviderBasic mProvider = new MapTileProviderBasic(context);
		Log.d("TEST","Folder Name : "+folderName);
		Log.d("TEST","Extension : "+tileExtension);
		Log.d("TEST","Min Zoom : "+minZoom);
		Log.d("TEST","Max zoom : "+maxZoom);
		final TMSTileSourceBase tmsSource = new TMSTileSourceBase(folderName,
				null, minZoom, maxZoom, TILE_SIZE, tileExtension);

		mProvider.setTileSource(tmsSource);
		mProvider.setUseDataConnection(false);

		final TMSOverlay mTilesOverlay = new TMSOverlay(mProvider, context,
				minZoom, maxZoom);

		return mTilesOverlay;
	}
	
	public static GeometryLayer importShapeFile(Context context, String filename){
		return ShpImport.getLayerFromShp(filename, context);
	}

	
	


}
