package com.utils;

import android.util.Log;


public class PrintLog {
	private static  boolean isLogEnabled = true;

    public static void show(int loginfo, String tag, String message){
        if(isLogEnabled) {
            switch (loginfo) {
                case Log.INFO:
                    Log.i(tag, message);
                    break;
                case Log.DEBUG:
                    Log.d(tag, message);
                    break;
                case Log.WARN:
                    Log.w(tag, message);
                    break;
                case Log.ERROR:
                    Log.e(tag, message);
                    break;
                case Log.VERBOSE:
                    Log.v(tag, message);
                    break;
                default:
                    Log.i(tag, message);
                    break;
            }
        }

    }
}
