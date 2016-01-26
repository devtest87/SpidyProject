package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.adapter.CheckRequestAdapter;
import com.android.adapter.CheckRequestAdapter.StartActivity;
import com.android.cityspidey.R;
import com.bean.CheckRequestData;
import com.bean.CheckRequestItemsData;
import com.bean.DeleteServicesData;
import com.bean.RequestBean;
import com.bean.RequestServiceData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;

public class CheckRequestStatusActivity extends BaseActivity implements StartActivity{
	//private ExecutorService mExecutorService;
	private ListView mCheckRequestListView;
	private CheckRequestAdapter mCheckRequestAdapter;
	private List<CheckRequestItemsData> mCheckRequestItemsDataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkrequest_layout);
		//		mExecutorService = Executors.newFixedThreadPool(1);
		//		mExecutorService.execute(new loadRWAs());

		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_rwas_hint));
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.servicecolor);
		
		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO));
		}

		mCheckRequestListView = (ListView)findViewById(R.id.listview_checkrequest);
		mCheckRequestListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mCheckRequestAdapter.startActivity(arg2);
			}
		});

//		searchET.addTextChangedListener(new TextWatcher() {
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				System.out.println("Text ["+s+"]");
//				if(mCheckRequestAdapter!=null)
//					mCheckRequestAdapter.getFilter().filter(s.toString());                           
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//
//			}
//
//			@Override
//			public void afterTextChanged(Editable s) {
//			}
//		});

		loadCheckRequestStatus();
	}

	private void loadCheckRequestStatus() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.SERVICES_REQUEST_STATUS);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("user_id", PreferenceHelper.getSingleInstance(this.getApplicationContext()).getString(PreferenceKey.USER_ID));
		list.add(valuePair);
		request.setCallingClassObject(this);
		request.setNamevaluepair(list);
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

	public void response(CheckRequestData checkRequestData) {
		mCheckRequestItemsDataList = checkRequestData.getCheckRequestItemsDatasList();
		mCheckRequestAdapter = new CheckRequestAdapter(getLayoutInflater(),this, mCheckRequestItemsDataList , mAQuery);
		mCheckRequestListView.setAdapter(mCheckRequestAdapter);
	}
	
	public void response(DeleteServicesData deleteServicesData) {
		if(deleteServicesData.getError().equalsIgnoreCase("success")){
			mCheckRequestItemsDataList.remove(position);
			if(mCheckRequestItemsDataList.size() == 0){
				finish();
			}else{
				mCheckRequestAdapter.notifyDataSetChanged();
			}
		}
	}
	
	private int position = 0;

	@Override
	public void startActivity(String data) {
		String[] tag = data.split("@");
		String service_id = tag[0];
		position = Integer.parseInt(tag[1]);
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.SERVICES_DELETE);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("id", service_id);
		list.add(valuePair);
		request.setCallingClassObject(this);
		request.setNamevaluepair(list);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
	}
}
