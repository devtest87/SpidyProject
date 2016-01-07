package com.android.spideycity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.spideycity.R;
import com.bean.RequestBean;
import com.bean.SliderData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;


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
//		if(v.equals(rwaBTN)){
//			Intent intent = new Intent(getActivity(), RWAsActivity.class);
//			getActivity().startActivity(intent);
//		}else if(v.equals(groupsBTN)){
//			Intent intent = new Intent(getActivity(), GroupsActivity.class);
//			getActivity().startActivity(intent);
//		}else if(v.equals(servicesBTN)){
//			Intent intent = new Intent(getActivity(), ServicesActivity.class);
//			getActivity().startActivity(intent);
//		}else if(v.equals(bookingBTN)){
//			Intent intent = new Intent(getActivity(), BookingActivity.class);
//			getActivity().startActivity(intent);
//		}else if(v.equals(noticeBoardBTN)){
//			Intent intent = new Intent(getActivity(), NoticeBoardActivity.class);
//			getActivity().startActivity(intent);
//		}else if(v.equals(directoryBTN)){
//			Intent intent = new Intent(getActivity(), DirectoryActivity.class);
//			getActivity().startActivity(intent);
//		}else if(v.equals(spideyPickBTN)){
//			Intent intent = new Intent(getActivity(), SpidyPickActivity.class);
//			getActivity().startActivity(intent);
//		}else if(v.equals(opinionPollBTN)){
//			Intent intent = new Intent(getActivity(), OpinionPollActivity.class);
//			getActivity().startActivity(intent);
//		}
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
