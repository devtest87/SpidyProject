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

import com.android.spideycity.R;
import com.androidquery.AQuery;
import com.bean.GroupItemsData;

public class GridGroupListAdapter extends BaseAdapter implements Filterable {

	private List<GroupItemsData> mGroupItemsDataList;
	private List<GroupItemsData> mGrouFilterItemsDataList;
	private StartActivity mStartActivity;
	private LayoutInflater mLayoutInflater;
	private AQuery mAQuery;
	private ItemFilter mFilter = new ItemFilter();

	public static interface StartActivity{
		void startActivity(String serviceId);
	}

	public GridGroupListAdapter(LayoutInflater layoutInflater, StartActivity startActivity, List<GroupItemsData> groupItemsDataList, AQuery aQuery) {
		mGroupItemsDataList = groupItemsDataList;
		mGrouFilterItemsDataList = groupItemsDataList;
		mLayoutInflater = layoutInflater;
		mStartActivity = startActivity;
		mAQuery = aQuery;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mGrouFilterItemsDataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mGrouFilterItemsDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.row_group_layout, null, false);
			viewHolder.groupiconIV = (ImageView)convertView.findViewById(R.id.iv_groupicon);
			viewHolder.groupTitleTV = (TextView)convertView.findViewById(R.id.tv_grouptitle);
			viewHolder.groupMemberTV = (TextView)convertView.findViewById(R.id.tv_groupmember);
			 
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		//PrintLog.show(Log.ERROR, "", mGrouFilterItemsDataList.get(position).getImage());
		mAQuery.id(viewHolder.groupiconIV).image(mGrouFilterItemsDataList.get(position).getImage());
		viewHolder.groupTitleTV.setText(mGrouFilterItemsDataList.get(position).getTitle());
		return convertView;
	}
	
	static class ViewHolder {
		public ImageView groupiconIV;
		public TextView groupTitleTV;
		public TextView groupMemberTV;
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

            final List<GroupItemsData> list = mGroupItemsDataList;

            int count = list.size();
            final List<GroupItemsData> nlist = new ArrayList<GroupItemsData>(count);

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
            mGrouFilterItemsDataList = (List<GroupItemsData>) results.values;
            notifyDataSetChanged();
        }

    }

	public void startActivity(int arg2) {
		mStartActivity.startActivity(mGrouFilterItemsDataList.get(arg2).getUrl());
	}

}
