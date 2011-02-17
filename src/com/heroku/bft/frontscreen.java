package com.heroku.bft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class frontscreen extends Activity {
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.frontscreen);

        final Button button = (Button) findViewById(R.id.find_truck);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	 
            	Intent intent = new Intent(frontscreen.this,trucklist.class);
                 startActivityForResult(intent, 0);
                 return;
             
                // Perform action on click
            }
        });
    }
}