package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.adapter.GridServiceAdapter;
import com.android.adapter.GridServiceAdapter.StartActivity;
import com.android.spideycity.R;
import com.bean.RequestBean;
import com.bean.RequestServicesData;
import com.bean.ServicesData;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;

public class ServicesActivity extends BaseActivity  implements StartActivity{
	//private ExecutorService mExecutorService;
//	private RecyclerView mRecyclerView;
//	private ServicesAdapter mServicesAdapter;
	private GridView mGridView;
	private GridServiceAdapter mGridServiceAdapter;
//	
//    private StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;
    
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
		
//		mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_service);
//		
//		
//
//        staggeredGridLayoutManagerVertical =
//                new StaggeredGridLayoutManager(
//                        2, //The number of Columns in the grid
//                        LinearLayoutManager.VERTICAL);
//        
//        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(30);
//        mRecyclerView.addItemDecoration(spacesItemDecoration);
		
		mGridView = (GridView)findViewById(R.id.recyclerview_service);
		
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mGridServiceAdapter.startActivity(arg2);
			}
		});
		
		searchET.addTextChangedListener(new TextWatcher() {

		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        System.out.println("Text ["+s+"]");
		        if(mGridServiceAdapter!=null)
		        mGridServiceAdapter.getFilter().filter(s.toString());                           
		    }

		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count,
		            int after) {

		    }

		    @Override
		    public void afterTextChanged(Editable s) {
		    }
		});
        
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
		
		/*mServicesAdapter = new ServicesAdapter(this, servicesData.getServicesItemsDatasList(), mAQuery);
		mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
		mRecyclerView.setAdapter(mServicesAdapter);*/
		
		mGridServiceAdapter = new GridServiceAdapter(getLayoutInflater(), this, servicesData.getServicesItemsDatasList(), mAQuery);
	    mGridView.setAdapter(mGridServiceAdapter);
	}

	@Override
	public void startActivity(String serviceId) {
		String[] id = serviceId.split("@");
		if(id[0].equals("CR")){
			checkRequestStatus(id[1]);
		}else if(id[0].equals("RS")){
			requestService(id[1]);
		}else{
			serviceDetail(id[1]);
		}
//		Intent intent = new Intent(this, RequestServicesActivity.class);
//		intent.putExtra("id", serviceId);
//		startActivity(intent);
	}

	private void serviceDetail(String id) {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.SERVICES_DETAIL);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("service_id", id);
		list.add(valuePair);
		request.setCallingClassObject(this);
		request.setNamevaluepair(list);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
	}

	private void checkRequestStatus(String id) {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.SERVICES_REQUEST_STATUS);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("service_id", id);
		list.add(valuePair);
		request.setCallingClassObject(this);
		request.setNamevaluepair(list);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
	}

	private void requestService(String id) {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.REQUEST_SERVICES);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("service_id", id);
		list.add(valuePair);
		request.setCallingClassObject(this);
		request.setNamevaluepair(list);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
	}

	public void response(RequestServicesData requestServicesData) {
		
	}
	
}
