package com.android.itemsActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.adapter.GroupListAdapter;
import com.android.adapter.GroupListAdapter.StartActivity;
import com.android.spideycity.R;
import com.bean.GroupsData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.SpacesItemDecoration;

public class GroupsActivity extends BaseActivity{
	//private ExecutorService mExecutorService;

	private RecyclerView mRecyclerView;
	private GroupListAdapter mGroupListAdapter;

	private StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_list_layout);
		//		mExecutorService = Executors.newFixedThreadPool(1);
		//		mExecutorService.execute(new loadRWAs());
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.groups));
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_groups_hint));
		titleTV.setTextColor(getResources().getColor(R.color.gray));
		titleTV.setBackgroundResource(R.color.groupcolor);

		mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_group);



		staggeredGridLayoutManagerVertical =
				new StaggeredGridLayoutManager(
						2, //The number of Columns in the grid
						LinearLayoutManager.VERTICAL);

		SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(30);
		mRecyclerView.addItemDecoration(spacesItemDecoration);



		loadGroups();
	}

	private void loadGroups() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.GROUPS);
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

	public void response(GroupsData groupsData) {
		mGroupListAdapter = new GroupListAdapter(groupsData.getGroupItemsDataList(), mAQuery);
		mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
		mRecyclerView.setAdapter(mGroupListAdapter);
	}

}
