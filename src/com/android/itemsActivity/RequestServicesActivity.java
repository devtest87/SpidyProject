package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.android.adapter.ServicesAdapter;
import com.android.spideycity.R;
import com.bean.RequestBean;
import com.bean.RequestServicesData;
import com.bean.ServicesData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;

public class RequestServicesActivity extends BaseActivity{
	//private ExecutorService mExecutorService;
	private GridView serviceGridView;
	private ServicesAdapter mServicesAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_layout);
		//		mExecutorService = Executors.newFixedThreadPool(1);
		//		mExecutorService.execute(new loadRWAs());
		serviceGridView = (GridView)findViewById(R.id.recyclerview_service);
		loadRWAs();
	}

	private void loadRWAs() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.REQUEST_SERVICES);
		request.setCallingClassObject(this);
		List<NameValuePair> namevaluepairList = new ArrayList<NameValuePair>();
		NameValuePair namevaluepair = new BasicNameValuePair("user_id", getIntent().getStringExtra("id"));
		namevaluepairList.add(namevaluepair);
		request.setNamevaluepair(namevaluepairList);
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

	public void response(RequestServicesData requestServicesData) {
	}

}
