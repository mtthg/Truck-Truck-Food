package com.heroku.bft;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class gps extends Activity {
	
	private DefaultHttpClient client=null;
	private String latitude=null;
	private String longitude=null;
	
	//have to get GPS coordinates first
	private void sendcoordinates() {
		Thread t = new Thread(){
			public void run() {
				Looper.prepare(); //For Preparing Message Pool for the child Thread
				try {
					StringBuilder url = new StringBuilder("http://localhost:3000/trucks/locate.json?");
					url.append("lat=").append("latitude").append("&lon=").append("longitude").append("&dist=").append("distance");
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost postMethod = new HttpPost(url.toString());
					
				//get latitude and longitude from gps and package 
										
					HttpResponse httpResponse = client.execute(postMethod);
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