package com.heroku.bft;

import android.app.Activity;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class Homescreen extends Activity {
	public LocationManager locMgr = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        locMgr = (LocationManager)getSystemService(LOCATION_SERVICE);
        locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,
        		10000, 10000.0f,
        		onLocationChange);
        
    }
    
    LocationListener onLocationChange=new LocationListener() {
    	public void onLocationChanged(Location location) {
    	if (state.weather!=null) {
    	new FetchForecastTask(state).execute(location);
    	}
    	else {
    		Log.w(getClass().getName(), "Unable to fetch forecast – no WeatherBinder");
    				}
    				}
    				public void onProviderDisabled(String provider) {
    				// required for interface, not used
    				}
    				public void onProviderEnabled(String provider) {
    				// required for interface, not used
    				}
    				public void onStatusChanged(String provider, int status,
    				Bundle extras) {
    				// required for interface, not used
    				}
    				};
    	}
