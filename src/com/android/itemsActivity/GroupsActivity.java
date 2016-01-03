package com.android.itemsActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.adapter.GridGroupListAdapter;
import com.android.adapter.GridGroupListAdapter.StartActivity;
import com.android.spideycity.R;
import com.bean.GroupsData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.SpacesItemDecoration;

public class GroupsActivity extends BaseActivity implements StartActivity{
	//private ExecutorService mExecutorService;

	private GridView mGridView;
	private GridGroupListAdapter mGridGroupListAdapter;

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
		titleTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setBackgroundResource(R.color.groupcolor);
		
		final TextView trendingTV = (TextView)findViewById(R.id.tv_trending);
		final TextView latestTV = (TextView)findViewById(R.id.tv_latest);
		final TextView popularTV = (TextView)findViewById(R.id.tv_popular);
		
		trendingTV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				trendingTV.setTypeface(Typeface.DEFAULT_BOLD);
				latestTV.setTypeface(Typeface.DEFAULT);
				popularTV.setTypeface(Typeface.DEFAULT);
				trendingTV.setTextColor(getResources().getColor(R.color.white));
				latestTV.setTextColor(getResources().getColor(R.color.white));
				popularTV.setTextColor(getResources().getColor(R.color.white));
			}
		});
		latestTV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				trendingTV.setTypeface(Typeface.DEFAULT);
				latestTV.setTypeface(Typeface.DEFAULT_BOLD);
				popularTV.setTypeface(Typeface.DEFAULT);
				trendingTV.setTextColor(getResources().getColor(R.color.white));
				latestTV.setTextColor(getResources().getColor(R.color.white));
				popularTV.setTextColor(getResources().getColor(R.color.white));
			}
		});
		popularTV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				trendingTV.setTypeface(Typeface.DEFAULT);
				latestTV.setTypeface(Typeface.DEFAULT);
				popularTV.setTypeface(Typeface.DEFAULT_BOLD);
				trendingTV.setTextColor(getResources().getColor(R.color.white));
				latestTV.setTextColor(getResources().getColor(R.color.white));
				popularTV.setTextColor(getResources().getColor(R.color.white));
			}
		});

		/*mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_group);



		staggeredGridLayoutManagerVertical =
				new StaggeredGridLayoutManager(
						2, //The number of Columns in the grid
						LinearLayoutManager.VERTICAL);

		SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(30);
		mRecyclerView.addItemDecoration(spacesItemDecoration);*/
		
		mGridView = (GridView)findViewById(R.id.recyclerview_group);
		
		
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mGridGroupListAdapter.startActivity(arg2);
			}
		});

		searchET.addTextChangedListener(new TextWatcher() {

		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        System.out.println("Text ["+s+"]");
		        if(mGridGroupListAdapter!=null)
		        	mGridGroupListAdapter.getFilter().filter(s.toString());                           
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count,
		            int after) {

		    }

		    @Override
		    public void afterTextChanged(Editable s) {
		    }
		});


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
		/*mGroupListAdapter = new GroupListAdapter(groupsData.getGroupItemsDataList(), mAQuery);
		mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
		mRecyclerView.setAdapter(mGroupListAdapter);*/
		mGridGroupListAdapter = new GridGroupListAdapter(getLayoutInflater(), this, groupsData.getGroupItemsDataList(), mAQuery);
		mGridView.setAdapter(mGridGroupListAdapter);
	}

	@Override
	public void startActivity(String url) {
		Intent intent = new Intent(this, GroupDetailActivity.class);
		intent.putExtra("url", url);
		startActivity(intent);
	}

}
