package com.android.itemsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.android.adapter.NoticeBoardAdapter;
import com.android.adapter.ServicesAdapter.StartActivity;
import com.android.spideycity.R;
import com.bean.NoticeBoardData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;

public class NoticeBoardActivity extends BaseActivity implements com.android.adapter.NoticeBoardAdapter.StartActivity{
	//private ExecutorService mExecutorService;
	private RecyclerView mRecyclerView;
	private NoticeBoardAdapter mNoticeBoardAdapter;

	private StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;
	//private ExecutorService mExecutorService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_layout);
		//		mExecutorService = Executors.newFixedThreadPool(1);
		//		mExecutorService.execute(new loadRWAs());
		
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.noticecolor);


		mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_service);



		staggeredGridLayoutManagerVertical =
				new StaggeredGridLayoutManager(
						2, //The number of Columns in the grid
						LinearLayoutManager.VERTICAL);

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
		mNoticeBoardAdapter = new NoticeBoardAdapter(this, noticeBoardData.getNoticeBoardItemsDatasList());
		mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
		mRecyclerView.setAdapter(mNoticeBoardAdapter);
	}
	
	@Override
	public void startActivity(String serviceId) {
	}

}
