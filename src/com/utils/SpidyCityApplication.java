package com.utils;

import android.app.Application;

public class SpidyCityApplication extends Application{
	@Override
	public void onCreate() {
		super.onCreate();
		PreferenceHelper preferenceHelper = PreferenceHelper.getSingleInstance(getApplicationContext());
		preferenceHelper.init();
	}
}
