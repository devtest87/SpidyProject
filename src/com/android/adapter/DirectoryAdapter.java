package com.android.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cityspidey.R;
import com.androidquery.AQuery;
import com.bean.DiretoryItemsData;

public class DirectoryAdapter extends BaseAdapter implements Filterable{

	private List<DiretoryItemsData> mDiretoryItemsDatasList;
	private List<DiretoryItemsData> mDiretoryFilterItemsDatasList;
	private LayoutInflater mLayoutInflater;
	private StartActivity mStartActivity;
	private AQuery mAQuery;
	private ItemFilter mFilter = new ItemFilter();

	public static interface StartActivity{
		void startActivity(String serviceId);
	}

	public DirectoryAdapter(LayoutInflater layoutInflater, StartActivity startActivity, List<DiretoryItemsData> rwaItemsDatasList, AQuery aQuery) {
		mDiretoryItemsDatasList = rwaItemsDatasList;
		mDiretoryFilterItemsDatasList = rwaItemsDatasList;
		mStartActivity = startActivity;
		mLayoutInflater = layoutInflater;
		mAQuery = aQuery;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDiretoryFilterItemsDatasList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDiretoryFilterItemsDatasList.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.row_directory_layout, null, false);
			viewHolder.houseNoTV = (TextView)convertView.findViewById(R.id.tv_directory_houseno);
			viewHolder.nameTV = (TextView)convertView.findViewById(R.id.tv_directory_name);
			viewHolder.mobileNoTV = (TextView)convertView.findViewById(R.id.tv_directory_mobileno);
			viewHolder.landlineNoTV = (TextView)convertView.findViewById(R.id.tv_directory_landlineno);
			viewHolder.extnoTV = (TextView)convertView.findViewById(R.id.tv_directory_extno);
			viewHolder.mobileNoIV = (ImageView)convertView.findViewById(R.id.iv_mobileicon);
			viewHolder.landlineNoIV = (ImageView)convertView.findViewById(R.id.iv_directory_landlineno);
			viewHolder.extnoIV = (ImageView)convertView.findViewById(R.id.iv_directory_extno);

			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.houseNoTV.setText("" + mDiretoryFilterItemsDatasList.get(position).getHouseno());
		viewHolder.nameTV.setText("" + mDiretoryFilterItemsDatasList.get(position).getTitle());
		viewHolder.mobileNoTV.setText("" + mDiretoryFilterItemsDatasList.get(position).getMobile());
		viewHolder.landlineNoTV.setText("" + mDiretoryFilterItemsDatasList.get(position).getLandline());
		viewHolder.extnoTV.setText("" + mDiretoryFilterItemsDatasList.get(position).getExtno());
		
		mAQuery.id(viewHolder.mobileNoIV).image(mDiretoryFilterItemsDatasList.get(position).getMobile_icon());
		mAQuery.id(viewHolder.landlineNoIV).image(mDiretoryFilterItemsDatasList.get(position).getLandline_icon());
		mAQuery.id(viewHolder.extnoIV).image(mDiretoryFilterItemsDatasList.get(position).getExtno_icon());

		return convertView;
	}

	static class ViewHolder{
		TextView houseNoTV;
		TextView nameTV;
		TextView mobileNoTV;
		TextView landlineNoTV;
		TextView extnoTV;
		ImageView mobileNoIV;
		ImageView landlineNoIV;
		ImageView extnoIV;
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

			final List<DiretoryItemsData> list = mDiretoryItemsDatasList;

			int count = list.size();
			final List<DiretoryItemsData> nlist = new ArrayList<DiretoryItemsData>(count);

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
			mDiretoryFilterItemsDatasList = (List<DiretoryItemsData>) results.values;
			notifyDataSetChanged();
		}

	}

	public void startActivity(int arg2) {
	}
}
