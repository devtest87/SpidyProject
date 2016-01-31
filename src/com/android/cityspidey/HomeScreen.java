package com.android.cityspidey; 


import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.adapter.ViewPagerAdapter;
import com.android.itemsActivity.BaseActivity;
import com.android.itemsActivity.BookingActivity;
import com.android.itemsActivity.DirectoryActivity;
import com.android.itemsActivity.EditProfileActivity;
import com.android.itemsActivity.GroupsActivity;
import com.android.itemsActivity.NoticeBoardActivity;
import com.android.itemsActivity.NoticeBoardDetailActivity;
import com.android.itemsActivity.OpinionPollActivity;
import com.android.itemsActivity.OpinionPollDetailActivity;
import com.android.itemsActivity.RWAsActivity;
import com.android.itemsActivity.ServicesActivity;
import com.android.itemsActivity.SpidyPickActivity;
import com.android.itemsActivity.SpidyPickDetailActivity;
import com.androidquery.AQuery;
import com.bean.RequestBean;
import com.bean.SliderData;
import com.customview.CircularImageView;
import com.network.NetworkCall;
import com.utils.AppConstant;
import com.utils.DialogController;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;




public class HomeScreen extends BaseActivity implements OnClickListener {


	LinearLayout newslin, assignmentlin, notificationlin, buslin, eventslin, noticelin;
	private Button rwaBTN, groupsBTN, servicesBTN, bookingBTN, noticeBoardBTN, directoryBTN, spideyPickBTN,
	opinionPollBTN;
	private Button votenow;
	private RelativeLayout rightbottom;
	private String noticeurl = null;
	protected AQuery mAQuery;
	private LinearLayout circleroot;
	private JSONObject voteJson = new JSONObject();


	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_home);

		mAQuery = new AQuery(getApplicationContext());
		
		rwaBTN = (Button)findViewById(R.id.btn_rwa);
		groupsBTN = (Button)findViewById(R.id.btn_groups);
		servicesBTN = (Button)findViewById(R.id.btn_services);
		bookingBTN = (Button)findViewById(R.id.btn_booking);
		noticeBoardBTN = (Button)findViewById(R.id.btn_noticeboard);
		directoryBTN = (Button)findViewById(R.id.btn_directory);
		spideyPickBTN = (Button)findViewById(R.id.btn_spideypick);
		opinionPollBTN = (Button)findViewById(R.id.btn_opinionpolls);
		rightbottom = (RelativeLayout)findViewById(R.id.right);

		votenow = (Button)findViewById(R.id.votenow);
		
		rwaBTN.setOnClickListener(this);
		groupsBTN.setOnClickListener(this);
		servicesBTN.setOnClickListener(this);
		bookingBTN.setOnClickListener(this);
		noticeBoardBTN.setOnClickListener(this);
		directoryBTN.setOnClickListener(this);
		spideyPickBTN.setOnClickListener(this);
		opinionPollBTN.setOnClickListener(this);
		votenow.setOnClickListener(this);
		rightbottom.setOnClickListener(this);
		
		circleroot = (LinearLayout)findViewById(R.id.dotroot);

		TextView username = (TextView)findViewById(R.id.tv_profile_name);
		TextView welcome = (TextView)findViewById(R.id.tv_welcome);

		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_home_hint));

		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			username.setText("Hello "+ PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.NAME));
