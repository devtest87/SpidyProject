package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.adapter.GridServiceAdapter;
import com.android.adapter.GridServiceAdapter.StartActivity;
import com.android.cityspidey.R;
import com.bean.RequestBean;
import com.bean.RequestServicesData;
import com.bean.ServicesData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;

public class ServicesActivity extends BaseActivity  implements StartActivity{
	private GridView mGridView;
	private GridServiceAdapter mGridServiceAdapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_layout);
		
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_services_request_hint));
		titleTV.setText(getResources().getString(R.string.servvices));
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.servicecolor);
		
		mGridView = (GridView)findViewById(R.id.recyclerview_service);
		
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mGridServiceAdapter.startActivity(arg2);
			}
		});
		
		searchET.addTextChangedListener(new TextWatcher() {

		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        System.out.println("Text ["+s+"]");
		        if(mGridServiceAdapter!=null)
		        mGridServiceAdapter.getFilter().filter(s.toString());                           
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count,
		            int after) {

		    }

		    @Override
		    public void afterTextChanged(Editable s) {
		    }
		});
		
		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO));
		}
        
		loadService();
	}

	private void loadService() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.SERVICES);
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

	public void response(ServicesData servicesData) {
		
		/*mServicesAdapter = new ServicesAdapter(this, servicesData.getServicesItemsDatasList(), mAQuery);
		mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
		mRecyclerView.setAdapter(mServicesAdapter);*/
		
		mGridServiceAdapter = new GridServiceAdapter(getLayoutInflater(), this, servicesData.getServicesItemsDatasList(), mAQuery);
	    mGridView.setAdapter(mGridServiceAdapter);
	}

	@Override
	public void startActivity(String serviceId) {
		String[] id = serviceId.split("@");
		if(id[0].equals("CR")){
			checkRequestStatus();
		}else if(id[0].equals("RS")){
			String[] service = id[1].split("#");
			requestServiceDialog(service[0], service[1],  service.length ==3 ? service[2] : "");
		}
//		Intent intent = new Intent(this, RequestServicesActivity.class);
//		intent.putExtra("id", serviceId);
//		startActivity(intent);
	}

	private void checkRequestStatus() {
		Intent intent = new Intent(this, CheckRequestStatusActivity.class);
		startActivity(intent);
	}

	private void requestService(String serviceId, String msg) {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.REQUEST_SERVICES);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("user_id", PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.USER_ID));
		list.add(valuePair);
		valuePair = new BasicNameValuePair("rwa_id", PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.RWAS_ID));
		list.add(valuePair);
		valuePair = new BasicNameValuePair("service_id", serviceId);
		list.add(valuePair);
		valuePair = new BasicNameValuePair("msg", msg);
		list.add(valuePair);
		request.setCallingClassObject(this);
		request.setNamevaluepair(list);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
	}

	public void response(RequestServicesData requestServicesData) {
		
	}
	
	private void requestServiceDialog(final String url, final String serviceId, final String serviceName){
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_request_service);
        dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView serviceTitleTV = (TextView)dialog.findViewById(R.id.tv_title);
        TextView usernameTV = (TextView)dialog.findViewById(R.id.tv_username);
        TextView checkPreviousServiceTV = (TextView)dialog.findViewById(R.id.tv_check_previuos_request);
        TextView cancleTV = (TextView)dialog.findViewById(R.id.iv_cancel_icon);
        final EditText msgET = (EditText)dialog.findViewById(R.id.et_message);
        ImageView serviceIV = (ImageView)dialog.findViewById(R.id.iv_service_icon);
        
        mAQuery.id(serviceIV).image(url);
        PreferenceHelper preferenceHelper = PreferenceHelper.getSingleInstance(this.getApplicationContext());
        usernameTV.setText(preferenceHelper.getString(PreferenceKey.RWAS_NAME));
        serviceTitleTV.setText(serviceName);


        Button doneBTN = (Button)dialog.findViewById(R.id.btn_submit_request);
        
        checkPreviousServiceTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	checkRequestStatus();
                dialog.dismiss();
            }
        });

        cancleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	requestService(serviceId, msgET.getText().toString());
                dialog.dismiss();
            }
        });

        dialog.show();

    }
	
}
