package fr.umlv.lastproject.smart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.location.LocationManager;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;
import fr.umlv.lastproject.smart.layers.GeometryLayer;
import fr.umlv.lastproject.smart.layers.LineGeometry;
import fr.umlv.lastproject.smart.layers.LineSymbology;
import fr.umlv.lastproject.smart.layers.PointGeometry;

/**
 * Main class for tracking
 * Starts and stops a track
 * @author Marc
 *
 */
public class GPSTrack {

	private final TRACK_MODE trackMode;
	private final GPS gps;
	private final List<TrackPoint> trackPoints;
	private final IGPSListener gpsListener;
	private final GeometryLayer geometryLayer;
	private boolean isFinished, isStarted;
	private final LineGeometry lineGeometry;
	private final String trackName;

	/**
	 * To know if we track by distance (meters) or time (seconds)
	 * @author Marc
	 *
	 */
	public enum TRACK_MODE{
		TIME(1), DISTANCE(10);

		private  int parameter;

		 TRACK_MODE(final int param){
			this.parameter=param;
		}

		 public int getParameter(){
			 return this.parameter;
		 }

		 public void setParameter(int parameter){
			 this.parameter=parameter;
		 }
	}

	public GPSTrack(final TRACK_MODE mode, final String trackName, final LocationManager lm, final SmartMapView mapView){

		isFinished=false;
		isStarted=false;
		this.trackName=trackName;
		this.trackMode=mode;
		this.gps=new GPS(lm);
		this.trackPoints=new ArrayList<TrackPoint>();
		this.gpsListener=new IGPSListener() {

			@Override
			public void actionPerformed(GPSEvent event) {
				final double latitude=event.getLatitude();
				final double longitude=event.getLongitude();
				final TrackPoint trackPoint=new TrackPoint(longitude, latitude, event.getAltitude(), event.getTime());
				trackPoints.add(trackPoint);

				lineGeometry.addPoint(new PointGeometry(latitude, longitude));
			}
		};
		gps.addGPSListener(gpsListener);
		this.geometryLayer=new GeometryLayer(mapView.getContext());
		this.geometryLayer.setType(GeometryType.LINE);
		lineGeometry=new LineGeometry();
		this.geometryLayer.addGeometry(lineGeometry);
		this.geometryLayer.setSymbology(new LineSymbology(5, Color.RED));
		mapView.getOverlays().add(geometryLayer);

	}

	/**
	 * Gets the graphics layer
	 * 
	 * @return the layer to add to the map view
	 */
	public GeometryLayer getGeometryLayer() {
		return geometryLayer;
	}

	/**
	 * List of track points that will be written in gpx file
	 * @return List of track points
	 */
	public List<TrackPoint> getTrackPoints() {
		return trackPoints;
	}



	/**
	 * Starts track if not already started
	 */
	public void startTrack(){
		if(!isStarted && !isFinished){
			isStarted=true;
			switch (this.trackMode) {
			case DISTANCE:
				gps.start(0, trackMode.getParameter());
				break;

			default:
				gps.start(trackMode.getParameter()*1000, 0);
				break;
			}
		}

	}


	/**
	 * Stops track and writes gpx files
	 * @throws IOException
	 */
	public void stopTrack() throws IOException{
		if(isStarted && !isFinished){
			gps.removeGPSListener(gpsListener);
			isFinished=true;
			/**Writing of gpx file */
			GPXWriter.writeGpxFile(trackName, trackPoints);
		}




	}



}