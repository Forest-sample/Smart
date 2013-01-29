package fr.umlv.lastproject.smart;

import java.util.ArrayList;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GPS {

	

	private LocationManager lm ;
	private Criteria critere ;

	private double latitude;
	private double longitude;
	private double altitude;
	private double precision;
	
	private ArrayList<GPSListener> listener = new ArrayList<GPSListener>();
	
	public GPS(LocationManager lm){
		this.lm = lm;
	}
	
	public boolean titi(){return true;}
	
	

	public boolean isEnabled(LocationManager lm){
		return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}
	

	public void setLocationManager(LocationManager lm){
		this.lm = lm;
	}
	
	public void setCriteria(){
		this.critere = new Criteria();
		this.critere.setAltitudeRequired(true);
		this.critere.setBearingRequired(true);
		this.critere.setCostAllowed(false);
		this.critere.setSpeedRequired(true);
	}

	public ArrayList<String> getValidateGPS(){
		return (ArrayList<String>) lm.getProviders(critere, true);
	}

	public void startGPSUpdate(){
		this.lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
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
				Log.d("GPS", "Latitude " +location.getLatitude() + " Longitude " +location.getLongitude());

				latitude = location.getLatitude();
				longitude = location.getLongitude();
				altitude = location.getAltitude();
				precision = location.getAccuracy();
				for(GPSListener l : listener){
					l.actionPerformed(new GPSEvent(location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy()));
				}
			}
		});
	}
	
	public void start(){
//		isEnabled(lm){
//			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//			startActivity(intent);
//		}
//		
		setCriteria();
		startGPSUpdate();
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public double getPrecision() {
		return precision;
	}
	
	public void addGPSListener(GPSListener l){
		listener.add(l);
	}


}