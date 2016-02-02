package com.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import android.graphics.Bitmap;


public class Utils {
	public static String getTimeRemaining(String date) 
	{ 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		long milis1 = 0;
		try {
		// git test change only
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
	    	day = (int) TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);//(int)(diff / (24 * 60 * 60 * 1000));
	    	if(day == 0){
		    	day = (int) TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);//(int)(diff / (24 * 60 * 60 * 1000));
		    	return day > 1 ? day + " minutes ago" : day + " minute ago";
		    }else{
		    	return day > 1 ? day + " hours ago" : day + " hour ago";
		    }
	    }else{
	    	if(day > 30){
	    		day = day/30;
	    		if(day > 12){
	    			day = day/12;
	    			return day > 1 ? day + " years ago" : day + " year ago";
	    		}else{
	    			return day > 1 ? day + " months ago" : day + " month ago";
	    		}
	    	}else{
	    		return day > 1 ? day + " days ago" : day + " day ago";
	    	}
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
	
	public static void saveImage(String imageUrl, Bitmap bitmap){
		
		FileOutputStream out = null;
		try { 
		    out = new FileOutputStream(imageUrl);
		    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
		    // PNG is a lossless format, the compression factor (100) is ignored 
		} catch (Exception e) {
		    e.printStackTrace();
		} finally { 
		    try { 
		        if (out != null) {
		            out.close();
		        } 
		    } catch (IOException e) {
		        e.printStackTrace();
		    } 
		} 
	}
	
}
