package com.android.itemsActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.cityspidey.R;
import com.bean.OpinionPostAnswerPollsDetailsData;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;
import com.utils.Utils;

public class OpinionPollsPostAnswerResponseActivity  extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opinion_post_answer_detail_layout);
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.openion_polls));
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.opinioncolor);
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_opinionpolls_hint));

		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO));
		}

		OpinionPostAnswerPollsDetailsData opinionPostAnswerPollsDetailsData = (OpinionPostAnswerPollsDetailsData) getIntent().getSerializableExtra("data");
		TextView tv_opiniontitle = (TextView)findViewById(R.id.tv_opiniontitle);
		TextView tv_postandenddate = (TextView)findViewById(R.id.tv_postandenddate);
		LinearLayout voteOptionLL = (LinearLayout)findViewById(R.id.ll_vote_options);

		tv_opiniontitle.setText(opinionPostAnswerPollsDetailsData.getMsg());
		tv_postandenddate.setText("Posted " + Utils.getTimeRemaining(getIntent().getStringExtra("postdate"))+
				"/Voting ends\n " + Utils.formatDate(getIntent().getStringExtra("enddate")));
		int size = opinionPostAnswerPollsDetailsData.getOptionsList().size();
		for (int i = 0; i < size; i++) {
			View view = getLayoutInflater().inflate(R.layout.inflate_vote_count, null, false);
			TextView vote1 = (TextView)view.findViewById(R.id.tv_option);
			TextView vote2 = (TextView)view.findViewById(R.id.tv_option_count);
			vote1.setText(opinionPostAnswerPollsDetailsData.getOptionsList().get(i));
			vote2.setText(opinionPostAnswerPollsDetailsData.getTotalCountList().get(i));
			//vote1.setBackgroundColor(Color.parseColor(opinionPostAnswerPollsDetailsData.getColorList().get(i)));
			voteOptionLL.addView(view);

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
