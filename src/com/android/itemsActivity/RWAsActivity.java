package com.android.itemsActivity;

import java.util.List;

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

import com.android.adapter.RWAsAdapter;
import com.android.adapter.RWAsAdapter.StartActivity;
import com.android.cityspidey.R;
import com.bean.RWAItemsData;
import com.bean.RWAsData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.AppConstant;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;

public class RWAsActivity extends BaseActivity implements StartActivity{
	//private ExecutorService mExecutorService;
	private ListView mRWAsListView;
	private RWAsAdapter mRWAssAdapter;
	private List<RWAItemsData> mRWAsDataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rwas_layout);
		//		mExecutorService = Executors.newFixedThreadPool(1);
		//		mExecutorService.execute(new loadRWAs());

		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.rwas));
		titleTV.setTextColor(getResources().getColor(R.color.black));
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_rwas_hint));
		titleTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setBackgroundResource(R.color.rwacolor);

		mRWAsListView = (ListView)findViewById(R.id.listview_rwa);
		
		mRWAsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mRWAssAdapter.startActivity(arg2);
			}
		});
		
		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO), true, true, 0, R.drawable.profile);
		}

		searchET.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				System.out.println("Text ["+s+"]");
				if(mRWAssAdapter!=null)
					mRWAssAdapter.getFilter().filter(s.toString());                           
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

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
		mRWAsDataList = rwAsData.getRwaItemsDatasList();
		mRWAssAdapter = new RWAsAdapter(getLayoutInflater(),this, mRWAsDataList , mAQuery);
		mRWAsListView.setAdapter(mRWAssAdapter);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			setResult(RESULT_OK);
			finish();
		}
	}

	@Override
	public void startActivity(String url) {
		String[] urls = url.split("@");
		Intent intent = new Intent(RWAsActivity.this, RWAsDetailActivity.class);
		intent.putExtra("url", urls[0]);
		intent.putExtra("furl", urls[1]);
		startActivityForResult(intent, AppConstant.REQUEST_RWA_ACTIVITY_CODE);

	}
}
