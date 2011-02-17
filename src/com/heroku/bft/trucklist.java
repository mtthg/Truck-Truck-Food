package com.heroku.bft;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class trucklist extends Activity {
	public static final String[] Trucks = new String[] {
	    "Clover Food Truck", "Bon Me", "MomoGoose", "World Eats"
	};
	public static final String[] Descrip = new String[] {
	    "The food you love.", 
	    "Feel good about the food you eat.",
	    "Fresh and served hot daily!", 
	    "Saving the planet, 1 gut at a time! "
	};

	public class TruckAdapter extends ArrayAdapter<String> {
		public TruckAdapter(Context a, int b) {
			super(a, b, Trucks);
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.list_item, parent, false);
			TextView band = (TextView) row.findViewById(R.id.band);
			band.setText(Descrip[position]);
			TextView track = (TextView) row.findViewById(R.id.track);
			track.setText(Trucks[position]);
			return row;
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView lv = (ListView)findViewById(R.id.ListView01);
		lv.setAdapter(new TruckAdapter(this, android.R.layout.simple_list_item_1));
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					Intent intent = new Intent(getApplicationContext(),GoogleMaps.class);
		            startActivityForResult(intent, 0);
		            return;
				// When clicked, show a toast with the TextView text
//				Toast.makeText(getApplicationContext(), ((TextView) view.findViewById(R.id.band)).getText(),
//						Toast.LENGTH_SHORT).show();
			}
		});
	}
}