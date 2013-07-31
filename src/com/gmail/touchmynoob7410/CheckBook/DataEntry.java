package com.gmail.touchmynoob7410.CheckBook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

public class DataEntry extends Activity {
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Variable declarations
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	
	Button saveOBJ;
	RadioButton expenseOBJ,
		depositOBJ;
	EditText recipientOBJ,
		amountOBJ,
		memoOBJ,
		check_numberOBJ;
	DatePicker datePickOBJ;
	CheckBox checkOBJ;
	
	Entry newSave = new Entry();
	
	DialogInterface.OnClickListener alertListener;
	boolean correctForm;
	double output;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_entry);
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Variable initializations
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
		
		//Object references
		saveOBJ = (Button) findViewById(R.id.SAVEBTN);
		expenseOBJ = (RadioButton) findViewById(R.id.EXPENSE);
		depositOBJ = (RadioButton) findViewById(R.id.DEPOSIT);
		recipientOBJ = (EditText) findViewById(R.id.RECIPIENT);
		amountOBJ = (EditText) findViewById(R.id.AMOUNT);
		memoOBJ = (EditText) findViewById(R.id.MEMO);
		check_numberOBJ = (EditText) findViewById(R.id.CHECK_NUMBER);
		datePickOBJ = (DatePicker) findViewById(R.id.datePicker1);
		checkOBJ = (CheckBox) findViewById(R.id.checkBox);
				
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Listeners
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
		
		/*
		 * Listener for alert boxes
		 */
		alertListener = new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				switch (which){
				/*
				 * This closes the current activity in order to start a new MainActivity
				 */
				case -1: //positiveButton
					final Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);  
					startActivity(MainActivity);
					finish();
					break;
					
				case -3: //neutral button
					onCreate(null);
				}
				
			}
		};
		
		/*
		 * Listener to format the amountOBJ to a float with two decimal places
		 */
		
		amountOBJ.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				
			
				try {
					double amount = Double.parseDouble(amountOBJ.getText().toString());
					amountOBJ.setText(String.format("%,.2f", amount));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}
		});
		
		/*
		 * Listener to enable/disable check_number
		 */
		check_numberOBJ.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
					
				if (checkOBJ.isChecked()){
					check_numberOBJ.setEnabled(true);
				} else {
					check_numberOBJ.setEnabled(false);
				}
						
				return false;
			}
		});
		/*
		 * Listener to enable/disable the recipientOBJ
		 */
		expenseOBJ.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					recipientOBJ.setEnabled(true);
				} else {
					recipientOBJ.setEnabled(false);
				}			
			}
		});
		
		/*
		 * Listener for the save button
		 */
		saveOBJ.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.SAVEBTN:	
						
					//Shoves all of the information into a new entry object
					if (setUpSave() 
							&& newSave.save()){
						saveAlert();
					}
												
					break;
						
				default:   
					showAlert("Tell Jim wrong button", "Error","Okay", null, null);
					break;
				}
				
			}
		});
		
		
		
	
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Methods
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
		
		
		super.onCreate(savedInstanceState);
	}
	
	/*
	 * Sets up the save object
	 * called within the saveBTN OnClickListener
	 */
	public boolean setUpSave(){		
		final boolean deposit = expenseOBJ.isChecked(); 
		newSave.setDeposit(deposit);
		
		if (expenseOBJ.isChecked()){
			final String recipient = recipientOBJ.toString();
			newSave.setRecipient(recipient);
		}
		
		final String memo = memoOBJ.toString();
		newSave.setMemo(memo);
		
		
	try {
	
		String amountFloat = String.format("%.f", amountOBJ.getText().toString());
		final float amount = Float.parseFloat(amountFloat);
		newSave.setAmount(amount);
	} catch (NumberFormatException e) {
		showAlert("There is no amount entered", "Error", "Okay", null, null);
		e.printStackTrace();
		return false;
	}
		
		if (checkOBJ.isChecked()){
			final int check_number = Integer.parseInt(check_numberOBJ.toString());
			newSave.setCheckNumber(check_number);
		}
								
		final int day = datePickOBJ.getDayOfMonth();
		newSave.setDay(day);
		
		final int month = datePickOBJ.getMonth();
		newSave.setMonth(month);
		
		final int year = datePickOBJ.getYear();
		newSave.setYear(year);
		return true;
	}
	
	/*
	 * AlertDialog used for primarily for debugging
	 */
	public void showAlert(String message, String title, String posBTNTxt, String neutralBTNTXT, DialogInterface.OnClickListener listener){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		alert.setMessage(message)
		.setCancelable(false)
		.setTitle("Error")
		.setNeutralButton(neutralBTNTXT, listener)
		.setPositiveButton(posBTNTxt, listener)
		.show();
	}
	
	/*
	 * alertDialog to show on successful save
	 */
	public void saveAlert(){
		AlertDialog.Builder success = new AlertDialog.Builder(this);
		
		success.setMessage("The files have been successfully saved!")
		.setCancelable(false)
		.setTitle("Operation Sucessful")
		.setPositiveButton("Return to home", alertListener)
		.setNeutralButton("New entry", alertListener)
		.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
}
