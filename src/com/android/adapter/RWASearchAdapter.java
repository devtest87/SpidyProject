package com.android.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.cityspidey.R;
import com.bean.RwaSearchItem;

public class RWASearchAdapter extends ArrayAdapter<RwaSearchItem>{

    private static final int MAX_RESULTS = 10;
    private Context mContext;
    private List<RwaSearchItem> resultList = new ArrayList<RwaSearchItem>();

    public RWASearchAdapter(Context context, List<RwaSearchItem> resultList) {
    	super(context, 0, resultList);
        mContext = context;
        this.resultList = resultList;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public RwaSearchItem getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.simple_drop_down, null, false);
        }
        ((TextView) convertView.findViewById(R.id.text1)).setText(getItem(position).getLabel());
        return convertView;
    }
}
