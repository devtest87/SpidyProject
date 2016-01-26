package com.android.itemsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.android.adapter.GridOpinionsPollsAdapter;
import com.android.adapter.GridOpinionsPollsAdapter.StartActivity;
import com.android.spideycity.R;
import com.bean.OpinionPollsData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;

public class OpinionPollActivity extends BaseActivity implements StartActivity{
	//private ExecutorService mExecutorService;

	private GridView mGridView;
	private GridOpinionsPollsAdapter mGridOpinionsPollsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opinionpolls_layout);
		//		mExecutorService = Executors.newFixedThreadPool(1);
		//		mExecutorService.execute(new loadRWAs());

		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.openion_polls));
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_opinionpolls_hint));
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.opinioncolor);
		
		final TextView trendingTV = (TextView)findViewById(R.id.tv_trending);
		final TextView latestTV = (TextView)findViewById(R.id.tv_latest);
		final TextView popularTV = (TextView)findViewById(R.id.tv_popular);
		
		trendingTV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				trendingTV.setTypeface(Typeface.DEFAULT_BOLD);
				latestTV.setTypeface(Typeface.DEFAULT);
				popularTV.setTypeface(Typeface.DEFAULT);
				trendingTV.setTextColor(getResources().getColor(R.color.white));
				latestTV.setTextColor(getResources().getColor(R.color.white));
				popularTV.setTextColor(getResources().getColor(R.color.white));
			}
		});
		latestTV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				trendingTV.setTypeface(Typeface.DEFAULT);
				latestTV.setTypeface(Typeface.DEFAULT_BOLD);
				popularTV.setTypeface(Typeface.DEFAULT);
				trendingTV.setTextColor(getResources().getColor(R.color.white));
				latestTV.setTextColor(getResources().getColor(R.color.white));
				popularTV.setTextColor(getResources().getColor(R.color.white));
			}
		});
		popularTV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				trendingTV.setTypeface(Typeface.DEFAULT);
				latestTV.setTypeface(Typeface.DEFAULT);
				popularTV.setTypeface(Typeface.DEFAULT_BOLD);
				trendingTV.setTextColor(getResources().getColor(R.color.white));
				latestTV.setTextColor(getResources().getColor(R.color.white));
				popularTV.setTextColor(getResources().getColor(R.color.white));
			}
		});

		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO));
		}

		mGridView = (GridView)findViewById(R.id.recyclerview_service);

		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mGridOpinionsPollsAdapter.startActivity(arg2);
			}
		});
		searchET.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				System.out.println("Text ["+s+"]");
				if(mGridOpinionsPollsAdapter!=null)
					mGridOpinionsPollsAdapter.getFilter().filter(s.toString());                           
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
		request.setNetworkRequestName(NetworkRequestName.OPINIONPOLLS);
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

	public void response(OpinionPollsData opinionPollsData) {
		mGridOpinionsPollsAdapter = new GridOpinionsPollsAdapter(this, getLayoutInflater(), this, opinionPollsData.getOpinionPollsItemsDataList(), mAQuery);
		mGridView.setAdapter(mGridOpinionsPollsAdapter);
	}

	@Override
	public void startActivity(String json) {
		JSONObject object;
		try {
			object = new JSONObject(json);
			Intent intent = new Intent(OpinionPollActivity.this, OpinionPollDetailActivity.class);
			intent.putExtra("url", object.getString("url"));
			intent.putExtra("postdate", object.getString("postdate"));
			intent.putExtra("enddate", object.getString("enddate"));
			intent.putExtra("image", object.getString("image"));
			startActivity(intent);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
