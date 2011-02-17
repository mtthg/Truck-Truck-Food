package com.heroku.bft;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class GoogleMaps extends MapActivity  {
    
	MapView mapView;
    private MapController mc;
    List<Overlay> mapOverlays;
    Drawable drawable;
    ItemizedOverlay itemizedOverlay;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mapView = (MapView) findViewById(R.id.mapview1);
        mapView.setBuiltInZoomControls(true);
        mc = mapView.getController();
        
        Button getDirections = (Button) findViewById(R.id.getdirections); 
        getDirections.setOnClickListener(directionsScreen);
        
        String coordinates[] = {"42.359844", "-71.059050"};
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
        
        GeoPoint p = new GeoPoint( //represents a geographical location
            (int) (lat * 1E6), 
            (int) (lng * 1E6));
        
        mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.babyfoodtruck);
        itemizedOverlay = new ItemizedOverlay(drawable);
        
        OverlayItem overlayitem = new OverlayItem(p, "", "");
        
        mc.animateTo(p);
        mc.setZoom(19); 
        mapView.setSatellite(true);
        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
        mapView.invalidate();  
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