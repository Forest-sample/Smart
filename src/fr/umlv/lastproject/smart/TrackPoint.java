package fr.umlv.lastproject.smart;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Defines a track point
 * 1)Longitude
 * 2)Latitude
 * 3)Elevation
 * 4)Time
 * @author Marc
 *
 */
public class TrackPoint {

	private final double longitude;
	private final double latitude;
	private final double elevation;
	private final String time;
	 /**
     * Date format for a point timestamp.
     */
    private static final SimpleDateFormat POINT_DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	public TrackPoint(final double longitude, final double latitude, final double elevation, final Date time) {
		this.longitude=longitude;
		this.latitude=latitude;
		this.elevation=elevation;
		this.time=POINT_DATE_FORMATTER.format(time);
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getElevation() {
		return elevation;
	}

	public String getTime() {
		return time;
	}

}