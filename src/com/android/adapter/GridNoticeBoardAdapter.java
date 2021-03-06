package com.android.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.cityspidey.R;
import com.androidquery.AQuery;
import com.bean.NoticeBoardItemsData;
import com.utils.Utils;

public class GridNoticeBoardAdapter  extends BaseAdapter implements Filterable{

	private List<NoticeBoardItemsData> mNoticeBoardItemsDatasList;
	private List<NoticeBoardItemsData> mNoticeBoardFilterItemsDatasList;
	private StartActivity mStartActivity;
	private AQuery mAQuery;
	private LayoutInflater mLayoutInflater;
	private ItemFilter mFilter = new ItemFilter();
	private Context mContext;

	public static interface StartActivity{
		void startActivity(String serviceId);
	}

	public GridNoticeBoardAdapter(Context context, LayoutInflater layoutInflater, StartActivity startActivity, List<NoticeBoardItemsData> noticeBoardItemsDatasList, AQuery aQuery) {
		mNoticeBoardItemsDatasList = noticeBoardItemsDatasList;
		mNoticeBoardFilterItemsDatasList = noticeBoardItemsDatasList;
		mStartActivity = startActivity;
		mAQuery = aQuery;
		mLayoutInflater = layoutInflater;
		mContext = context;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mNoticeBoardFilterItemsDatasList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mNoticeBoardFilterItemsDatasList.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.row_noticeboard_layout, null, false);
			viewHolder.noticeboardIconIV = (ImageView)convertView.findViewById(R.id.iv_noticeboardicon);
			viewHolder.noticeboardTitleTV = (TextView)convertView.findViewById(R.id.tv_noticeboardtitle);
			viewHolder.noticeboardCommentTV = (TextView)convertView.findViewById(R.id.tv_noticeboardcomment);
			viewHolder.noticeboardDescTV = (TextView)convertView.findViewById(R.id.tv_noticeboarddescription);
			viewHolder.noticeboardDateTV = (TextView)convertView.findViewById(R.id.tv_noticeboarddate);
			viewHolder.backgroundLL = (LinearLayout)convertView.findViewById(R.id.ll_parent);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		mAQuery.id(viewHolder.noticeboardIconIV).image(mNoticeBoardFilterItemsDatasList.get(position).getIcon());
		viewHolder.noticeboardTitleTV.setText(mNoticeBoardFilterItemsDatasList.get(position).getGenre());
		viewHolder.noticeboardDescTV.setText(mNoticeBoardFilterItemsDatasList.get(position).getTitle());
		viewHolder.noticeboardCommentTV.setText(mNoticeBoardFilterItemsDatasList.get(position).getComment());
		viewHolder.noticeboardDateTV.setText(Utils.getTimeRemaining(mNoticeBoardFilterItemsDatasList.get(position).getReleaseYear()));
		if(mNoticeBoardFilterItemsDatasList.get(position).getStatus() != null && 
				mNoticeBoardFilterItemsDatasList.get(position).getStatus().equalsIgnoreCase("Inactive")){
			viewHolder.backgroundLL.setBackgroundColor(mContext.getResources().getColor(R.color.search_background_color));
		}else{
			viewHolder.backgroundLL.setBackgroundColor(mContext.getResources().getColor(R.color.noticecolor));
		}
		
		return convertView;
	}
	
	static class ViewHolder {

		public ImageView noticeboardIconIV;
		public TextView noticeboardTitleTV;
		public TextView noticeboardDescTV;
		public TextView noticeboardCommentTV;
		public TextView noticeboardDateTV;
		public LinearLayout backgroundLL;
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

            final List<NoticeBoardItemsData> list = mNoticeBoardItemsDatasList;

            int count = list.size();
            final List<NoticeBoardItemsData> nlist = new ArrayList<NoticeBoardItemsData>(count);

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
            mNoticeBoardFilterItemsDatasList = (List<NoticeBoardItemsData>) results.values;
            notifyDataSetChanged();
        }

    }

	public void startActivity(int pos) {
		mStartActivity.startActivity(mNoticeBoardFilterItemsDatasList.get(pos).getUrl());
	}
}
