package com.android.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.cityspidey.R;
import com.bean.BookingItemsData;

public class BookingListAdapter extends BaseAdapter{

	private List<BookingItemsData> mBookingItemsDataList;
	private LayoutInflater mLayoutInflater;
	
	public BookingListAdapter(LayoutInflater layoutInflater, List<BookingItemsData> bookingItemsDataList) {
		mBookingItemsDataList = bookingItemsDataList;
		mLayoutInflater = layoutInflater;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mBookingItemsDataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mBookingItemsDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.row_booinglist_layout, null, false);
			viewHolder.bookingTimeTV = (TextView)convertView.findViewById(R.id.tv_booking_time);
			viewHolder.bookingcreatedNameTV = (TextView)convertView.findViewById(R.id.tv_created);
			viewHolder.bookingAddrTV = (TextView)convertView.findViewById(R.id.tv_address);
			 
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.bookingTimeTV.setText(mBookingItemsDataList.get(position).getStarttime() + " to " + mBookingItemsDataList.get(position).getEndtime());
		viewHolder.bookingcreatedNameTV.setText(mBookingItemsDataList.get(position).getCreatedby());
		viewHolder.bookingAddrTV.setText(mBookingItemsDataList.get(position).getFacilityName());
		
		return convertView;
	}

	static class ViewHolder{
		TextView bookingTimeTV;
		TextView bookingcreatedNameTV;
		TextView bookingAddrTV;
	}
}
