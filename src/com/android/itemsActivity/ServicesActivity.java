package com.android.itemsActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.adapter.ServicesAdapter;
import com.android.adapter.ServicesAdapter.StartActivity;
import com.android.spideycity.R;
import com.bean.RequestBean;
import com.bean.ServicesData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;

public class ServicesActivity extends BaseActivity  implements StartActivity{
	//private ExecutorService mExecutorService;
	private RecyclerView mRecyclerView;
	private ServicesAdapter mServicesAdapter;
	
    private StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_layout);
		//		mExecutorService = Executors.newFixedThreadPool(1);
		//		mExecutorService.execute(new loadRWAs());
		
		
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_services_request_hint));
		titleTV.setText(getResources().getString(R.string.servvices));
		titleTV.setTextColor(getResources().getColor(R.color.white));
		titleTV.setBackgroundResource(R.color.servicecolor);
		
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
		request.setNetworkRequestName(NetworkRequestName.SERVICES);
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

	public void response(ServicesData servicesData) {
		
		mServicesAdapter = new ServicesAdapter(this, servicesData.getServicesItemsDatasList(), mAQuery);
		mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
		mRecyclerView.setAdapter(mServicesAdapter);
	}

	@Override
	public void startActivity(String serviceId) {
		Intent intent = new Intent(this, RequestServicesActivity.class);
		intent.putExtra("id", serviceId);
		startActivity(intent);
	}
	
	public class SpacesItemDecoration extends RecyclerView.ItemDecoration { 
		  private int space;
		 
		  public SpacesItemDecoration(int space) {
		    this.space = space;
		  } 
		 
		  @Override 
		  public void getItemOffsets(Rect outRect, View view, 
		      RecyclerView parent, RecyclerView.State state) {
		    outRect.left = space;
		    outRect.right = space;
		    outRect.bottom = space;
		 
		    // Add top margin only for the first item to avoid double space between items 
		    if(parent.getChildLayoutPosition(view) == 0)
		        outRect.top = space;
		  } 
		} 

}
