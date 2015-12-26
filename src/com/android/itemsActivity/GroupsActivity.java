package com.android.itemsActivity;

import com.android.spideycity.R;
import com.bean.GroupsData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GroupsActivity extends BaseActivity{
	//private ExecutorService mExecutorService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_layout);
//		mExecutorService = Executors.newFixedThreadPool(1);
//		mExecutorService.execute(new loadRWAs());
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setTextColor(getResources().getColor(R.color.gray));
		titleTV.setBackgroundResource(R.color.groupcolor);
		
		
		loadGroups();
	}
	
	private void loadGroups() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.GROUPS);
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

	public void response(GroupsData groupsData) {
		
	}
	
}
