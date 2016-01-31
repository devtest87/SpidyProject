package com.android.itemsActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.MediaColumns;
import android.support.v4.content.CursorLoader;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.adapter.GridGroupListAdapter;
import com.android.adapter.GridGroupListAdapter.StartActivity;
import com.android.cityspidey.R;
import com.bean.CreateGroupDetailData;
import com.bean.GroupsData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.AppConstant;
import com.utils.DialogController;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;
import com.utils.Utils;

public class GroupsActivity extends BaseActivity implements StartActivity{
	//private ExecutorService mExecutorService;

	private GridView mGridView;
	private GridGroupListAdapter mGridGroupListAdapter;
	private String imageUrl;

	private StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_list_layout);
		//		mExecutorService = Executors.newFixedThreadPool(1);
		//		mExecutorService.execute(new loadRWAs());
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		TextView plusTV = (TextView)findViewById(R.id.tv_plus);
		plusTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setText(getResources().getString(R.string.groups));
		EditText searchET = (EditText)findViewById(R.id.et_search);
		searchET.setHint(getResources().getString(R.string.search_groups_hint));
		titleTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setBackgroundResource(R.color.groupcolor);
		plusTV.setBackgroundResource(R.color.groupcolor);

		final TextView trendingTV = (TextView)findViewById(R.id.tv_trending);
		final TextView latestTV = (TextView)findViewById(R.id.tv_latest);
		final TextView popularTV = (TextView)findViewById(R.id.tv_popular);

		plusTV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*Intent intent = new Intent(GroupsActivity.this, CreateGroupsActivity.class);
				startActivity(intent);*/
				if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
					createGroupDialog();
				}else{
					DialogController.login(GroupsActivity.this);
				}
			}
		});
		
		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO), true, true, 0, R.drawable.profile);
		}

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

	public void response(CreateGroupDetailData groupsData) {
		/*mGroupListAdapter = new GroupListAdapter(groupsData.getGroupItemsDataList(), mAQuery);
		mRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
		mRecyclerView.setAdapter(mGroupListAdapter);*/
	}

	@Override
	public void startActivity(String url) {
		Intent intent = new Intent(this, GroupDetailActivity.class);
		intent.putExtra("url", url);
		startActivityForResult(intent, AppConstant.REQUEST_GROUP_DETAIL_ACTIVITY_CODE);
	}
	
	


	private void createGroupDialog(){
		// custom dialog
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		dialog.setContentView(R.layout.dialog_create_group);
		dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.MATCH_PARENT;
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		RelativeLayout addImageRL = (RelativeLayout)dialog.findViewById(R.id.rl_photo);
		ImageView addImageIV = (ImageView)dialog.findViewById(R.id.iv_click_image);
		addIV = addImageIV;
		TextView cancleTV = (TextView)dialog.findViewById(R.id.iv_cancel_icon);
		RadioButton sameRWARB = (RadioButton)dialog.findViewById(R.id.radio_same_rwa);
		RadioButton neighbourhoodRB = (RadioButton)dialog.findViewById(R.id.radio_neighbourhood_rwa);
		RadioButton entreZoneRB = (RadioButton)dialog.findViewById(R.id.radio_entire_zones);
		final EditText groupNameET = (EditText)dialog.findViewById(R.id.et_groupname);
		final EditText groupDesceET = (EditText)dialog.findViewById(R.id.et_add_description);

		Button doneBTN = (Button)dialog.findViewById(R.id.btn_submit_request);

		addImageRL.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DialogController.selectImage(GroupsActivity.this);
			}
		});
		
		cancleTV.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.dismiss();
			}
		});

		doneBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				createGroup(groupNameET.getText().toString(), groupDesceET.getText().toString());
				dialog.dismiss();
			}
		});

		dialog.show();

	}

	protected void createGroup(String groupName, String groupDesc) {
		PreferenceHelper preferenceHelper = PreferenceHelper.getSingleInstance(this.getApplicationContext());
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.CREATE_GROUPS);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("user_id", preferenceHelper.getString(PreferenceKey.USER_ID));
		list.add(valuePair);

		valuePair = new BasicNameValuePair("group_name", groupName);
		list.add(valuePair);
		valuePair = new BasicNameValuePair("group_desc", groupDesc);
		list.add(valuePair);
		valuePair = new BasicNameValuePair("group_image", imageUrl);
		list.add(valuePair);
		request.setCallingClassObject(this);
		request.setNamevaluepair(list);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
	}

	private ImageView addIV;
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == AppConstant.REQUEST_GROUP_DETAIL_ACTIVITY_CODE && resultCode == RESULT_OK){
			Intent intent = new Intent();
			setResult(Activity.RESULT_OK, intent);
			finish();
		}else{
			imageUrl = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + "image.jpeg";
			if (resultCode == RESULT_OK) {
				if (requestCode == DialogController.REQUEST_CAMERA) {
					Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
					thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
					File destination = new File(Environment.getExternalStorageDirectory(),
							System.currentTimeMillis() + ".jpg");
					FileOutputStream fo;
					try {
						destination.createNewFile();
						fo = new FileOutputStream(destination);
						fo.write(bytes.toByteArray());
						fo.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					addIV.setImageBitmap(thumbnail);
					Utils.saveImage(imageUrl, thumbnail);
				} else if (requestCode == DialogController.SELECT_FILE) {
					Uri selectedImageUri = data.getData();
					String[] projection = { MediaColumns.DATA };
					CursorLoader cursorLoader = new CursorLoader(this,selectedImageUri, projection, null, null,
							null);
					Cursor cursor =cursorLoader.loadInBackground();
					int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
					cursor.moveToFirst();
					String selectedImagePath = cursor.getString(column_index);
					Bitmap bm;
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inJustDecodeBounds = true;
					BitmapFactory.decodeFile(selectedImagePath, options);
					final int REQUIRED_SIZE = 200;
					int scale = 1;
					while (options.outWidth / scale / 2 >= REQUIRED_SIZE
							&& options.outHeight / scale / 2 >= REQUIRED_SIZE)
						scale *= 2;
					options.inSampleSize = scale;
					options.inJustDecodeBounds = false;
					bm = BitmapFactory.decodeFile(selectedImagePath, options);
					addIV.setImageBitmap(bm);
					Utils.saveImage(imageUrl, bm);
				}
			}
		}

	}
}
