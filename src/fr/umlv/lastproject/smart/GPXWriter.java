package fr.umlv.lastproject.smart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import android.os.Environment;

/**
 * Writes a GPX file.
 * 
 * @author Marc Barat
 * 
 */
public final class GPXWriter {

	/**
	 * XML header.
	 */
	private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";

	/**
	 * GPX opening tag
	 */
	private static final String TAG_GPX = "<gpx"
			+ " xmlns=\"http://www.topografix.com/GPX/1/1\""
			+ " version=\"1.1\""
			+ " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
			+ " xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd \">";


	private static final String TRACKS_FOLDER=Environment.getExternalStorageDirectory()+ "/SMART/"+"tracks/";

	private GPXWriter() {

	}

	/**
	 * Writes the GPX file
	 * 
	 * @param trackName
	 *            Name of the GPX track (metadata)
	 * @param cTrackPoints
	 *            Cursor to track points.
	 * @param cWayPoints
	 *            Cursor to way points.
	 * @param target
	 *            Target GPX file
	 * @throws IOException
	 */
	public static void writeGpxFile(String trackName,
			List<TrackPoint> trackPoints) throws IOException {
		if (trackName == null || trackPoints==null){
			throw new IllegalArgumentException();
		}
		if(trackPoints.isEmpty())return;
		final File appFolder = new File(TRACKS_FOLDER);
		appFolder.mkdirs();


		final File trackFile = new File(TRACKS_FOLDER+trackName+".gpx");

		final FileWriter fileWriter = new FileWriter(trackFile);

		fileWriter.write(XML_HEADER + "\n");
		fileWriter.write(TAG_GPX + "\n");

		writeTrackPoints(trackName, fileWriter, trackPoints);
		fileWriter.write("</gpx>");

		fileWriter.close();
	}

	/**
	 * Iterates on track points and write them.
	 * 
	 * @param trackName
	 *            Name of the track (metadata).
	 * @param fileWriter
	 *            Writer to the target file.
	 * @param trackPoints
	 *            {@link List}<{@link TrackPoint}>
	 * @throws IOException
	 */
	public static void writeTrackPoints(final String trackName,
			final FileWriter fileWriter, final List<TrackPoint> trackPoints)
			throws IOException {


		final StringBuilder builder = new StringBuilder();
		builder.append("\t" + "<trk>");
		builder.append("\t\t" + "<name>" + trackName + "</name>" + "\n");
		builder.append("\t\t" + "<trkseg>" + "\n");
		// fileWriter.write("\t" + "<trk>");
		// fileWriter.write("\t\t" + "<name>" + trackName + "</name>" + "\n");
		// fileWriter.write("\t\t" + "<trkseg>" + "\n");
		for (TrackPoint trackPoint : trackPoints) {
			builder.append("\t\t\t" + "<trkpt lat=\""
					+ trackPoint.getLatitude() + "\" " + "lon=\""
					+ trackPoint.getLongitude() + "\">");
			builder.append("<ele>" + trackPoint.getElevation() + "</ele>");
			builder.append("<time>" + trackPoint.getTime() + "</time>");
			builder.append("</trkpt>" + "\n");
		}

		// while (!c.isAfterLast() ) {
		// StringBuffer out = new StringBuffer();
		// out.append("\t\t\t" + "<trkpt lat=\""
		// + c.getDouble(c.getColumnIndex(DataHelper.Schema.COL_LATITUDE)) +
		// "\" "
		// + "lon=\"" +
		// c.getDouble(c.getColumnIndex(DataHelper.Schema.COL_LONGITUDE)) +
		// "\">");
		// out.append("<ele>" +
		// c.getDouble(c.getColumnIndex(DataHelper.Schema.COL_ELEVATION)) +
		// "</ele>");
		// out.append("<time>" + POINT_DATE_FORMATTER.format(new
		// Date(c.getLong(c.getColumnIndex(DataHelper.Schema.COL_TIMESTAMP)))) +
		// "</time>");



		// out.append("</trkpt>" + "\n");

		// fileWriter.write(out.toString());

		// c.moveToNext();
		// }
		builder.append("\t\t" + "</trkseg>" + "\n");
		builder.append("\t" + "</trk>" + "\n");
		// fileWriter.write("\t\t" + "</trkseg>" + "\n");
		// fileWriter.write("\t" + "</trk>" + "\n");
		fileWriter.write(builder.toString());
	}

}