package com.gmail.touchmynoob7410.CheckBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class History extends Activity{
	private File savedFile;
	private ArrayList<Entry> list;
	private AlertDialog.Builder alert;
	ProgressDialog progress;
	int count = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.transaction_history);
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 * Instance variables 
		 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		
		/*
		 * Object references
		 */
		alert = new AlertDialog.Builder(this);
		
		savedFile = new File(Environment.getExternalStorageDirectory() + "/CheckBook/", "Entry" + count + ".ser");
		list = new ArrayList<Entry>();
		progress = new ProgressDialog(this);
		
		
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 * End of variables
		 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
				
		if (savedFile.exists()){
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.setTitle("Loading entries");
			progress.setCancelable(false);
			progress.show();
			while(savedFile.exists()){
				list.add(load(count));
				count++;
			}
			progress.dismiss();
			alert.setTitle("Success!")
			.setMessage("Files loaded sucessfully!")
			.setNeutralButton("Okay", null)
			.show();
		} else {
			alert.setTitle("Error")
			.setMessage("No files found")
			.setNeutralButton("Okay", null)
			.show();
		}
		
		
		super.onCreate(savedInstanceState);
	}
	
	public Entry load(int i){

			savedFile = new File(Environment.getExternalStorageDirectory() + "/CheckBook/", "Entry" + i + ".ser");
			Entry importObject;
			try {
				ObjectInputStream stream = new ObjectInputStream(new FileInputStream(savedFile));
				importObject = (Entry) stream.readObject();
				stream.close();
			} catch (Exception e) {
				
				return null;				
			}
			return importObject;
					
	}
	
	

}
