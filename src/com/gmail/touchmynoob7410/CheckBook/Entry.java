package com.gmail.touchmynoob7410.CheckBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.os.Environment;


/*
 * An entry object represents a single entry into the checkbook.
 * It is to be serialized and stored for further evaluation and computation
 * 
 * Jim Gildersleeve 
 * 7-19-2013
 */
public class Entry implements Serializable{
	
	private static final long serialVersionUID = -1128155909267684079L;
	
	private int	checkNumber;
	private float amount;
	private String recipient,
	memo;
	private int month,
	day, 
	year;
	private String[] date;
	boolean deposit;
	
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Getters and setters.
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	public boolean isDeposit() {
		return deposit;
	}
	public void setDeposit(boolean deposit) {
		this.deposit = deposit;
	}
	public String[] getDate() {
		return date;
	}
	public void setDate() {
		Integer dateMonth = (Integer) month;
		Integer dateDay = (Integer) day;
		Integer dateYear = (Integer) year;
		date = new String[] {(dateMonth.toString()), (dateDay.toString()), (dateYear.toString())};
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String Memo) {
		this.memo = Memo;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(int checkNumber) {
		this.checkNumber = checkNumber;
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Methods
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	
	
	
	public boolean save(){
		setDate();
		int i = 1;
		
			File saveFile = new File(Environment.getExternalStorageDirectory() + "/CheckBook/", "Entry" + i + ".ser");
			
		while (saveFile.exists()){
			File newFile = new File(Environment.getExternalStorageDirectory() + "/CheckBook/", "Entry" + i + ".ser");
			saveFile = newFile;
			i++;
			
		}

		if (!saveFile.exists()){
			
			
			try {	
				ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(saveFile));
				save.writeObject(this);
				save.close();
			} catch (FileNotFoundException e) {
				
				saveFile.getParentFile().mkdirs();
				
				this.save();
			} catch (IOException e) {
				
				e.printStackTrace();
				return false;
				 
			}	
		}	
		return true;
	} 
		
}

