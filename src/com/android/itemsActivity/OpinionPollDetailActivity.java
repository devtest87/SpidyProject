package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.cityspidey.R;
import com.bean.OpinionPollsDetailsData;
import com.bean.OpinionPostAnswerPollsDetailsData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;
import com.utils.Utils;

public class OpinionPollDetailActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opinion_detail_layout);
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.openion_polls));
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.opinioncolor);
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_opinionpolls_hint));

		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO), true, true, 0, R.drawable.profile);
		}

		loadOpinionDetail();
	}

	private void loadOpinionDetail() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.OPINIONPOLLS_DETAILS);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("url", getIntent().getStringExtra("url"));
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

	public void response(final OpinionPollsDetailsData opinionPollsDetailsData) {
		if(opinionPollsDetailsData.getOpinionPollsDetailItemsDataList().size() != 0){
			if(opinionPollsDetailsData.getOpinionPollsDetailItemsDataList().size() != 0){
				ImageView opinionPollIV = (ImageView)findViewById(R.id.iv_opinion);
				TextView titleTV = (TextView)findViewById(R.id.tv_opiniontitle);
				TextView postandendDateTV = (TextView)findViewById(R.id.tv_postandenddate);
				LinearLayout voteOptionLL = (LinearLayout)findViewById(R.id.ll_vote_options);

				mAQuery.id(opinionPollIV).image(getIntent().getStringExtra("image"));
				titleTV.setText(opinionPollsDetailsData.getTitle());
				postandendDateTV.setText("Posted " + Utils.getTimeRemaining(getIntent().getStringExtra("postdate"))+
						"/Voting ends\n " + Utils.formatDate(getIntent().getStringExtra("enddate")));
				int size = opinionPollsDetailsData.getOpinionPollsDetailItemsDataList().size();
				int rowCount  = size / 3;
				int reminder = size % 3;
				for (int i = 0; i < rowCount + 1; i++) {
					View view = getLayoutInflater().inflate(R.layout.inflate_vote_option, null, false);
					TextView vote1 = (TextView)view.findViewById(R.id.tv_vote1);
					TextView vote2 = (TextView)view.findViewById(R.id.tv_vote2);
					TextView vote3 = (TextView)view.findViewById(R.id.tv_vote3);
					vote1.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							int pos = Integer.parseInt(v.getTag().toString());
							List<NameValuePair> list = new ArrayList<NameValuePair>();
							NameValuePair nameValuePairs = new BasicNameValuePair("user_id", PreferenceHelper.
									getSingleInstance(getApplicationContext()).getString(PreferenceKey.USER_ID));
							list.add(nameValuePairs);
							nameValuePairs = new BasicNameValuePair("poll_id", 
									opinionPollsDetailsData.getId());
							list.add(nameValuePairs);
							nameValuePairs = new BasicNameValuePair("ans", opinionPollsDetailsData.
									getOpinionPollsDetailItemsDataList().get(pos).getId());
							list.add(nameValuePairs);
							voteNow(list);
						}
					});
					vote2.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							int pos = Integer.parseInt(v.getTag().toString());
							List<NameValuePair> list = new ArrayList<NameValuePair>();
							NameValuePair nameValuePairs = new BasicNameValuePair("user_id", PreferenceHelper.
									getSingleInstance(getApplicationContext()).getString(PreferenceKey.USER_ID));
							list.add(nameValuePairs);
							nameValuePairs = new BasicNameValuePair("poll_id", 
									opinionPollsDetailsData.getId());
							list.add(nameValuePairs);
							nameValuePairs = new BasicNameValuePair("ans", opinionPollsDetailsData.
									getOpinionPollsDetailItemsDataList().get(pos).getId());
							list.add(nameValuePairs);
							voteNow(list);
						}
					});
					vote3.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							int pos = Integer.parseInt(v.getTag().toString());
							List<NameValuePair> list = new ArrayList<NameValuePair>();
							NameValuePair nameValuePairs = new BasicNameValuePair("user_id", PreferenceHelper.
									getSingleInstance(getApplicationContext()).getString(PreferenceKey.USER_ID));
							list.add(nameValuePairs);
							nameValuePairs = new BasicNameValuePair("poll_id", 
									opinionPollsDetailsData.getId());
							list.add(nameValuePairs);
							nameValuePairs = new BasicNameValuePair("ans", opinionPollsDetailsData.
									getOpinionPollsDetailItemsDataList().get(pos).getId());
							list.add(nameValuePairs);
							voteNow(list);
						}
					});
					if(i != rowCount){
						vote1.setText(opinionPollsDetailsData.getOpinionPollsDetailItemsDataList().get((i*3)+0).getOptions());
						vote2.setText(opinionPollsDetailsData.getOpinionPollsDetailItemsDataList().get((i*3)+1).getOptions());
						vote3.setText(opinionPollsDetailsData.getOpinionPollsDetailItemsDataList().get((i*3)+2).getOptions());
						vote1.setTag((i*3)+0);
						vote2.setTag((i*3)+1);
						vote3.setTag((i*3)+2);
					}else{
						if(reminder == 1){
							vote1.setText(opinionPollsDetailsData.getOpinionPollsDetailItemsDataList().get((i*3)+0).getOptions());
							vote1.setTag((i*3)+0);
							vote2.setVisibility(View.INVISIBLE);
							vote3.setVisibility(View.INVISIBLE);
						}else if(reminder == 2){
							vote1.setText(opinionPollsDetailsData.getOpinionPollsDetailItemsDataList().get((i*3)+0).getOptions());
							vote2.setText(opinionPollsDetailsData.getOpinionPollsDetailItemsDataList().get((i*3)+1).getOptions());
							vote1.setTag((i*3)+0);
							vote2.setTag((i*3)+1);
							vote3.setVisibility(View.INVISIBLE);
						}else if(reminder == 0){
							vote1.setVisibility(View.GONE);
							vote2.setVisibility(View.GONE);
							vote3.setVisibility(View.GONE);
						}
					}
					voteOptionLL.addView(view);

				}
			}
		}
	}

	private void voteNow(List<NameValuePair> nameValuePairs){
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.OPINIONPOLLS_VOTE);
		request.setCallingClassObject(this);
		request.setNamevaluepair(nameValuePairs);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
	}

	public void response(OpinionPostAnswerPollsDetailsData opinionPostAnswerPollsDetailsData) {
		Intent intent = new Intent(OpinionPollDetailActivity.this, OpinionPollsPostAnswerResponseActivity.class);
		intent.putExtra("data", opinionPostAnswerPollsDetailsData);
		intent.putExtra("postdate", getIntent().getStringExtra("postdate"));
		intent.putExtra("enddate", getIntent().getStringExtra("enddate"));
		startActivity(intent);
	}

}
