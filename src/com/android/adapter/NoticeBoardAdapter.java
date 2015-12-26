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

import com.android.adapter.NoticeBoardAdapter.SolventViewHolders;
import com.android.spideycity.R;
import com.bean.NoticeBoardItemsData;
import com.bean.ServicesItemsData;

public class NoticeBoardAdapter extends RecyclerView.Adapter<SolventViewHolders> {

	private List<NoticeBoardItemsData> mNoticeBoardItemsDatasList;
	private StartActivity mStartActivity;

	public static interface StartActivity{
		void startActivity(String serviceId);
	}

	public NoticeBoardAdapter(StartActivity startActivity, List<NoticeBoardItemsData> noticeBoardItemsDatasList) {
		mNoticeBoardItemsDatasList = noticeBoardItemsDatasList;
		mStartActivity = startActivity;
	}

	@Override
	public SolventViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

		View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_noticeboard_layout, null);
		SolventViewHolders rcv = new SolventViewHolders(layoutView);
		return rcv;
	}

	@Override
	public void onBindViewHolder(SolventViewHolders holder, int position) {
//		holder.noticeboardIconIV.setText(mNoticeBoardItemsDatasList.get(position).getMobile());
		holder.noticeboardTitleTV.setText(mNoticeBoardItemsDatasList.get(position).getTitle());
		holder.noticeboardDescTV.setText(mNoticeBoardItemsDatasList.get(position).getDesc());
		holder.noticeboardDateTV.setText(mNoticeBoardItemsDatasList.get(position).getReleaseYear());
		holder.noticeboardCommentTV.setText(mNoticeBoardItemsDatasList.get(position).getGenre());

	}

	@Override
	public int getItemCount() {
		return mNoticeBoardItemsDatasList.size();
	}
	
	class SolventViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

		public ImageView noticeboardIconIV;
		public TextView noticeboardTitleTV;
		public TextView noticeboardDescTV;
		public TextView noticeboardDateTV;
		public TextView noticeboardCommentTV;

		public SolventViewHolders(View convertView) {
			super(convertView);
			itemView.setOnClickListener(this);
			noticeboardIconIV = (ImageView)convertView.findViewById(R.id.iv_noticeboardicon);
			noticeboardTitleTV = (TextView)convertView.findViewById(R.id.tv_noticeboardtitle);
			noticeboardDescTV = (TextView)convertView.findViewById(R.id.tv_noticeboarddescription);
			noticeboardDateTV = (TextView)convertView.findViewById(R.id.tv_noticeboarddate);
			noticeboardCommentTV = (TextView)convertView.findViewById(R.id.tv_noticeboardcomment);
		}

		@Override
		public void onClick(View view) {
			Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
		}
	}
}
