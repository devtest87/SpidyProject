package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.spideycity.R;
import com.bean.NoticeBoardDetailData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.Utils;
import com.utils.PreferenceHelper.PreferenceKey;

public class NoticeBoardDetailActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticeboard_detail_layout);
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.notice_board));
		titleTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setBackgroundResource(R.color.noticecolor);
		findViewById(R.id.rl_search).setVisibility(View.GONE);
		
		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO));
		}
		
		loadNoticeBoardDetail();
	}

	private void loadNoticeBoardDetail() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.NOTICEBOARDS_DETAILS);
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

	public void response(NoticeBoardDetailData noticeBoardDetailData) {
		TextView noticeBoardTitle = (TextView)findViewById(R.id.tv_noticeboard_title);
		TextView noticeBoardDesc = (TextView)findViewById(R.id.tv_noticeboard_detail);
		TextView noticeBoardPostDate = (TextView)findViewById(R.id.tv_posteddate);
		ImageView noticeBoardIV = (ImageView)findViewById(R.id.iv_noticeboard);
		
		mAQuery.id(noticeBoardIV).image(noticeBoardDetailData.getNoticeBoardDetailItemsData().get(0).getImage());
		noticeBoardTitle.setText(noticeBoardDetailData.getNoticeBoardDetailItemsData().get(0).getTitle());
		noticeBoardDesc.setText(noticeBoardDetailData.getNoticeBoardDetailItemsData().get(0).getDesc());
		noticeBoardPostDate.setText(Utils.getTimeRemaining(noticeBoardDetailData.getNoticeBoardDetailItemsData().get(0).getReleaseYear()));
	}
}
