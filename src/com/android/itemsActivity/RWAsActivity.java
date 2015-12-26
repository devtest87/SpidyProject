package com.android.itemsActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.adapter.RWAsAdapter;
import com.android.spideycity.R;
import com.bean.RWAsData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;

public class RWAsActivity extends BaseActivity{
	//private ExecutorService mExecutorService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rwas_layout);
//		mExecutorService = Executors.newFixedThreadPool(1);
//		mExecutorService.execute(new loadRWAs());
		
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_rwas_hint));
		titleTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setBackgroundResource(R.color.rwacolor);
		
		loadRWAs();
	}
	
	private void loadRWAs() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.RWAS);
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

	public void response(RWAsData rwAsData) {
		RWAsAdapter rwAsAdapter = new RWAsAdapter(getLayoutInflater(), rwAsData.getRwaItemsDatasList(), mAQuery);
		ListView listView = (ListView)findViewById(R.id.listview_rwa);
		listView.setAdapter(rwAsAdapter);
	}
}
