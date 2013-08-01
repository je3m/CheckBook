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
        
        final Button newEntryBTN = (Button) findViewById(R.id.ENTRYBTN),
        	historyBTN = (Button) findViewById(R.id.HISTORYBTN);
        final Intent dataEntry = new Intent(getApplicationContext(), DataEntry.class);   
        final Intent history = new Intent(getApplicationContext(), History.class);
     /*
	  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  * Methods
	  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  */
        
        OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (v.getId()){
				
					case R.id.ENTRYBTN:
						startActivity(dataEntry);
						break;
						
					case R.id.HISTORYBTN:
						startActivity(history);
						break;
				}
				
				
			}
		};
			newEntryBTN.setOnClickListener(listener);
			historyBTN.setOnClickListener(listener);
			
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
