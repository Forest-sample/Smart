package fr.umlv.lastproject.smart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GPS {
	private LocationManager locationManager;
	private Criteria criteria;
	private double latitude;
	private double longitude;
	private double altitude;
	private float accuracy;
	private float bearing;
	private float speed;
	private Date time;

	private List<IGPSListener> GPSListeners = new ArrayList<IGPSListener>();

	/**
	 * GPS Constructor
	 * 
	 * @param lm
	 *            : LocationManager of the GPS
	 */
	public GPS(LocationManager lm) {
		if(lm==null){
			throw new IllegalArgumentException();
		}
		this.locationManager = lm;
		this.criteria = new Criteria();
		this.criteria.setAltitudeRequired(false);
		this.criteria.setBearingRequired(true);
		this.criteria.setCostAllowed(true);
		this.criteria.setSpeedRequired(true);
	}

	/**
	 * Function which try if the LocationManager is enable
	 * 
	 * @param lm
	 *            : the location manager to try
	 * @return true if GPS is enable
	 */
	public boolean isEnabled(LocationManager lm) {
		return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	/**
	 * Function which return the list of compatible GPS with criteria
	 * 
	 * @return the list of compatible GPS
	 */
	public ArrayList<String> getValidateGPS() {
		return (ArrayList<String>) locationManager.getProviders(criteria, true);
	}

	/**
	 * Function which start the GPS Location Updates
	 * 
	 * @param ms
	 *            : time to refresh location
	 * @param meter
	 *            : distance to refresh location
	 */
	public void start(int ms, int meter) {
		// TODO Attention: test isEnabled a faire dans la classe qui appel le
		// start !
		this.locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, ms, meter,
				new LocationListener() {

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProviderEnabled(String provider) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProviderDisabled(String provider) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onLocationChanged(Location location) {
						

						longitude = location.getLongitude();
						latitude = location.getLatitude();
						altitude = location.getAltitude();
						accuracy = location.getAccuracy();
						bearing = location.getBearing();
						speed = location.getSpeed();
						time=new Date(location.getTime());

						for (int i = 0; i < GPSListeners.size(); i++) {
							GPSListeners.get(i).actionPerformed(
									new GPSEvent(latitude, longitude, altitude,
											accuracy, bearing, speed,time));
						}

					}
				});
	}

	/**
	 * Function which add the listener to the list
	 * 
	 * @param listener
	 *            : listener to add
	 */
	public void addGPSListener(IGPSListener listener) {
		GPSListeners.add(listener);
	}
	
	public void removeGPSListener(IGPSListener listener){
		GPSListeners.remove(listener);
	}

	/**
	 * 
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * 
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * 
	 * @return the altitude
	 */
	public double getAltitude() {
		return altitude;
	}

	/**
	 * 
	 * @return the accuracy
	 */
	public float getAccuracy() {
		return accuracy;
	}

	/**
	 * 
	 * @return the bearing
	 */
	public float getBearing() {
		return bearing;
	}

	/**
	 * 
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}
}
