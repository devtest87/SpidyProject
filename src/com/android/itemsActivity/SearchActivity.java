package com.android.itemsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.android.adapter.GridSpidyPickAdapter;
import com.android.adapter.GridSpidyPickAdapter.StartActivity;
import com.android.spideycity.R;
import com.bean.RequestBean;
import com.bean.SpidyPickData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;

public class SearchActivity extends BaseActivity implements StartActivity{
	//private ExecutorService mExecutorService;
//	private RecyclerView mRecyclerView;
//	private NoticeBoardAdapter mNoticeBoardAdapter;
//
//	private StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;
	
	private GridView mGridView;
	private GridSpidyPickAdapter mGridSpidyPickAdapter;

	//private ExecutorService mExecutorService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_layout);
		//		mExecutorService = Executors.newFixedThreadPool(1);
		//		mExecutorService.execute(new loadRWAs());
		
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		titleTV.setText(getResources().getString(R.string.spidey_pick));
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_groups_hint));
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.spideycolor);


		/*mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_service);



		staggeredGridLayoutManagerVertical =
				new StaggeredGridLayoutManager(
						2, //The number of Columns in the grid
						LinearLayoutManager.VERTICAL);
		
		SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(30);
        mRecyclerView.addItemDecoration(spacesItemDecoration);*/
		
		mGridView = (GridView)findViewById(R.id.recyclerview_service);
        
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mGridSpidyPickAdapter.startActivity(arg2);
			}
		});
        searchET.addTextChangedListener(new TextWatcher() {

		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        System.out.println("Text ["+s+"]");
		        if(mGridSpidyPickAdapter!=null)
		        	mGridSpidyPickAdapter.getFilter().filter(s.toString());                           
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count,
		            int after) {

		    }

		    @Override
		    public void afterTextChanged(Editable s) {
		    }
		});
        
        if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO));
		}

		loadRWAs();
	}

	private void loadRWAs() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.SPIDYPICKS);
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

	public void response(SpidyPickData spidyPickData) {
		/*mNoticeBoardAdapter = new NoticeBoardAdapter(this, noticeBoardData.getNoticeBoardItemsDatasList(), mAQuery);
		mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
		mRecyclerView.setAdapter(mNoticeBoardAdapter);*/
		mGridSpidyPickAdapter = new GridSpidyPickAdapter(getLayoutInflater(), this, spidyPickData.getSpidyPickItemsDatasL(), mAQuery);
		mGridView.setAdapter(mGridSpidyPickAdapter);
	}
	
	@Override
	public void startActivity(String url) {
		Intent intent = new Intent(this, SpidyPickDetailActivity.class);
		intent.putExtra("url", url);
		startActivity(intent);
	}

}