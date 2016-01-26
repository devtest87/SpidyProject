package com.android.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cityspidey.R;
import com.androidquery.AQuery;
import com.bean.Comments;

public class SpidyPickDetailAdapter extends BaseAdapter{

	private List<Comments> mCommentList;
	private LayoutInflater mLayoutInflater;
	private AQuery mAQuery;
	
	public SpidyPickDetailAdapter(LayoutInflater layoutInflater, List<Comments> commentList, AQuery aQuery) {
		mCommentList = commentList;
		mLayoutInflater = layoutInflater;
		mAQuery = aQuery;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCommentList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mCommentList.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.row_comment, null, false);
			viewHolder.createrIV = (ImageView)convertView.findViewById(R.id.iv_creater);
			viewHolder.createrTV = (TextView)convertView.findViewById(R.id.tv_creater);
			viewHolder.commentTV = (TextView)convertView.findViewById(R.id.tv_comment);
			 
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.createrTV.setText(mCommentList.get(position).getCommentby());
		viewHolder.commentTV.setText(mCommentList.get(position).getDescrption());
		mAQuery.id(viewHolder.createrIV).image(mCommentList.get(position).getProfilephoto());
		
		return convertView;
	}

	static class ViewHolder{
		TextView commentTV;
		TextView createrTV;
		ImageView createrIV ;
	}
}
