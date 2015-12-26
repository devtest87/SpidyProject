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

import com.android.adapter.ServicesAdapter.SolventViewHolders;
import com.android.spideycity.R;
import com.bean.ServicesItemsData;

public class ServicesAdapter extends RecyclerView.Adapter<SolventViewHolders> {

	private List<ServicesItemsData> mServicesItemsDatasList;
	private StartActivity mStartActivity;

	public static interface StartActivity{
		void startActivity(String serviceId);
	}

	public ServicesAdapter(StartActivity startActivity, List<ServicesItemsData> servicesItemsDatasList) {
		mServicesItemsDatasList = servicesItemsDatasList;
		mStartActivity = startActivity;
	}

	@Override
	public SolventViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

		View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_services, null);
		SolventViewHolders rcv = new SolventViewHolders(layoutView);
		return rcv;
	}

	@Override
	public void onBindViewHolder(SolventViewHolders holder, int position) {
		holder.mobileNumberTV.setText(mServicesItemsDatasList.get(position).getMobile());
		holder.descTextView.setText(mServicesItemsDatasList.get(position).getDesc());

		holder.checkRequestTextView.setTag(position);
		holder.checkRequestTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int pos = Integer.parseInt(v.getTag().toString());
				mStartActivity.startActivity(mServicesItemsDatasList.get(pos).getId());

			}
		});
	}

	@Override
	public int getItemCount() {
		return mServicesItemsDatasList.size();
	}
	
	class SolventViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

		public ImageView serviceIconIV;
		public TextView mobileNumberTV;
		public TextView descTextView;
		public TextView checkRequestTextView;

		public SolventViewHolders(View convertView) {
			super(convertView);
			itemView.setOnClickListener(this);
			serviceIconIV = (ImageView)convertView.findViewById(R.id.iv_services_icon);
			mobileNumberTV = (TextView)convertView.findViewById(R.id.tv_mobilenumber);
			descTextView = (TextView)convertView.findViewById(R.id.tv_description);
			checkRequestTextView = (TextView)convertView.findViewById(R.id.tv_checkrequest_status);
		}

		@Override
		public void onClick(View view) {
			Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
		}
	}
}
