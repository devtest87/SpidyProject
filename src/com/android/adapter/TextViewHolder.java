package com.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.spideycity.R;

public class TextViewHolder  extends RecyclerView.ViewHolder { 
	  public TextView textView;
	  public TextViewHolder(View itemView) {
	    super(itemView);
	    textView = (TextView) itemView.findViewById(R.id.tv_bookingoptiontitle);
	  } 
	} 
