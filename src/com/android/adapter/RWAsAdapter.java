package com.android.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.spideycity.R;
import com.androidquery.AQuery;
import com.bean.RWAItemsData;

public class RWAsAdapter extends BaseAdapter{

	private List<RWAItemsData> mRwaItemsDatasList;
	private LayoutInflater mLayoutInflater;
	private AQuery mAQuery;
	
	public static interface StartActivity{
		void startActivity(String serviceId);
	}
	
	public RWAsAdapter(LayoutInflater layoutInflater, List<RWAItemsData> rwaItemsDatasList, AQuery aQuery) {
		mRwaItemsDatasList = rwaItemsDatasList;
		mLayoutInflater = layoutInflater;
		mAQuery = aQuery;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mRwaItemsDatasList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mRwaItemsDatasList.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.row_rwa_layout, null, false);
			viewHolder.rwaIV = (ImageView)convertView.findViewById(R.id.iv_apartments);
			viewHolder.rwaAdressTV = (TextView)convertView.findViewById(R.id.tv_rwa_address);
			viewHolder.rwaTitleTV = (TextView)convertView.findViewById(R.id.tv_rwa_title);
			viewHolder.rwaDescTV = (TextView)convertView.findViewById(R.id.tv_rwa_desc);
			viewHolder.rwaCityTV = (TextView)convertView.findViewById(R.id.tv_rwa_city);
			 
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.rwaCityTV.setText(mRwaItemsDatasList.get(position).getCity());
		viewHolder.rwaAdressTV.setText(mRwaItemsDatasList.get(position).getAdr());
		viewHolder.rwaTitleTV.setText(mRwaItemsDatasList.get(position).getTitle());
		viewHolder.rwaDescTV.setText(mRwaItemsDatasList.get(position).getDesc());
		mAQuery.id(viewHolder.rwaIV).image(mRwaItemsDatasList.get(position).getImage());
		
		return convertView;
	}

	static class ViewHolder{
		TextView rwaCityTV;
		TextView rwaAdressTV;
		TextView rwaTitleTV;
		TextView rwaDescTV;
		ImageView rwaIV ;
	}
}
