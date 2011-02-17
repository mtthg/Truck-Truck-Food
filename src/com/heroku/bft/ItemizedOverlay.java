package com.heroku.bft;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;

import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class ItemizedOverlay extends com.google.android.maps.ItemizedOverlay {
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	

	public ItemizedOverlay(Drawable babyfoodtruck) {
		super(boundCenterBottom(babyfoodtruck));
		// TODO Auto-generated constructor stub
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}

	@Override
	protected OverlayItem createItem(int z) {
	  return mOverlays.get(z);
	}

	@Override
	public int size() {
		return mOverlays.size();

	}

}