package com.android.spideycity; 
 
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.itemsActivity.BookingActivity;
import com.android.itemsActivity.DirectoryActivity;
import com.android.itemsActivity.GroupsActivity;
import com.android.itemsActivity.NoticeBoardActivity;
import com.android.itemsActivity.OpinionPollActivity;
import com.android.itemsActivity.RWAsActivity;
import com.android.itemsActivity.ServicesActivity;
import com.android.itemsActivity.SpidyPickActivity;
import com.bean.RequestBean;
import com.bean.SliderData;
import com.network.NetworkCall;
import com.utils.AppConstant;
import com.utils.DialogController;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;
 
 
 
 
public class HomeScreen extends Activity implements OnClickListener {
 
 
	LinearLayout newslin, assignmentlin, notificationlin, buslin, eventslin, noticelin;
	private Button rwaBTN, groupsBTN, servicesBTN, bookingBTN, noticeBoardBTN, directoryBTN, spideyPickBTN,
	opinionPollBTN;
 
 
	 
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		setContentView(R.layout.fragment_home);
 
 
		rwaBTN = (Button)findViewById(R.id.btn_rwa);
		groupsBTN = (Button)findViewById(R.id.btn_groups);
		servicesBTN = (Button)findViewById(R.id.btn_services);
		bookingBTN = (Button)findViewById(R.id.btn_booking);
		noticeBoardBTN = (Button)findViewById(R.id.btn_noticeboard);
		directoryBTN = (Button)findViewById(R.id.btn_directory);
		spideyPickBTN = (Button)findViewById(R.id.btn_spideypick);
		opinionPollBTN = (Button)findViewById(R.id.btn_opinionpolls);
		 
		rwaBTN.setOnClickListener(this);
		groupsBTN.setOnClickListener(this);
		servicesBTN.setOnClickListener(this);
		bookingBTN.setOnClickListener(this);
		noticeBoardBTN.setOnClickListener(this);
		directoryBTN.setOnClickListener(this);
		spideyPickBTN.setOnClickListener(this);
		opinionPollBTN.setOnClickListener(this);
 
 
		callWS(); 
	} 
	 
	 
 
 
	@Override 
	public void onClick(View v) {
		if(v.equals(rwaBTN)){ 
			Intent intent = new Intent(HomeScreen.this, RWAsActivity.class); 
			startActivityForResult(intent, AppConstant.REQUEST_HOME_CODE); 
		}else if(v.equals(groupsBTN)){ 
			Intent intent = new Intent(HomeScreen.this, GroupsActivity.class); 
			startActivityForResult(intent, AppConstant.REQUEST_HOME_CODE); 
		}else if(v.equals(servicesBTN)){ 
			if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
				Intent intent = new Intent(HomeScreen.this, ServicesActivity.class); 
				startActivity(intent); 
			}else{
				DialogController.login(HomeScreen.this);
			}
		}else if(v.equals(bookingBTN)){ 
			if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
				Intent intent = new Intent(HomeScreen.this, BookingActivity.class); 
				startActivity(intent); 
			}else{
				DialogController.login(HomeScreen.this);
			}
		}else if(v.equals(noticeBoardBTN)){ 
			if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
				Intent intent = new Intent(HomeScreen.this, NoticeBoardActivity.class); 
				startActivity(intent); 
			}else{
				DialogController.login(HomeScreen.this);
			}
		}else if(v.equals(directoryBTN)){ 
			if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
				Intent intent = new Intent(HomeScreen.this, DirectoryActivity.class); 
				startActivity(intent); 
			}else{
				DialogController.login(HomeScreen.this);
			}
		}else if(v.equals(spideyPickBTN)){ 
			Intent intent = new Intent(HomeScreen.this, SpidyPickActivity.class); 
			startActivityForResult(intent, AppConstant.REQUEST_HOME_CODE); 
		}else if(v.equals(opinionPollBTN)){ 
			Intent intent = new Intent(HomeScreen.this, OpinionPollActivity.class); 
			startActivityForResult(intent, AppConstant.REQUEST_HOME_CODE);  
		} 
	} 
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == AppConstant.REQUEST_HOME_CODE){
			Intent intent = new Intent(HomeScreen.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
	}
	 
	 
	private void callWS(){ 
		 
		RequestBean request = new RequestBean();
		request.setActivity(HomeScreen.this);
		request.setNetworkRequestName(NetworkRequestName.HOMESLIDER);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
		 
	} 
	 
	public void sliderWSresponse(SliderData sliderdata){
		 
		if(sliderdata!=null){
			 
		} 
	} 
	 
} 