package com.android.itemsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.adapter.NoticeBoardAdapter;
import com.android.spideycity.R;
import com.bean.NoticeBoardData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.SpacesItemDecoration;

public class NoticeBoardActivity extends BaseActivity implements com.android.adapter.NoticeBoardAdapter.StartActivity{
	//private ExecutorService mExecutorService;
	private RecyclerView mRecyclerView;
	private NoticeBoardAdapter mNoticeBoardAdapter;

	private StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;
	//private ExecutorService mExecutorService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticeboard_layout);
		//		mExecutorService = Executors.newFixedThreadPool(1);
		//		mExecutorService.execute(new loadRWAs());
		
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.notice_board));
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_noticeboard_hint));
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.noticecolor);


		mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_service);



		staggeredGridLayoutManagerVertical =
				new StaggeredGridLayoutManager(
						2, //The number of Columns in the grid
						LinearLayoutManager.VERTICAL);
		
		SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(30);
        mRecyclerView.addItemDecoration(spacesItemDecoration);

		loadRWAs();
	}

	private void loadRWAs() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.NOTICEBOARDS);
		request.setCallingClassObject(this);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
	}

	//	class loadRWAs implements Runnable {
	//		public void run() { 
	//			new NetworkCall()
	//		} 
	//	};


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

	public void response(NoticeBoardData noticeBoardData) {
		mNoticeBoardAdapter = new NoticeBoardAdapter(this, noticeBoardData.getNoticeBoardItemsDatasList(), mAQuery);
		mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
		mRecyclerView.setAdapter(mNoticeBoardAdapter);
	}
	
	@Override
	public void startActivity(String url) {
		Intent intent = new Intent(this, NoticeBoardDetailActivity.class);
		intent.putExtra("url", url);
		startActivity(intent);
	}

}
