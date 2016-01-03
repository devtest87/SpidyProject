package com.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceHelper {
	private static Context mContext;
	private static PreferenceHelper sPreferenceHelper;
	
	public static PreferenceHelper getSingleInstance(Context context){
		if(sPreferenceHelper == null){
			mContext = context;
			sPreferenceHelper = new PreferenceHelper();
		}
		return sPreferenceHelper;
	}
	
	private PreferenceHelper() {
		
	}

	public static enum PreferenceKey {

		IS_LOGIN("false"),
		
		RWAS_ID(null),
		
		RWAS_NAME(null),
		
		EMAIL(null),
		
		USER_ID(null),
		
		MOBILE(null),

		NAME(null),

		PHOTO(null);

		String mDefaultValue;
		PreferenceKey(String defaultValue) {
			mDefaultValue = defaultValue;
		}
	}

	private static final PreferenceKey[] PREFERENCE_KEYS = PreferenceKey.values();

	protected class PreferenceData {
		private String mKey;
		private String mValue;
		public PreferenceData(String key, String value) {
			mKey = key;
			mValue = value;
		}
		public void set(String value) {
			mValue = value;
			persist(mKey, mValue);
		}
		public String get() {
			return mValue;
		}
	}

	private PreferenceData[] mPreferenceData = new PreferenceData[PREFERENCE_KEYS.length];

	public SharedPreferences mSharedPreferences;

	public boolean has(PreferenceKey key) {
		return mSharedPreferences.contains(key.name());
	}

	public String getString(PreferenceKey key) {
		return mPreferenceData[key.ordinal()].get();
	}

	public void setString(PreferenceKey key, String value) {
		mPreferenceData[key.ordinal()].set(value);
	}

	public boolean getBoolean(PreferenceKey key) {
		return Boolean.parseBoolean(mPreferenceData[key.ordinal()].get());
	}

	public void setBoolean(PreferenceKey key, boolean value) {
		mPreferenceData[key.ordinal()].set(String.valueOf(value));
	}

	public long getLong(PreferenceKey key) {
		return Long.parseLong(mPreferenceData[key.ordinal()].get());
	}

	public void setLong(PreferenceKey key, long value) {
		mPreferenceData[key.ordinal()].set(String.valueOf(value));
	}

	private void persist(final String key, final String value) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Editor editor = mSharedPreferences.edit();
				editor.putString(key, value);
				editor.commit();
			}
		}, "preference-thread").start();
	}

	public void init() {
		mSharedPreferences = mContext.getSharedPreferences("spidycity_preferences", Context.MODE_PRIVATE);
		for (PreferenceKey key : PREFERENCE_KEYS) {
			mPreferenceData[key.ordinal()] = new PreferenceData(key.name(), mSharedPreferences.getString(key.name(), key.mDefaultValue));
		}
	}

}
