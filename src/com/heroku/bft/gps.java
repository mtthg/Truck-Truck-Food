package com.heroku.bft;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class gps extends Activity {
	
	private DefaultHttpClient client=null;
	private Object lat=null;
	private Object lon=null;
	
	//have to get GPS coordinates first
	private LocationManager lm;
    private LocationListener locationListener;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        
        //---use the LocationManager class to obtain GPS locations---
        lm = (LocationManager) 
            getSystemService(Context.LOCATION_SERVICE);    
        
        locationListener = new MyLocationListener();
        
        lm.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 
            0, 
            0, 
            locationListener);        
    }
    
    private class MyLocationListener implements LocationListener 
    {
        @Override
        public void onLocationChanged(Location loc) {
            if (loc != null) {
                Toast.makeText(getBaseContext(), 
                    "Location changed : lat: " + loc.getLatitude() + 
                    " lon: " + loc.getLongitude(), 
                    Toast.LENGTH_SHORT).show();
            }
        lat = loc.getLatitude();
        lon = loc.getLongitude();
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider, int status, 
            Bundle extras) {
            // TODO Auto-generated method stub
        }
    }        

	private void sendcoordinates() {
		Thread t = new Thread(){
			public void run() {
				Looper.prepare(); //For Preparing Message Pool for the child Thread
				try {
					StringBuilder url = new StringBuilder("http://trucktruckfood.heroku.com/trucks/locate.json?");
					url.append("lat=").append(lat).append("&lon=").append(lon).append("&dist=").append("distance");
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(url.toString());
					
				//get latitude and longitude from gps and package 
										
					HttpResponse httpResponse = client.execute(httppost);
					Log.d("LocationResponse",httpResponse.toString());
					if(httpResponse!=null){
						InputStream in = httpResponse.getEntity().getContent(); //Get the data in the entity
					}

					//return httpResponse;		
				}

				catch (Throwable t) {
					Log.e("location", "Exception in updateStatus()", t);
					errorHandler(t);

					//return null;
				}
				Looper.loop(); //Loop in the message queue
			}

			private void errorHandler(Throwable t) {
				// TODO Auto-generated method stub
				
			}
		};
		t.start();      
	}
	/*public void getStuff(View v) {
		HttpGet get = new HttpGet("http://twory.heroku.com/random.json");
		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		try {
			String responseBody = client.execute(get, responseHandler);
			JSONObject jObject = new JSONObject(responseBody);
			JSONObject jObjLine = jObject.getJSONObject("last_line").getJSONObject("line");
			String message = jObjLine.getString("text");
			story_id = jObjLine.getString("story_id");

			JSONObject jObjStory = jObject.getJSONObject("current_story").getJSONObject("story");
			String story_title = jObjStory.getString("title");

		}

		catch (Throwable t) {
			Log.e("MashUp", "Exception in getStuff()", t);
			errorHandler(t);
		}
	}

	private void errorHandler(Throwable t) {
		Toast.makeText(getApplicationContext(), "NO INTERNET!", Toast.LENGTH_SHORT).show();
		//		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		//		builder
		//		.setTitle("Exception!")
		//		.setMessage(t.toString())
		//		.setPositiveButton("OK", null)
		//		.show();
	}*/
}