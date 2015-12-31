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
import com.bean.ServicesItemsData;

public class GridServiceAdapter extends BaseAdapter implements Filterable{

	private List<ServicesItemsData> mServicesItemsDatasList;
	private List<ServicesItemsData> mServicesFilterItemsDatasList;
	private StartActivity mStartActivity;
	private AQuery mAQuery;
	private LayoutInflater mLayoutInflater;
	private ItemFilter mFilter = new ItemFilter();

	public static interface StartActivity{
		void startActivity(String serviceId);
	}

	public GridServiceAdapter(LayoutInflater layoutInflater, StartActivity startActivity, List<ServicesItemsData> servicesItemsDatasList, AQuery aQuery) {
		mServicesItemsDatasList = servicesItemsDatasList;
		mServicesFilterItemsDatasList = servicesItemsDatasList;
		mStartActivity = startActivity;
		mAQuery = aQuery;
		mLayoutInflater = layoutInflater;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mServicesFilterItemsDatasList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mServicesFilterItemsDatasList.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.grid_item_services, null, false);
		    viewHolder.serviceIconIV = (ImageView)convertView.findViewById(R.id.iv_services_icon);
		    viewHolder.titleTV = (TextView)convertView.findViewById(R.id.tv_title);
			viewHolder.mobileNumberTV = (TextView)convertView.findViewById(R.id.tv_mobilenumber);
			viewHolder.descTextView = (TextView)convertView.findViewById(R.id.tv_description);
			viewHolder.checkRequestTitleTextView = (TextView)convertView.findViewById(R.id.tv_checkrequest_status_title);
			viewHolder.checkRequestTextView = (TextView)convertView.findViewById(R.id.tv_checkrequest_status);
			 
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.titleTV.setText(mServicesFilterItemsDatasList.get(position).getTitle());
		StringBuilder stringBuilder = new StringBuilder();
		String[] provider = mServicesFilterItemsDatasList.get(position).getServiceProvider().split(",");
		String[] mobile = mServicesFilterItemsDatasList.get(position).getMobile().split(",");
		for (int i = 0; i < provider.length; i++) {
			stringBuilder.append("\n" + provider[i].trim() + "\n" + mobile[i].trim());
		}
		
		viewHolder.mobileNumberTV.setText(stringBuilder.toString());
		viewHolder.descTextView.setText(mServicesFilterItemsDatasList.get(position).getDesc());
		mAQuery.id(viewHolder.serviceIconIV).image(mServicesFilterItemsDatasList.get(position).getIcon());

		viewHolder.checkRequestTextView.setTag(position);
		viewHolder.checkRequestTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int pos = Integer.parseInt(v.getTag().toString());
				mStartActivity.startActivity("RS@" +mServicesFilterItemsDatasList.get(pos).getId());

			}
		});
		
		viewHolder.checkRequestTitleTextView.setTag(position);
		viewHolder.checkRequestTitleTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int pos = Integer.parseInt(v.getTag().toString());
				mStartActivity.startActivity("CR@" + mServicesFilterItemsDatasList.get(pos).getId());

			}
		});
		
		return convertView;
	}
	
	static class ViewHolder {

		public ImageView serviceIconIV;
		public TextView titleTV;
		public TextView mobileNumberTV;
		public TextView descTextView;
		public TextView checkRequestTitleTextView;
		public TextView checkRequestTextView;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return mFilter;
	}
	
	private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<ServicesItemsData> list = mServicesItemsDatasList;

            int count = list.size();
            final List<ServicesItemsData> nlist = new ArrayList<ServicesItemsData>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getTitle();
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
            mServicesFilterItemsDatasList = (List<ServicesItemsData>) results.values;
            notifyDataSetChanged();
        }

    }

	public void startActivity(int arg2) {
		mStartActivity.startActivity(mServicesFilterItemsDatasList.get(arg2).getId());
	}
}
