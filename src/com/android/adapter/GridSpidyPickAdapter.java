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
import com.bean.NoticeBoardItemsData;
import com.bean.SpidyPickItemsData;

public class GridSpidyPickAdapter  extends BaseAdapter implements Filterable{

	private List<SpidyPickItemsData> mSpidyPickItemsDatasList;
	private List<SpidyPickItemsData> mSpidyPickFilterItemsDatasList;
	private StartActivity mStartActivity;
	private AQuery mAQuery;
	private LayoutInflater mLayoutInflater;
	private ItemFilter mFilter = new ItemFilter();

	public static interface StartActivity{
		void startActivity(String serviceId);
	}

	public GridSpidyPickAdapter(LayoutInflater layoutInflater, StartActivity startActivity, List<SpidyPickItemsData> spidyPickItemsDatasList, AQuery aQuery) {
		mSpidyPickItemsDatasList = spidyPickItemsDatasList;
		mSpidyPickFilterItemsDatasList = spidyPickItemsDatasList;
		mStartActivity = startActivity;
		mAQuery = aQuery;
		mLayoutInflater = layoutInflater;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mSpidyPickFilterItemsDatasList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mSpidyPickFilterItemsDatasList.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.row_spidypick_layout, null, false);
			viewHolder.noticeboardIconIV = (ImageView)convertView.findViewById(R.id.iv_noticeboardicon);
			viewHolder.noticeboardTitleTV = (TextView)convertView.findViewById(R.id.tv_noticeboardtitle);
			viewHolder.noticeboardCommentTV = (TextView)convertView.findViewById(R.id.tv_noticeboardcomment);
			viewHolder.noticeboardDescTV = (TextView)convertView.findViewById(R.id.tv_noticeboarddescription);
			viewHolder.noticeboardDateTV = (TextView)convertView.findViewById(R.id.tv_noticeboarddate);
			 
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.noticeboardTitleTV.setText(mSpidyPickFilterItemsDatasList.get(position).getTitle());
		viewHolder.noticeboardDescTV.setText(mSpidyPickFilterItemsDatasList.get(position).getDesc());
		viewHolder.noticeboardDateTV.setText(mSpidyPickFilterItemsDatasList.get(position).getReleaseYear());
		
		return convertView;
	}
	
	static class ViewHolder {

		public ImageView noticeboardIconIV;
		public TextView noticeboardTitleTV;
		public TextView noticeboardDescTV;
		public TextView noticeboardCommentTV;
		public TextView noticeboardDateTV;
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

            final List<SpidyPickItemsData> list = mSpidyPickItemsDatasList;

            int count = list.size();
            final List<SpidyPickItemsData> nlist = new ArrayList<SpidyPickItemsData>(count);

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
            mSpidyPickFilterItemsDatasList = (List<SpidyPickItemsData>) results.values;
            notifyDataSetChanged();
        }

    }

	public void startActivity(int pos) {
		mStartActivity.startActivity(mSpidyPickFilterItemsDatasList.get(pos).getUrl());
	}
}
