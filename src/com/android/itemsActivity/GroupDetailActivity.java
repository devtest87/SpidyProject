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
import com.bean.GroupDetailData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.Utils;

public class GroupDetailActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_detail_layout);
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.groups));
		titleTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setBackgroundResource(R.color.groupcolor);
		findViewById(R.id.rl_search).setVisibility(View.GONE);

		
		loadGroupDetail();
	}

	private void loadGroupDetail() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.GROUPS_DETAIL);
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

	public void response(GroupDetailData groupDetailData) {
		TextView groupTitleTV = (TextView)findViewById(R.id.tv_group_title);
		TextView createdDAteTV = (TextView)findViewById(R.id.tv_createddate);
		TextView groupAdminTV = (TextView)findViewById(R.id.tv_groupadmin);
		ImageView groupDetailIV = (ImageView)findViewById(R.id.iv_group_detail);
		mAQuery.id(groupDetailIV).image(groupDetailData.getGroupDetailItemsDataList().get(0).getImage());
		TextView descTV = (TextView)findViewById(R.id.tv_desc);
		groupTitleTV.setText(groupDetailData.getGroupDetailItemsDataList().get(0).getTitle());
		createdDAteTV.setText("Created: " + Utils.getTimeRemaining(groupDetailData.getGroupDetailItemsDataList().get(0).getCreatedDate()));
		groupAdminTV.setText("Group Admin: " + groupDetailData.getGroupDetailItemsDataList().get(0).getCreatedby());
		descTV.setText(groupDetailData.getGroupDetailItemsDataList().get(0).getDesc());
	}
}
