package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.adapter.DirectoryAdapter;
import com.android.adapter.DirectoryAdapter.StartActivity;
import com.android.spideycity.R;
import com.bean.DirectoryData;
import com.bean.DiretoryItemsData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;

public class DirectoryActivity extends BaseActivity implements StartActivity{
	//private ExecutorService mExecutorService;
	private DirectoryAdapter mDirectoryAdapter;
	private List<DiretoryItemsData> mDiretoryItemsDataList;
	private ListView mDirectoryListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_directory_layout);
//		mExecutorService.execute(new loadRWAs());
		
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.directory));
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_rwas_hint));
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.directorycolor);
		
		mDirectoryListView = (ListView)findViewById(R.id.listview_directory);
		mDirectoryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mDirectoryAdapter.startActivity(arg2);
			}
		});
		
		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO));
		}

		searchET.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				System.out.println("Text ["+s+"]");
				if(mDirectoryAdapter!=null)
					mDirectoryAdapter.getFilter().filter(s.toString());                           
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		
		loadDirectory();
	}
	
	private void loadDirectory() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.DIRECTORY);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("rwa_id", "3");//PreferenceHelper.getSingleInstance(this.getApplicationContext()).getString(PreferenceKey.RWAS_ID));
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

	public void response(DirectoryData directoryData) {
		mDirectoryAdapter = new DirectoryAdapter(getLayoutInflater(), this, directoryData.getDiretoryItemsDatasList(), mAQuery);
		mDirectoryListView.setAdapter(mDirectoryAdapter);
	}

	@Override
	public void startActivity(String serviceId) {
		
	}
	
}
