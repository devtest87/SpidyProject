package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.adapter.SpidyPickDetailAdapter;
import com.android.cityspidey.R;
import com.bean.CommentSave;
import com.bean.Comments;
import com.bean.RequestBean;
import com.bean.SpidyPickDetailData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;
import com.utils.Utils;

public class SpidyPickDetailActivity extends BaseActivity{
	
	private ListView listView;
	private SpidyPickDetailData spidyPickDetailData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spidypick_detail_layout);
		listView = (ListView)findViewById(R.id.listview);
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.spidey_pick));
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.spideycolor);

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

	private SpidyPickDetailAdapter spidyPickDetailAdapter;
	public void response(SpidyPickDetailData spidyPickDetailData) {
		this.spidyPickDetailData = spidyPickDetailData;
		View headerView = getHeaderView();
		View footerView = getFooterView();
		if(spidyPickDetailData.getCommentList().size() > 0){
			spidyPickDetailAdapter = new SpidyPickDetailAdapter
					(getLayoutInflater(), spidyPickDetailData.getCommentList(), mAQuery);
//			listView.setAdapter(spidyPickDetailAdapter);
		}
		listView.addHeaderView(headerView);
		listView.addFooterView(footerView);
		
		
		listView.setAdapter(spidyPickDetailAdapter);
	}

	private View getFooterView() {
		View view = getLayoutInflater().inflate(R.layout.inflate_write_comment, null, false);
		final EditText commentET = (EditText)view.findViewById(R.id.et_comment);
		TextView resetBTN = (TextView)view.findViewById(R.id.btn_reset);
		TextView submitBTN = (TextView)view.findViewById(R.id.btn_submit);
		resetBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				commentET.setText("");
			}
		});
		
		submitBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Comments comments = new Comments();
				comments.setCommentby(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.NAME));
				comments.setDescrption(commentET.getText().toString());
				comments.setProfilephoto(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO));
				spidyPickDetailData.getCommentList().add(comments);
				if(spidyPickDetailAdapter != null){
					spidyPickDetailAdapter = new SpidyPickDetailAdapter(getLayoutInflater(), spidyPickDetailData.getCommentList(), mAQuery);
					listView.setAdapter(spidyPickDetailAdapter);
				}else{
					spidyPickDetailAdapter.notifyDataSetChanged();
				}
				listView.setSelection(spidyPickDetailData.getCommentList().size()-1);
				comment(commentET.getText().toString());
				commentET.setText("");
			}
		});
		return view;
	}

	private View getHeaderView() {
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
	
	private void comment(String message) {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.COMMENT);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		BasicNameValuePair valuePair = new BasicNameValuePair("articleid", spidyPickDetailData.getSpidyPickDetailItemsDataList().get(0).getId());
		list.add(valuePair);
		valuePair = new BasicNameValuePair("content_type", "1");//1 for spideypick
		list.add(valuePair);
		valuePair = new BasicNameValuePair("userid", PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.USER_ID));
		list.add(valuePair);
		valuePair = new BasicNameValuePair("task", "addComments");
		list.add(valuePair);
		valuePair = new BasicNameValuePair("message", message);
		list.add(valuePair);
		
		request.setCallingClassObject(this);
		request.setNamevaluepair(list);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
	}


	public void response(CommentSave commentSave) {
		
	}
}
