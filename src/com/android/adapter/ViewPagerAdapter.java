package com.android.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cityspidey.HomeScreen;
import com.android.cityspidey.R;
import com.androidquery.AQuery;
import com.bean.HomeSliderItem;

public class ViewPagerAdapter extends PagerAdapter {

	Activity activity;
	List<HomeSliderItem> homedata;

	ImageView img;
	TextView txtv1;
	TextView txtv2;

	protected AQuery mAQuery;

	public ViewPagerAdapter( Activity act,  List<HomeSliderItem> homelist) {
		this.homedata = homelist;
		activity = act;

		mAQuery = new AQuery(activity.getApplicationContext());
	}

	public int getCount() {
		return homedata.size();
	}

	public Object instantiateItem(View collection,final int position) {
		//		ImageView view = new ImageView(activity);
		//		view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
		//				LayoutParams.FILL_PARENT));
		//		view.setScaleType(ScaleType.FIT_XY);
		//		view.setBackgroundResource(imageArray[position]);
		//		((ViewPager) collection).addView(view, 0);

		LayoutInflater inflater = (LayoutInflater)   activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		View view = inflater.inflate(R.layout.inflatehomeslider, null);


		img = (ImageView)view.findViewById(R.id.bg);
		txtv1 = (TextView)view.findViewById(R.id.text1);
		txtv2 = (TextView)view.findViewById(R.id.text2);

		mAQuery.id(img).image(homedata.get(position).getImage());

		//		txtv1.setText(position+" *");

		txtv1.setText(homedata.get(position).getGenre());
		txtv2.setText(homedata.get(position).getTitle());
		 if(homedata.get(position).getGenre().equalsIgnoreCase("RWA")){
			txtv1.setBackgroundResource(R.color.rwacolor);
			txtv2.setBackgroundResource(R.color.rwacolor);
			txtv1.setTextColor(activity.getResources().getColor(R.color.black));
			txtv2.setTextColor(activity.getResources().getColor(R.color.black));
		}else if(homedata.get(position).getGenre().equalsIgnoreCase("GROUPS")){
			txtv1.setBackgroundResource(R.color.groupcolor);
			txtv2.setBackgroundResource(R.color.groupcolor);
			txtv1.setTextColor(activity.getResources().getColor(R.color.black));
			txtv2.setTextColor(activity.getResources().getColor(R.color.black));
		}else if(homedata.get(position).getGenre().equalsIgnoreCase("SERVICE")){
			txtv1.setBackgroundResource(R.color.servicecolor);
			txtv2.setBackgroundResource(R.color.servicecolor);
			txtv1.setTextColor(activity.getResources().getColor(R.color.white));
			txtv2.setTextColor(activity.getResources().getColor(R.color.white));
		}else if(homedata.get(position).getGenre().equalsIgnoreCase("BOOKING")){
			txtv1.setBackgroundResource(R.color.bookingcolor);
			txtv2.setBackgroundResource(R.color.bookingcolor);
			txtv1.setTextColor(activity.getResources().getColor(R.color.white));
			txtv2.setTextColor(activity.getResources().getColor(R.color.white));
		}else if(homedata.get(position).getGenre().equalsIgnoreCase("NOTICE BOARD")){
			txtv1.setBackgroundResource(R.color.noticecolor);
			txtv2.setBackgroundResource(R.color.noticecolor);
			txtv1.setTextColor(activity.getResources().getColor(R.color.white));
			txtv2.setTextColor(activity.getResources().getColor(R.color.white));
		}else if(homedata.get(position).getGenre().equalsIgnoreCase("DIRECTORY")){
			txtv1.setBackgroundResource(R.color.directorycolor);
			txtv2.setBackgroundResource(R.color.directorycolor);
			txtv1.setTextColor(activity.getResources().getColor(R.color.white));
			txtv2.setTextColor(activity.getResources().getColor(R.color.white));
		}else if(homedata.get(position).getGenre().equalsIgnoreCase("SPIDEY BUZZ")){
			txtv1.setBackgroundResource(R.color.spideycolor);
			txtv2.setBackgroundResource(R.color.spideycolor);
			txtv1.setTextColor(activity.getResources().getColor(R.color.white));
			txtv2.setTextColor(activity.getResources().getColor(R.color.white));
		}else if(homedata.get(position).getGenre().equalsIgnoreCase("OPINION POLLS")){
			txtv1.setBackgroundResource(R.color.opinioncolor);
			txtv2.setBackgroundResource(R.color.opinioncolor);
			txtv1.setTextColor(activity.getResources().getColor(R.color.white));
			txtv2.setTextColor(activity.getResources().getColor(R.color.white));
		}

		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				((HomeScreen) activity).sliderclicked(homedata.get(position).getUrl());

			}
		});

		((ViewPager) collection).addView(view, 0);

		return view;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View) arg1);
	}

	@Override
	public Parcelable saveState() {
		return null;
	}
}
