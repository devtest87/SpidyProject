package com.fragment.item;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.cityspidey.HomeScreen;
import com.android.cityspidey.R;
import com.android.itemsActivity.BookingActivity;
import com.android.itemsActivity.DirectoryActivity;
import com.android.itemsActivity.GroupsActivity;
import com.android.itemsActivity.NoticeBoardActivity;
import com.android.itemsActivity.OpinionPollActivity;
import com.android.itemsActivity.OpinionPollDetailActivity;
import com.android.itemsActivity.RWAsActivity;
import com.android.itemsActivity.ServicesActivity;
import com.android.itemsActivity.SpidyPickActivity;
import com.bean.RequestBean;
import com.bean.SliderData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;


public class HomeFragment extends Fragment implements OnClickListener {

	LinearLayout newslin, assignmentlin, notificationlin, buslin, eventslin, noticelin;
	private Button rwaBTN, groupsBTN, servicesBTN, bookingBTN, noticeBoardBTN, directoryBTN, spideyPickBTN,
	opinionPollBTN;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_home, container, false);
	}


	@SuppressLint("NewApi") @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Home</font>"));

//		newslin = (LinearLayout)getActivity().findViewById(R.id.newslin);
//		assignmentlin = (LinearLayout)getActivity().findViewById(R.id.assignmentlin);
//		notificationlin = (LinearLayout)getActivity().findViewById(R.id.maillin);
//		buslin = (LinearLayout)getActivity().findViewById(R.id.buslin);
//		eventslin = (LinearLayout)getActivity().findViewById(R.id.eventslin);
//		noticelin = (LinearLayout)getActivity().findViewById(R.id.noticelin);

//		newslin.setOnClickListener(this);
//		assignmentlin.setOnClickListener(this);
//		notificationlin.setOnClickListener(this);
//		buslin.setOnClickListener(this);
//		eventslin.setOnClickListener(this);
//		noticelin.setOnClickListener(this);
		
		callSliderWS();
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rwaBTN = (Button)view.findViewById(R.id.btn_rwa);
		groupsBTN = (Button)view.findViewById(R.id.btn_groups);
		servicesBTN = (Button)view.findViewById(R.id.btn_services);
		bookingBTN = (Button)view.findViewById(R.id.btn_booking);
		noticeBoardBTN = (Button)view.findViewById(R.id.btn_noticeboard);
		directoryBTN = (Button)view.findViewById(R.id.btn_directory);
		spideyPickBTN = (Button)view.findViewById(R.id.btn_spideypick);
		opinionPollBTN = (Button)view.findViewById(R.id.btn_opinionpolls);
		
		rwaBTN.setOnClickListener(this);
		groupsBTN.setOnClickListener(this);
		servicesBTN.setOnClickListener(this);
		bookingBTN.setOnClickListener(this);
		noticeBoardBTN.setOnClickListener(this);
		directoryBTN.setOnClickListener(this);
		spideyPickBTN.setOnClickListener(this);
		opinionPollBTN.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		if(v.equals(rwaBTN)){
			Intent intent = new Intent(getActivity(), RWAsActivity.class);
			getActivity().startActivity(intent);
		}else if(v.equals(groupsBTN)){
			Intent intent = new Intent(getActivity(), GroupsActivity.class);
			getActivity().startActivity(intent);
		}else if(v.equals(servicesBTN)){
			Intent intent = new Intent(getActivity(), ServicesActivity.class);
			getActivity().startActivity(intent);
		}else if(v.equals(bookingBTN)){
			Intent intent = new Intent(getActivity(), BookingActivity.class);
			getActivity().startActivity(intent);
		}else if(v.equals(noticeBoardBTN)){
			Intent intent = new Intent(getActivity(), NoticeBoardActivity.class);
			getActivity().startActivity(intent);
		}else if(v.equals(directoryBTN)){
			Intent intent = new Intent(getActivity(), DirectoryActivity.class);
			getActivity().startActivity(intent);
		}else if(v.equals(spideyPickBTN)){
			Intent intent = new Intent(getActivity(), SpidyPickActivity.class);
			getActivity().startActivity(intent);
		}else if(v.equals(opinionPollBTN)){
			Intent intent = new Intent(getActivity(), OpinionPollActivity.class);
			getActivity().startActivity(intent);
		}
//		if(v.equals(newslin))
//		{
//			Intent intent = new Intent(getActivity(), ListScreen.class);
//			intent.putExtra("From", "NEWS");
//			startActivity(intent);
//		}else if(v.equals(assignmentlin)){
//			Intent intent = new Intent(getActivity(), ListScreen.class);
//			intent.putExtra("From", "ASSIGNMENTS");
//			startActivity(intent);
//		}else if(v.equals(notificationlin)){
//			Intent intent = new Intent(getActivity(), ListScreen.class);
//			intent.putExtra("From", "NOTIFICATIONS");
//			startActivity(intent);
//		}else if(v.equals(buslin)){
//			Intent intent = new Intent(getActivity(), LocationOnMap.class);
//			intent.putExtra("From", "BUS");
//			startActivity(intent);
//		}else if(v.equals(eventslin)){
//			Intent intent = new Intent(getActivity(), ListScreen.class);
//			intent.putExtra("From", "EVENTS");
//			startActivity(intent);
//		}else if(v.equals(noticelin)){
//			Intent intent = new Intent(getActivity(), ListScreen.class);
//			intent.putExtra("From", "NOTICE");
//			startActivity(intent);
//		}
	}
	
	
	private void callSliderWS(){
		
		RequestBean request = new RequestBean();
		request.setActivity(getActivity());
		request.setNetworkRequestName(NetworkRequestName.HOMESLIDER);
		request.setCallingClassObject(HomeFragment.this);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
		
	}
	
	public void sliderWSresponse(SliderData sliderdata){
		
		if(sliderdata!=null){
			
		}
	}
	
}
