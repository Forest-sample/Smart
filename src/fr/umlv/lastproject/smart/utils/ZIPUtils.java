package fr.umlv.lastproject.smart.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import fr.umlv.lastproject.smart.browser.utils.FileUtils;

import android.util.Log;

/**
 * Utility Class to compress a folder of tiles into a zip Its also permits to
 * retrieve tiles metadata
 * 
 * @author Marc
 * 
 */
public class ZIPUtils {

	private static final int BUFFER = 2048;

	/**
	 * 
	 * @param directory
	 *            complete pathname to the tiles directory
	 * @return tiles metadata [0]->Root folder in zip archive / [1]->extension
	 *         of tiles / [2]-> minimum level of zoom / [3]-> maximum level of
	 *         zoom
	 * @throws IOException
	 */
	public static Object[] compress(final String directory) throws IOException {

		if (directory == null)
			throw new IllegalArgumentException();

		/** We retrieve tiles metadata */
		final Object[] metaData = getTilesMetaData(directory);

		BufferedInputStream origin = null;
		final FileOutputStream dest = new FileOutputStream(
				"/mnt/sdcard/osmdroid/"
						+ directory.substring(directory.lastIndexOf("/"))
						+ ".zip");

		final ZipOutputStream out = new ZipOutputStream(
				new BufferedOutputStream(dest));
		final byte data[] = new byte[BUFFER];

		final File dir = new File(directory);
		if (!dir.isDirectory())
			throw new IllegalArgumentException("can't compress a file");

		final List<String> filenames = listFiles(directory, true);
		for (int i = 0; i < filenames.size(); i++) {
			String file = filenames.get(i);
			final File lFile = new File(file);
			ZipEntry entry = null;
			FileInputStream inputStream = null;

			Log.v("TEST2", "Adding: " + entry);

			if (!lFile.isDirectory()) {

				inputStream = new FileInputStream(file);
				origin = new BufferedInputStream(inputStream, BUFFER);

				entry = new ZipEntry(file.split("/mnt/sdcard/osmdroid/")[1]);

				out.putNextEntry(entry);

				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();
			}

		}

		out.close();
		return metaData;

	}

	private static List<String> listFiles(String directory, boolean root) {
		final File file = new File(directory);

		final List<String> files = new ArrayList<String>();
		if (root)
			files.add(directory);
		final File[] tabFiles = file.listFiles();
		for (File f : tabFiles) {
			files.add(f.toString());
			if (f.isDirectory())
				files.addAll(listFiles(f.toString(), false));
		}
		return files;

	}

	/**
	 * 0 folderName 1 extension 2 min tile zoom level 3 max tile zoom level
	 * 
	 * @return
	 */
	private static Object[] getTilesMetaData(final String directory) {
		final Object[] metaData = new Object[4];

		final File file = new File(directory);

		metaData[0] = directory.substring(directory.lastIndexOf("/") + 1);
		final File[] tileDirectories = file.listFiles();
		final String lastZoomTileDirectory = tileDirectories[tileDirectories.length - 1]
				.toString();
		final String firstZoomTileDirectory = tileDirectories[0].toString();
		int minZoom, maxZoom;
		try {
			minZoom = Integer.parseInt(firstZoomTileDirectory
					.substring(firstZoomTileDirectory.lastIndexOf("/") + 1));
			maxZoom = Integer.parseInt(lastZoomTileDirectory
					.substring(lastZoomTileDirectory.lastIndexOf("/") + 1));
		} catch (Exception e) {
			minZoom = 0;
			maxZoom = 22;
		}

		String extension = null;
		for (File f : tileDirectories) {
			extension = getExtension(f);
		}

		metaData[1] = extension;
		metaData[2] = minZoom;
		metaData[3] = maxZoom;

		return metaData;

	}

	private static String getExtension(File file) {
		String extension = null;
		if (file.isFile())
			return FileUtils.getExtension(file.toString());
		else {
			for (File f : file.listFiles()) {
				extension = getExtension(f);
			}
			return extension;
		}
	}

}
