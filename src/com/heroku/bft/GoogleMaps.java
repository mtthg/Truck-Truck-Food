package com.heroku.bft;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GoogleMaps extends MapActivity  {
    
	private MapView mapView;
    private MapController mc;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mapView = (MapView) findViewById(R.id.mapview1);
        mc = mapView.getController();
        Button getDirections = (Button) findViewById(R.id.getdirections); 
        getDirections.setOnClickListener(directionsScreen);
        
    	

        String coordinates[] = {"40.747778", "-73.985556"};
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
        
        //use loc.getLongitude() and loc.getLatitude() in place of lat and lng
     
        GeoPoint p = new GeoPoint( //represents a geographical location
            (int) (lat * 1E6), 
            (int) (lng * 1E6));

        mc.animateTo(p); //move map to specific location
        mc.setZoom(17); 
        mapView.setSatellite(true);
        mapView.invalidate();        //what does invalidate do?     
    }

	public View.OnClickListener directionsScreen=new View.OnClickListener() {
		public void onClick(View v) {
			
			Intent i = new Intent(GoogleMaps.this, Directions.class);
			startActivityForResult(i, 0);
			};
		};    
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}