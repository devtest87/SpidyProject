package com.android.itemsActivity;

import com.android.spideycity.R;
import com.bean.RequestBean;
import com.bean.spidyPickData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SpidyPickActivity extends BaseActivity{
	//private ExecutorService mExecutorService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_layout);
//		mExecutorService = Executors.newFixedThreadPool(1);
//		mExecutorService.execute(new loadRWAs());
		
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_rwas_hint));
		titleTV.setText(getResources().getString(R.string.spidey_pick));
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.spideycolor);
		
		loadRWAs();
	}
	
	private void loadRWAs() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.SPIDYPICKS);
		request.setCallingClassObject(this);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
	}

//	class loadRWAs implements Runnable {
//		public void run() { 
//			new NetworkCall()
//		} 
//	};
	

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
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	public void response(spidyPickData spidyPickData) {
		
	}
	
}
