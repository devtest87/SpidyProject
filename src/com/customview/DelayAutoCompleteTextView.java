package com.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.bean.RwaSearchItem;

public class DelayAutoCompleteTextView  extends AutoCompleteTextView {
 
    public DelayAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    } 
    /* Overriding this method and returning String type solves the problem */ 
    @Override 
    protected CharSequence convertSelectionToString(Object selectedItem) {
    	RwaSearchItem rwaSearchData = (RwaSearchItem) selectedItem;
 
        return rwaSearchData.getLabel();
    } 
} 