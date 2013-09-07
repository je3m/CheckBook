package com.gmail.touchmynoob7410.CheckBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

public class History extends Activity{
	private File savedFile;
	private ArrayList<Entry> list;
	private ArrayList<Float> moneyList;
	private AlertDialog.Builder alert;
	ProgressDialog progress;
	private TextView moneyOBJ;
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
		moneyOBJ = (TextView) findViewById(R.id.totalMoney);
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
				savedFile = new File(Environment.getExternalStorageDirectory() + "/CheckBook/", "Entry" + count + ".ser");		
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
		
		
		
		
		//reading data
		boolean color = true;
		for (Entry entry: list){
			printEntry(entry, color);
			
			//populating moneyList arrayList
			
			
			if (color){
				color = false;
			} else{
				color = true;
			}
		}
		
		//add all amounts together
		
		
		super.onCreate(savedInstanceState);
	}
	
	//used to generate the GUI
	@SuppressLint("NewApi")
	private void printEntry(Entry e, boolean c){
		//table object to be used 
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout);
		TableRow row = new TableRow(this);
		
		//Views to be printed
		TextView recepient = new TextView(this);
		TextView date = new TextView(this);
		TextView amount = new TextView(this);
		
		//Information to be printed
		recepient.setText(e.getRecipient().toString());
		String[] dateString = e.getDate();
		date.setText(" " + dateString[0] + "/" + dateString[1] + "/" + dateString[2] + " ");
		if (e.isDeposit()){
			amount.setText("-" + e.getAmount());
		} else{
			amount.setText("+" + e.getAmount());
		}
		
		//formatting text
		
		recepient.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		amount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		
		recepient.setPadding(10, 10, 10, 10);
		date.setPadding(10, 10, 10, 10);
		amount.setPadding(10, 10, 10, 10);
		
		recepient.setTextColor(Color.BLUE);
		if (e.isDeposit()){
			amount.setTextColor(Color.RED);
		} else {
			amount.setTextColor(Color.GREEN);
		}
		
				
		//add stuff to individual row
		row.addView(recepient);
		row.addView(date);
		row.addView(amount);
		
		table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		//row formatting
		if (c){
			row.setBackgroundColor(Color.GRAY);
		}
		row.setGravity(Gravity.CENTER_HORIZONTAL);
		
	}
	
	public Entry load(int i){

			savedFile = new File(Environment.getExternalStorageDirectory() + "/CheckBook/", "Entry" + i + ".ser");
			Entry importObject;
			try {
				ObjectInputStream stream = new ObjectInputStream(new FileInputStream(savedFile));
				importObject = (Entry) stream.readObject();
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
				return null;				
			}
			return importObject;
					
	}
	
	

}
