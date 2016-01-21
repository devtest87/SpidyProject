package com.android.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.spideycity.R;
import com.androidquery.AQuery;
import com.bean.CheckRequestItemsData;
import com.utils.Utils;

public class CheckRequestAdapter extends BaseAdapter implements Filterable{

	private List<CheckRequestItemsData> mCheckRequestItemsDataList;
	private List<CheckRequestItemsData> mCheckRequestFilterItemsDataList;
	private LayoutInflater mLayoutInflater;
	private StartActivity mStartActivity;
	private AQuery mAQuery;
	private ItemFilter mFilter = new ItemFilter();
	
	public static interface StartActivity{
		void startActivity(String serviceId);
	}
	
	public CheckRequestAdapter(LayoutInflater layoutInflater, StartActivity startActivity, List<CheckRequestItemsData> checkRequestItemsDataList, AQuery aQuery) {
		mCheckRequestItemsDataList = checkRequestItemsDataList;
		mCheckRequestFilterItemsDataList = checkRequestItemsDataList;
		mStartActivity = startActivity;
		mLayoutInflater = layoutInflater;
		mAQuery = aQuery;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCheckRequestFilterItemsDataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mCheckRequestFilterItemsDataList.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.row_checkrequest_service_layout, null, false);
			viewHolder.serviceIV = (ImageView)convertView.findViewById(R.id.iv_serviceicon);
			viewHolder.titleTV = (TextView)convertView.findViewById(R.id.tv_service_title);
			viewHolder.postDateTV = (TextView)convertView.findViewById(R.id.tv_service_date);
			viewHolder.descTV = (TextView)convertView.findViewById(R.id.tv_service_desc);
			viewHolder.alertTV = (TextView)convertView.findViewById(R.id.tv_service_alert);
			viewHolder.statusTV = (TextView)convertView.findViewById(R.id.tv_service_status);
			viewHolder.deleteTV = (TextView)convertView.findViewById(R.id.tv_service_delete);
			 
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.titleTV.setText(mCheckRequestFilterItemsDataList.get(position).getServiceName());
		viewHolder.postDateTV.setText(Utils.getTimeRemaining(mCheckRequestFilterItemsDataList.get(position).getCreatedDate()));
		viewHolder.descTV.setText(mCheckRequestFilterItemsDataList.get(position).getDesc());
		viewHolder.alertTV.setText("");
		viewHolder.statusTV.setText(mCheckRequestFilterItemsDataList.get(position).getService_status());
		//mAQuery.id(viewHolder.serviceIV).image(mCheckRequestFilterItemsDataList.get(position).getImage());
		
		viewHolder.deleteTV.setTag(position);
		viewHolder.deleteTV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int pos = Integer.parseInt(v.getTag().toString());
			}
		});
		
		return convertView;
	}

	static class ViewHolder{
		TextView titleTV;
		TextView postDateTV;
		TextView descTV;
		TextView alertTV;
		TextView statusTV;
		TextView deleteTV;
		ImageView serviceIV ;
	}
	
	@Override
	public Filter getFilter() {
		return mFilter;
	}
	
	private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<CheckRequestItemsData> list = mCheckRequestItemsDataList;

            int count = list.size();
            final List<CheckRequestItemsData> nlist = new ArrayList<CheckRequestItemsData>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getServiceName();
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(list.get(i));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
        	mCheckRequestFilterItemsDataList = (List<CheckRequestItemsData>) results.values;
            notifyDataSetChanged();
        }

    }

	public void startActivity(int arg2) {
		mStartActivity.startActivity(mCheckRequestFilterItemsDataList.get(arg2).getId());
	}
}
