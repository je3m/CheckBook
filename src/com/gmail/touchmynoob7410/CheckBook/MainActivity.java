package com.gmail.touchmynoob7410.CheckBook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Instance Variables
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
        
        final Button newEntry = (Button) findViewById(R.id.ENTRYBTN);
        final Button entryHistory = (Button) findViewById(R.id.ENTRYHISTORYBTN);
        final Intent dataEntry = new Intent(getApplicationContext(), DataEntry.class);       
     /*
	  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  * Methods
	  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  */
        newEntry.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (v.getId()){
				
					case R.id.ENTRYBTN:
						
						startActivity(dataEntry);
						finish();
						
						break;
						
					case R.id.ENTRYHISTORYBTN:
						//need to make new activity
						break;
				}
				
				
			}
		});
				
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
