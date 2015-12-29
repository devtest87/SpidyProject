package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.android.spideycity.R;
import com.bean.RWADetailData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;

public class RWAsDetailActivity extends BaseActivity{
	private String mDetailUrl,mFacilityUrl;
	//private ExecutorService mExecutorService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rwa_detail_layout);
		//		mExecutorService = Executors.newFixedThreadPool(1);
		//		mExecutorService.execute(new loadRWAs());


		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_rwas_hint));
		titleTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setBackgroundResource(R.color.rwacolor);

		findViewById(R.id.rl_search).setVisibility(View.GONE);

		mDetailUrl = getIntent().getStringExtra("url");
		mFacilityUrl = getIntent().getStringExtra("furl");

		detailAndfacility();
	}

	private void detailAndfacility() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.RWAS_DTEAIL);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("url", mDetailUrl);
		list.add(valuePair);
		valuePair = new BasicNameValuePair("furl", mFacilityUrl);
		list.add(valuePair);
		request.setNamevaluepair(list);
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

	public void response(RWADetailData rwaDetailData) {
		//		RWAsAdapter rwAsAdapter = new RWAsAdapter(getLayoutInflater(), rwAsData.getRwaItemsDatasList(), mAQuery);
		//		ListView listView = (ListView)findViewById(R.id.listview_rwa);
		//		listView.setAdapter(rwAsAdapter);


		TextView addressTitleTV = (TextView)findViewById(R.id.tv_address_title);
		TextView addressTV = (TextView)findViewById(R.id.tv_address);
		TextView conatctDetailTV = (TextView)findViewById(R.id.tv_contact_detail);
		ImageView rwaIV = (ImageView)findViewById(R.id.iv_apartments);
		TextView descTV = (TextView)findViewById(R.id.tv_rwa_detail);
		LinearLayout quickLookLL = (LinearLayout)findViewById(R.id.ll_quicklook);
		LinearLayout facilityLL = (LinearLayout)findViewById(R.id.ll_facility);
		LinearLayout parentLL = (LinearLayout)findViewById(R.id.ll_parent);
		if(rwaDetailData != null){
			parentLL.setVisibility(View.VISIBLE);

			mAQuery.id(rwaIV).image(rwaDetailData.getRwAsDetailItemData().getImage());
			addressTitleTV.setText(rwaDetailData.getRwAsDetailItemData().getTitle());
			descTV.setText(rwaDetailData.getRwAsDetailItemData().getDesc());
			addressTV.setText(rwaDetailData.getRwAsDetailItemData().getAddress());
			if(rwaDetailData.getRwAsDetailItemData().getCdetails() != null){
				JSONObject jobjLabel = rwaDetailData.getRwAsDetailItemData().getCdetailslabel();
				JSONObject jobjLabelDetail = rwaDetailData.getRwAsDetailItemData().getCdetails();

				StringBuilder stringBuilder = new StringBuilder();
				Iterator<String> key = rwaDetailData.getRwAsDetailItemData().getCdetails().keys();
				for (Iterator iterator = key; iterator.hasNext();) {
					String type = (String) iterator.next();
					try {
						stringBuilder.append(jobjLabel.getString(type) + " : " + jobjLabelDetail.getString(type) + "\n");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				conatctDetailTV.setText(stringBuilder);
			}

			LayoutInflater layoutInflater = getLayoutInflater();
			if(rwaDetailData.getRwAsDetailItemData().getQuickLookLablel() != null){
				JSONObject jobjLabel = rwaDetailData.getRwAsDetailItemData().getQuickLookLablel();
				JSONObject jobjLabelDetail = rwaDetailData.getRwAsDetailItemData().getQuickLook();

				int count = 0;
				LinearLayout linearLayout = null;
				LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				layoutParams.weight = 1.0f;
				int index = 1;
				Iterator<String> key = rwaDetailData.getRwAsDetailItemData().getQuickLookLablel().keys();
				for (Iterator iterator = key; iterator.hasNext();) {
					String type = (String) iterator.next();
					try {
						View view = layoutInflater.inflate(R.layout.inflate_quick_label, null, false);
						TextView quickLableName = (TextView)view.findViewById(R.id.tv_quciklabelname);
						TextView quickLableDetail = (TextView)view.findViewById(R.id.tv_quciklabeldetail);
						//view.setLayoutParams(layoutParams);
						quickLableName.setText(jobjLabel.getString(type) + " : ");
						quickLableDetail.setText(jobjLabelDetail.getString(type));
						//					if(count == 0){
						//						linearLayout = new LinearLayout(getApplicationContext());
						//						linearLayout.setOrientation(LinearLayout.HORIZONTAL);
						//					}
						//					linearLayout.addView(view);
						//					count ++;
						//					if(count == 2){
						quickLookLL.addView(view, index);
						index++;
						//						count = 0;
						//					}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}else{
				quickLookLL.setVisibility(View.GONE);
			}

			if(rwaDetailData.getRWAFacilityDataList().size() != 0){
				int count = 0;
				int index = 0;
				LinearLayout linearLayout = null;
				LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				layoutParams.weight = 1.0f;
				for (int i = 0; i < rwaDetailData.getRWAFacilityDataList().size(); i++) {
					View view = layoutInflater.inflate(R.layout.inflate_facilities, null, false);
					TextView quickLableName = (TextView)view.findViewById(R.id.tv_facilitylabelname);
					view.setLayoutParams(layoutParams);
					quickLableName.setText(rwaDetailData.getRWAFacilityDataList().get(i).getFacilityName());
					if(count == 0){
						linearLayout = new LinearLayout(getApplicationContext());
						linearLayout.setOrientation(LinearLayout.HORIZONTAL);
					}
					linearLayout.addView(view);
					count ++;
					if(count == 3){
						facilityLL.addView(linearLayout, index);
						index++;
						count = 0;
					}
				}
			}else{
				facilityLL.setVisibility(View.GONE);
			}
		}
	}
}
