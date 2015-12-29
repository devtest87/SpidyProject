package com.android.adapter;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.adapter.GroupListAdapter.SolventViewHolders;
import com.android.spideycity.R;
import com.androidquery.AQuery;
import com.bean.GroupItemsData;
import com.bean.ServicesItemsData;

public class GroupListAdapter extends RecyclerView.Adapter<SolventViewHolders> {

	private List<GroupItemsData> mGroupItemsDataList;
	private StartActivity mStartActivity;
	private AQuery mAQuery;

	public static interface StartActivity{
		void startActivity(String serviceId);
	}

	public GroupListAdapter(List<GroupItemsData> groupItemsDataList, AQuery aQuery) {
		mGroupItemsDataList = groupItemsDataList;
		mAQuery = aQuery;
	}

	@Override
	public SolventViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

		View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_group_layout, null);
		SolventViewHolders rcv = new SolventViewHolders(layoutView);
		return rcv;
	}

	@Override
	public void onBindViewHolder(SolventViewHolders holder, int position) {
//		holder.mobileNumberTV.setText(mGroupItemsDataList.get(position).getMobile());
//		holder.descTextView.setText(mGroupItemsDataList.get(position).getDesc());
		mAQuery.id(holder.groupiconIV).image(mGroupItemsDataList.get(position).getImage());

	}

	@Override
	public int getItemCount() {
		return mGroupItemsDataList.size();
	}
	
	class SolventViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

		public ImageView groupiconIV;
//		public TextView mobileNumberTV;
//		public TextView descTextView;
//		public TextView checkRequestTextView;

		public SolventViewHolders(View convertView) {
			super(convertView);
			itemView.setOnClickListener(this);
			groupiconIV = (ImageView)convertView.findViewById(R.id.iv_groupicon);
//			mobileNumberTV = (TextView)convertView.findViewById(R.id.tv_mobilenumber);
//			descTextView = (TextView)convertView.findViewById(R.id.tv_description);
//			checkRequestTextView = (TextView)convertView.findViewById(R.id.tv_checkrequest_status);
		}

		@Override
		public void onClick(View view) {
			Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
		}
	}
}
