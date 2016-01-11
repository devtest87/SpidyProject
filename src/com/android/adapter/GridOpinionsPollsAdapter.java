package com.android.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.spideycity.R;
import com.androidquery.AQuery;
import com.bean.OpinionPollsItemsData;
import com.utils.Utils;

public class GridOpinionsPollsAdapter  extends BaseAdapter implements Filterable{

	private List<OpinionPollsItemsData> mOpinionPollsItemsDatasList;
	private List<OpinionPollsItemsData> mOpinionPollsFilterItemsDatasList;
	private StartActivity mStartActivity;
	private AQuery mAQuery;
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private ItemFilter mFilter = new ItemFilter();

	public static interface StartActivity{
		void startActivity(String serviceId);
	}

	public GridOpinionsPollsAdapter(Context context, LayoutInflater layoutInflater, StartActivity startActivity, List<OpinionPollsItemsData> OpinionPollsItemsDatasList, AQuery aQuery) {
		mOpinionPollsItemsDatasList = OpinionPollsItemsDatasList;
		mOpinionPollsFilterItemsDatasList = OpinionPollsItemsDatasList;
		mStartActivity = startActivity;
		mAQuery = aQuery;
		mLayoutInflater = layoutInflater;
		mContext = context;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mOpinionPollsFilterItemsDatasList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mOpinionPollsFilterItemsDatasList.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.row_opinionpolls_layout, null, false);
			viewHolder.opinionpollsIconIV = (ImageView)convertView.findViewById(R.id.iv_opinionpollsicon);
			viewHolder.opinionpollsTitleTV = (TextView)convertView.findViewById(R.id.tv_opinionpollstitle);
			viewHolder.opinionpollsPostedDateTV = (TextView)convertView.findViewById(R.id.tv_opinionpollsposteddate);
			viewHolder.opinionpollsVoteNowTV = (TextView)convertView.findViewById(R.id.tv_opinionpollsvotenow);
			viewHolder.opinionpollsVotingEndDateTV = (TextView)convertView.findViewById(R.id.tv_opinionpollsvotingenddate);
			 
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.opinionpollsVoteNowTV.setTag(position);
		mAQuery.id(viewHolder.opinionpollsIconIV).image(mOpinionPollsFilterItemsDatasList.get(position).getImage());
		viewHolder.opinionpollsTitleTV.setText(mOpinionPollsFilterItemsDatasList.get(position).getTitle());
		viewHolder.opinionpollsPostedDateTV.setText("Posted " + Utils.getTimeRemaining(mOpinionPollsFilterItemsDatasList.get(position).getStartPoll()));
		viewHolder.opinionpollsVotingEndDateTV.setText("Voting ends " + Utils.formatDate(mOpinionPollsFilterItemsDatasList.get(position).getEndPoll()));
		viewHolder.opinionpollsVoteNowTV.setTag(position);
		viewHolder.opinionpollsVoteNowTV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mStartActivity.startActivity("vote@@" + mOpinionPollsFilterItemsDatasList.get(Integer.parseInt(v.getTag().toString())).getUrl());
			}
		});
		
		return convertView;
	}
	
	static class ViewHolder {

		public ImageView opinionpollsIconIV;
		public TextView opinionpollsTitleTV;
		public TextView opinionpollsPostedDateTV;
		public TextView opinionpollsVoteNowTV;
		public TextView opinionpollsVotingEndDateTV;
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

            final List<OpinionPollsItemsData> list = mOpinionPollsItemsDatasList;

            int count = list.size();
            final List<OpinionPollsItemsData> nlist = new ArrayList<OpinionPollsItemsData>(count);

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
        	mOpinionPollsFilterItemsDatasList = (List<OpinionPollsItemsData>) results.values;
            notifyDataSetChanged();
        }

    }

	public void startActivity(int pos) {
		mStartActivity.startActivity("detail@@" + mOpinionPollsFilterItemsDatasList.get(pos).getUrl());
	}
}
