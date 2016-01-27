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
import com.bean.RWAItemsData;

public class RWAsAdapter extends BaseAdapter implements Filterable{

	private List<RWAItemsData> mRwaItemsDatasList;
	private List<RWAItemsData> mRwaFiterItemsDatasList;
	private LayoutInflater mLayoutInflater;
	private StartActivity mStartActivity;
	private AQuery mAQuery;
	private ItemFilter mFilter = new ItemFilter();
	
	public static interface StartActivity{
		void startActivity(String serviceId);
	}
	
	public RWAsAdapter(LayoutInflater layoutInflater, StartActivity startActivity, List<RWAItemsData> rwaItemsDatasList, AQuery aQuery) {
		mRwaItemsDatasList = rwaItemsDatasList;
		mRwaFiterItemsDatasList = rwaItemsDatasList;
		mStartActivity = startActivity;
		mLayoutInflater = layoutInflater;
		mAQuery = aQuery;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mRwaFiterItemsDatasList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mRwaFiterItemsDatasList.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.row_rwa_layout, null, false);
			viewHolder.rwaIV = (ImageView)convertView.findViewById(R.id.iv_apartments);
			viewHolder.rwaAdressTV = (TextView)convertView.findViewById(R.id.tv_rwa_address);
			viewHolder.rwaTitleTV = (TextView)convertView.findViewById(R.id.tv_rwa_title);
			viewHolder.rwaDescTV = (TextView)convertView.findViewById(R.id.tv_rwa_desc);
			viewHolder.rwaCityTV = (TextView)convertView.findViewById(R.id.tv_rwa_city);
			 
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.rwaCityTV.setText(mRwaFiterItemsDatasList.get(position).getCity());
		viewHolder.rwaAdressTV.setText(mRwaFiterItemsDatasList.get(position).getAdr());
		viewHolder.rwaTitleTV.setText(mRwaFiterItemsDatasList.get(position).getTitle());
		viewHolder.rwaDescTV.setText(mRwaFiterItemsDatasList.get(position).getDesc());
		mAQuery.id(viewHolder.rwaIV).image(mRwaFiterItemsDatasList.get(position).getImage());
		
		return convertView;
	}

	static class ViewHolder{
		TextView rwaCityTV;
		TextView rwaAdressTV;
		TextView rwaTitleTV;
		TextView rwaDescTV;
		ImageView rwaIV ;
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

            final List<RWAItemsData> list = mRwaItemsDatasList;

            int count = list.size();
            final List<RWAItemsData> nlist = new ArrayList<RWAItemsData>(count);

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
            mRwaFiterItemsDatasList = (List<RWAItemsData>) results.values;
            notifyDataSetChanged();
        }

    }

	public void startActivity(int arg2) {
		mStartActivity.startActivity(mRwaFiterItemsDatasList.get(arg2).getUrl() + "@" + mRwaFiterItemsDatasList.get(arg2).getFurl());
	}
}
