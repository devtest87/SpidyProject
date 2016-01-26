package com.android.itemsActivity;

import android.app.Activity;
import android.os.Bundle;

import com.android.spideycity.R;
import com.androidquery.AQuery;

public class BaseActivity extends Activity{
	protected AQuery mAQuery;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAQuery = new AQuery(getApplicationContext());
	
		overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