//			welcome.setText("Welcome to " + PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.RWAS_NAME));
			welcome.setText(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.RWAS_NAME));
				mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO), true, true, 0, R.drawable.profile);
		}
		
		CircularImageView imageView = (CircularImageView)findViewById(R.id.iv_profile_picture);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN))
				showMenu();
			}
		});


		callWS(); 
	} 
	



	protected void showMenu() {
		CharSequence colors[] = new CharSequence[] {"Profile", "logout"};
		 
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Cityspidey");
		builder.setItems(colors, new DialogInterface.OnClickListener() {
		    @Override 
		    public void onClick(DialogInterface dialog, int which) {
		    	if(which == 0){
		    		Intent intent = new Intent(HomeScreen.this, EditProfileActivity.class);
		    		startActivity(intent);
		    	}else if(which == 1){
		    		PreferenceHelper.getSingleInstance(getApplicationContext()).setString(PreferenceKey.RWAS_ID, null);
		    		PreferenceHelper.getSingleInstance(getApplicationContext()).setString(PreferenceKey.NAME, null);
		    		PreferenceHelper.getSingleInstance(getApplicationContext()).setString(PreferenceKey.EMAIL, null);
		    		PreferenceHelper.getSingleInstance(getApplicationContext()).setString(PreferenceKey.USER_ID, null);
		    		PreferenceHelper.getSingleInstance(getApplicationContext()).setString(PreferenceKey.PHOTO, null);
		    		PreferenceHelper.getSingleInstance(getApplicationContext()).setString(PreferenceKey.RWAS_NAME, null);
		    		PreferenceHelper.getSingleInstance(getApplicationContext()).setBoolean(PreferenceKey.IS_LOGIN, false);
		    		Intent intent = new Intent(HomeScreen.this, LoginActivity.class);
		    		startActivity(intent);
		    		finish();
		    	}
		    } 
		}); 
		builder.show();		
	}




	@Override 
	public void onClick(View v) {
		if(v.equals(rwaBTN)){ 
			Intent intent = new Intent(HomeScreen.this, RWAsActivity.class); 
			startActivityForResult(intent, AppConstant.REQUEST_REGISTER_CODE); 
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
		} else if(v.equals(votenow)){
			
			try {
				Intent intent = new Intent(HomeScreen.this, OpinionPollDetailActivity.class);
				intent.putExtra("url", voteJson.getString("url"));
				intent.putExtra("postdate", voteJson.getString("postdate"));
				intent.putExtra("enddate", voteJson.getString("enddate"));
				intent.putExtra("image", voteJson.getString("image"));
				startActivity(intent);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else if(v.equals(rightbottom)){
			if(noticeurl!=null){
			Intent intent = new Intent(this, NoticeBoardDetailActivity.class);
			intent.putExtra("url", noticeurl);
			startActivity(intent);
			}
		}
	} 

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == AppConstant.REQUEST_HOME_CODE && resultCode == RESULT_OK){
			Intent intent = new Intent(HomeScreen.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}else if(requestCode == AppConstant.REQUEST_REGISTER_CODE && resultCode == RESULT_OK){
			Intent intent = new Intent(HomeScreen.this, RegisterActivity.class);
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
			if(sliderdata.getPollitem()!=null){
				((TextView) findViewById(R.id.optitle)).setText(sliderdata.getPollitem().getGenre());
				((TextView) findViewById(R.id.opdesc)).setText(sliderdata.getPollitem().getTitle());
				//				 ((TextView) findViewById(R.id.optime)).setText("POSTED "+sliderdata.getPollitem().getStartPoll() +"AGO / VOTING ENDS "+ sliderdata.getPollitem().getEndPoll());

				((TextView) findViewById(R.id.optime)).setText("POSTED "+sliderdata.getPollitem().getStartPoll() +" / VOTING ENDS "+ sliderdata.getPollitem().getEndPoll());

				try{
				
				voteJson.put("url", sliderdata.getPollitem().getUrl());
				voteJson.put("postdate", sliderdata.getPollitem().getStartPoll());
				voteJson.put("enddate", sliderdata.getPollitem().getEndPoll());
				voteJson.put("image", sliderdata.getPollitem().getImage());
				
				}catch(JSONException je){
					je.printStackTrace();
				}
				
			}

			if(sliderdata.getNoticeitem()!=null){

				mAQuery.id(findViewById(R.id.rwicon)).image(sliderdata.getNoticeitem().getIcon());

				((TextView) findViewById(R.id.rwtitle)).setText(sliderdata.getNoticeitem().getGenre());
				((TextView) findViewById(R.id.rwdesc)).setText(sliderdata.getNoticeitem().getTitle());
				((TextView) findViewById(R.id.rwtime)).setText(sliderdata.getNoticeitem().getDesc());
				
				noticeurl = sliderdata.getNoticeitem().getUrl();
			}

			if(sliderdata.getSliderList()!=null){

				NUM_PAGES = sliderdata.getSliderList().size();

				ViewPagerAdapter adapter = new ViewPagerAdapter(this, sliderdata.getSliderList());
				myPager = (ViewPager) findViewById(R.id.myfivepanelpager);
				myPager.setAdapter(adapter);
				myPager.setCurrentItem(0);

				getCircle(sliderdata.getSliderList().size());
				
				autoscroll();
				
				PageListener pageListener = new PageListener();
				myPager.setOnPageChangeListener(pageListener);
			}
		} 
	} 

	
	class PageListener extends SimpleOnPageChangeListener{
        public void onPageSelected(int position) {
            Log.i("TAG", "page selected " + position);
               changecolor(position);
    }
}

	private int NUM_PAGES = 0;
	private int currentPage = 0;
	private Timer swipeTimer;
	private ViewPager myPager;

	private void autoscroll(){
		final Handler handler = new Handler();

		final Runnable Update = new Runnable() {
			public void run() {
				if (currentPage == NUM_PAGES - 1) {
					currentPage = 0;
				}
				myPager.setCurrentItem(currentPage++, true);
			}
		};

		swipeTimer = new Timer();
		swipeTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				handler.post(Update);
			}
		}, 10000, 10000);
	}

	private void getCircle(int count){
		//ImageView Setup

		for (int i = 0; i < count; i++) {
			ImageView imageView = new ImageView(this);
			//setting image resource
			imageView.setBackgroundResource(R.drawable.graycircle);
			
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, 20);
			lp.setMargins(10, 0, 0, 0);
			imageView.setLayoutParams(lp);
			
			//adding view to layout
			circleroot.addView(imageView);	
		}
		
		changecolor(0);
			
	}

	
	public void changecolor(int current){
		
		for (int i = 0; i < circleroot.getChildCount(); i++) {
				circleroot.getChildAt(i).setBackgroundResource(R.drawable.graycircle);
		}
		circleroot.getChildAt(current).setBackgroundResource(R.drawable.whitecircle);
		
		
	}
	

	public void sliderclicked(String url){
		Intent intent = new Intent(HomeScreen.this, SpidyPickDetailActivity.class);
		intent.putExtra("url", url);
		startActivity(intent);
	}

	

} 