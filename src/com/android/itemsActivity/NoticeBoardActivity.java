package com.android.itemsActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.android.adapter.GridNoticeBoardAdapter;
import com.android.adapter.GridNoticeBoardAdapter.StartActivity;
import com.android.cityspidey.R;
import com.bean.NoticeBoardData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.AppConstant;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;

public class NoticeBoardActivity extends BaseActivity implements StartActivity{
	//private ExecutorService mExecutorService;
//	private RecyclerView mRecyclerView;
//	private NoticeBoardAdapter mNoticeBoardAdapter;
//
//	private StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;
	
	private GridView mGridView;
	private GridNoticeBoardAdapter mGridNoticeBoardAdapter;

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
				mGridNoticeBoardAdapter.startActivity(arg2);
			}
		});
//        searchET.addTextChangedListener(new TextWatcher() {
//
//		    @Override
//		    public void onTextChanged(CharSequence s, int start, int before, int count) {
//		        System.out.println("Text ["+s+"]");
//		        if(mGridNoticeBoardAdapter!=null)
//		        	mGridNoticeBoardAdapter.getFilter().filter(s.toString());                           
//		    }
//
//		    @Override
//		    public void beforeTextChanged(CharSequence s, int start, int count,
//		            int after) {
//
//		    }
//
//		    @Override
//		    public void afterTextChanged(Editable s) {
//		    }
//		});
        
		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO), true, true, 0, R.drawable.profile);
		}

		loadRWAs();
	}

	private void loadRWAs() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.NOTICEBOARDS);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("rwa_id", PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.RWAS_ID));
		list.add(valuePair);
		request.setCallingClassObject(this);
		request.setNamevaluepair(list);
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
		/*mNoticeBoardAdapter = new NoticeBoardAdapter(this, noticeBoardData.getNoticeBoardItemsDatasList(), mAQuery);
		mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
		mRecyclerView.setAdapter(mNoticeBoardAdapter);*/
		mGridNoticeBoardAdapter = new GridNoticeBoardAdapter(this, getLayoutInflater(), this, noticeBoardData.getNoticeBoardItemsDatasList(), mAQuery);
		mGridView.setAdapter(mGridNoticeBoardAdapter);
	}
	
	@Override
	public void startActivity(String url) {
		Intent intent = new Intent(this, NoticeBoardDetailActivity.class);
		intent.putExtra("url", url);
		startActivityForResult(intent, AppConstant.REQUEST_GROUP_DETAIL_ACTIVITY_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == AppConstant.REQUEST_GROUP_DETAIL_ACTIVITY_CODE && resultCode == RESULT_OK){
			Intent intent = new Intent();
			setResult(Activity.RESULT_OK, intent);
			finish();
		}
	}

}
