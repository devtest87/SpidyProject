package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.ls.LSInput;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.adapter.SpidyPickDetailAdapter;
import com.android.spideycity.R;
import com.bean.NoticeBoardDetailData;
import com.bean.RequestBean;
import com.bean.SpidyPickDetailData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.Utils;
import com.utils.PreferenceHelper.PreferenceKey;

public class SpidyPickDetailActivity extends BaseActivity{
	
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spidypick_detail_layout);
		listView = (ListView)findViewById(R.id.listview);
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.spidey_pick));
		titleTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setBackgroundResource(R.color.spideycolor);
		findViewById(R.id.rl_search).setVisibility(View.GONE);

		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO));
		}
		
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
		View headerView = getHeaderView(spidyPickDetailData);
		View footerView = getFooterView(spidyPickDetailData);
		if(spidyPickDetailData.getCommentList().size() > 0){
			SpidyPickDetailAdapter spidyPickDetailAdapter = new SpidyPickDetailAdapter
					(getLayoutInflater(), spidyPickDetailData.getCommentList(), mAQuery);
			listView.setAdapter(spidyPickDetailAdapter);
		}
		listView.addHeaderView(headerView);
		listView.addFooterView(footerView);
	}

	private View getFooterView(SpidyPickDetailData spidyPickDetailData) {
		View view = getLayoutInflater().inflate(R.layout.inflate_write_comment, null, false);
		return view;
	}

	private View getHeaderView(SpidyPickDetailData spidyPickDetailData) {
		View view = getLayoutInflater().inflate(R.layout.inflate_spideypick_detail_header, null, false);
		TextView noticeBoardTitle = (TextView)view.findViewById(R.id.tv_noticeboard_title);
		TextView noticeBoardByLine = (TextView)view.findViewById(R.id.tv_noticeboard_byline);
		TextView noticeBoardDesc = (TextView)view.findViewById(R.id.tv_noticeboard_detail);
		TextView tag = (TextView)view.findViewById(R.id.tv_tags);
		TextView noticeBoardPostDate = (TextView)view.findViewById(R.id.tv_noticeboard_posteddate);
        ImageView noticeBoardIV = (ImageView)view.findViewById(R.id.iv_noticeboard);
		
		mAQuery.id(noticeBoardIV).image(spidyPickDetailData.getSpidyPickDetailItemsDataList().get(0).getImage());
		noticeBoardByLine.setText(spidyPickDetailData.getSpidyPickDetailItemsDataList().get(0).getByline());
		tag.setText(spidyPickDetailData.getSpidyPickDetailItemsDataList().get(0).getTags());
		noticeBoardTitle.setText(spidyPickDetailData.getSpidyPickDetailItemsDataList().get(0).getTitle());
		noticeBoardDesc.setText(spidyPickDetailData.getSpidyPickDetailItemsDataList().get(0).getDesc());
		noticeBoardPostDate.setText(Utils.getTimeRemaining(spidyPickDetailData.getSpidyPickDetailItemsDataList().get(0).getReleaseYear()));
		return view;
	}
}
