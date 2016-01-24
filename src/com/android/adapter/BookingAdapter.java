package com.android.adapter;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.spideycity.R;
import com.androidquery.AQuery;
import com.bean.BookingOptionFacilityData;

public class BookingAdapter extends RecyclerView.Adapter<TextViewHolder> { 
	private List<BookingOptionFacilityData> facilityNameList;
	private StartActivity startActivity;
	private AQuery mAQuery;

	public static interface StartActivity{
		void startActivity(String serviceId);
	}
	public BookingAdapter(List<BookingOptionFacilityData> facilityNameList, StartActivity startActivity, AQuery aQuery) {
		this.facilityNameList = facilityNameList;
		this.startActivity = startActivity;
		this.mAQuery = aQuery;
	} 


	@Override 
	public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_booking_options, parent, false);
		return new TextViewHolder(view);
	} 


	@Override 
	public void onBindViewHolder(final TextViewHolder holder, final int position) {
		final String label = facilityNameList.get(position).getFacilityName();
		holder.textView.setText(label);
		mAQuery.id(holder.bookingOptionIV).image(facilityNameList.get(position).getFacilityImg());
		holder.textView.setTag(position);
		holder.textView.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) {
				startActivity.startActivity(v.getTag().toString());
			} 
		}); 
	} 


	@Override 
	public int getItemCount() { 
		return facilityNameList.size();
	} 
} 