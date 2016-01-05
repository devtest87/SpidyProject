package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import android.text.format.DateFormat;


public class Utils {
	public static String getTimeRemaining(String date) 
	{ 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		long milis1 = 0;
		try {
			milis1 = dateFormat.parse(date).getTime();
		} 
catch (ParseException e) 
{
			e.printStackTrace();
		}
	    // Get the represented date in milliseconds 
	    long milis2 = System.currentTimeMillis();
	 
	    // Calculate difference in milliseconds 
	    long diff = Math.abs(milis2 - milis1);
	    int day = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);//(int)(diff / (24 * 60 * 60 * 1000));
	    if(day == 0){
	    	int hour = (int) TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);//(int)(diff / (24 * 60 * 60 * 1000));
	    	if(hour == 0){
		    	int minute = (int) TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);//(int)(diff / (24 * 60 * 60 * 1000));
		    	return minute > 1 ? minute + " minutes ago" : minute + " minute ago";
		    }else{
		    	return hour > 1 ? day + " hours ago" : day + " hour ago";
		    }
	    }else{
	    	return day > 1 ? day + " days ago" : day + " day ago";
	    }
	} 
	
	public static String formatDate(String date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat df1 = new SimpleDateFormat("dd MMM yyyy");
		String formattedDate = null;
        long startTime;
		try {
			startTime = df.parse(date).getTime();
			formattedDate = df1.format(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return formattedDate;
	}
}
