package com.fragment.item;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class DrawerAdapter extends BaseAdapter {


	private Activity activity;


	private List<items> itmList;
	private ImageView imageView;
	private TextView textView;

	public DrawerAdapter(Activity activity,SlideDrawerItems sldItm) {
		this.activity = activity;
		this.itmList = sldItm.getItem();
	}


	@Override
	public int getCount() {

		return itmList.size();
	}

	@Override
	public Object getItem(int position) {

		return itmList.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

//		if (convertView == null) {
//
//			LayoutInflater inflater = activity.getLayoutInflater();
//			convertView = inflater.inflate(R.layout.drawer_adapter, null,false);
//		}

//		textView = (TextView)convertView.findViewById(R.id.drawerName);
//		imageView = 	(ImageView)convertView.findViewById(R.id.homescreenIcon);
//
//		textView.setText(itmList.get(position).getItemtext());
//		if(itmList.get(position).isClicked()){
//			
//			textView.setTextColor(activity.getResources().getColor(R.color.drwr_text_color));
//			imageView.setBackgroundResource(itmList.get(position).getItemImageClicked());
//		}else{
//			textView.setTextColor(activity.getResources().getColor(R.color.black));
//			imageView.setBackgroundResource(itmList.get(position).getItemImageNonClicked());
//		}

		return convertView;
	}

}
