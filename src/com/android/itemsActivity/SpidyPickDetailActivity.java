package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.spideycity.R;
import com.bean.NoticeBoardDetailData;
import com.bean.RequestBean;
import com.bean.SpidyPickDetailData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.Utils;

public class SpidyPickDetailActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spidypick_detail_layout);
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.spidey_pick));
		titleTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setBackgroundResource(R.color.noticecolor);
		findViewById(R.id.rl_search).setVisibility(View.GONE);

		
		loadSpidyPickDetail();
	}

	private void loadSpidyPickDetail() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.SPIDYPICKS_DETAILS);
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

	public void response(SpidyPickDetailData spidyPickDetailData) {
		TextView noticeBoardTitle = (TextView)findViewById(R.id.tv_noticeboard_title);
		TextView noticeBoardDesc = (TextView)findViewById(R.id.tv_noticeboard_detail);
		TextView noticeBoardPostDate = (TextView)findViewById(R.id.tv_posteddate);
		
        ImageView noticeBoardIV = (ImageView)findViewById(R.id.iv_noticeboard);
		
		mAQuery.id(noticeBoardIV).image(spidyPickDetailData.getSpidyPickDetailItemsDataList().get(0).getImage());
		noticeBoardTitle.setText(spidyPickDetailData.getSpidyPickDetailItemsDataList().get(0).getTitle());
		noticeBoardDesc.setText(spidyPickDetailData.getSpidyPickDetailItemsDataList().get(0).getDesc());
		noticeBoardPostDate.setText(Utils.getTimeRemaining(spidyPickDetailData.getSpidyPickDetailItemsDataList().get(0).getReleaseYear()));
	}
}