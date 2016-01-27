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

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.MediaColumns;
import android.support.v4.content.CursorLoader;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.adapter.SpidyPickDetailAdapter;
import com.android.cityspidey.R;
import com.bean.CommentSave;
import com.bean.Comments;
import com.bean.GroupDetailData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.DialogController;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;
import com.utils.Utils;

public class GroupDetailActivity extends BaseActivity{
	private String imageUrl;
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_detail_layout);
		listView = (ListView)findViewById(R.id.listview);
		TextView titleTV = (TextView)findViewById(R.id.tv_title);
		TextView plusTV = (TextView)findViewById(R.id.tv_plus);
		plusTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setText(getResources().getString(R.string.groups));
		titleTV.setTextColor(getResources().getColor(R.color.black));
		titleTV.setBackgroundResource(R.color.groupcolor);
		plusTV.setBackgroundResource(R.color.groupcolor);

		plusTV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*Intent intent = new Intent(GroupsActivity.this, CreateGroupsActivity.class);
				startActivity(intent);*/
				if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
					createGroupDialog();
				}else{
					DialogController.login(GroupDetailActivity.this);
				}
			}
		});
		
		if(PreferenceHelper.getSingleInstance(getApplicationContext()).getBoolean(PreferenceKey.IS_LOGIN)){
			mAQuery.id(R.id.iv_profile_picture).image(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO));
		}
		
		loadGroupDetail();
	}

	private void loadGroupDetail() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.GROUPS_DETAIL);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("url", getIntent().getStringExtra("url"));
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

	public void response(GroupDetailData groupDetailData) {
		this.groupDetailData = groupDetailData;
		View headerView = getHeaderView();
		View footerView = getFooterView();
		if(groupDetailData.getCommentList().size() > 0){
			spidyPickDetailAdapter = new SpidyPickDetailAdapter
					(getLayoutInflater(), groupDetailData.getCommentList(), mAQuery);
			listView.setAdapter(spidyPickDetailAdapter);
		}
		listView.addHeaderView(headerView);
		listView.addFooterView(footerView);
	}
	
	
	private SpidyPickDetailAdapter spidyPickDetailAdapter;
	private GroupDetailData groupDetailData;

	private View getFooterView() {
		View view = getLayoutInflater().inflate(R.layout.inflate_write_comment, null, false);
		final EditText commentET = (EditText)view.findViewById(R.id.et_comment);
		TextView resetBTN = (TextView)view.findViewById(R.id.btn_reset);
		TextView submitBTN = (TextView)view.findViewById(R.id.btn_submit);
		resetBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				commentET.setText("");
			}
		});
		
		submitBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Comments comments = new Comments();
				comments.setCommentby(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.NAME));
				comments.setDescrption(commentET.getText().toString());
				comments.setProfilephoto(PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.PHOTO));
				groupDetailData.getCommentList().add(comments);
				if(spidyPickDetailAdapter != null){
					spidyPickDetailAdapter = new SpidyPickDetailAdapter(getLayoutInflater(), groupDetailData.getCommentList(), mAQuery);
					listView.setAdapter(spidyPickDetailAdapter);
				}else{
					spidyPickDetailAdapter.notifyDataSetChanged();
				}
				listView.setSelection(groupDetailData.getCommentList().size()-1);
				comment(commentET.getText().toString());
				commentET.setText("");
			}
		});
		return view;
	}

	private View getHeaderView() {
		View view = getLayoutInflater().inflate(R.layout.inflate_ngroup_detail_header, null, false);
		TextView groupTitleTV = (TextView)view.findViewById(R.id.tv_group_title);
		TextView createdDAteTV = (TextView)view.findViewById(R.id.tv_createddate);
		TextView groupAdminTV = (TextView)view.findViewById(R.id.tv_groupadmin);
		ImageView groupDetailIV = (ImageView)view.findViewById(R.id.iv_group_detail);
		mAQuery.id(groupDetailIV).image(groupDetailData.getGroupDetailItemsDataList().get(0).getImage());
		TextView descTV = (TextView)view.findViewById(R.id.tv_desc);
		groupTitleTV.setText(groupDetailData.getGroupDetailItemsDataList().get(0).getTitle());
		createdDAteTV.setText("Created: " + Utils.getTimeRemaining(groupDetailData.getGroupDetailItemsDataList().get(0).getCreatedDate()));
		groupAdminTV.setText("Group Admin: " + groupDetailData.getGroupDetailItemsDataList().get(0).getCreatedby());
		descTV.setText(groupDetailData.getGroupDetailItemsDataList().get(0).getDesc());
		
		return view;
	}
	
	private void comment(String message) {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.COMMENT);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		BasicNameValuePair valuePair = new BasicNameValuePair("articleid", groupDetailData.getGroupDetailItemsDataList().get(0).getId());
		list.add(valuePair);
		valuePair = new BasicNameValuePair("content_type", "2");//2 for group
		list.add(valuePair);
		valuePair = new BasicNameValuePair("userid", PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.USER_ID));
		list.add(valuePair);
		valuePair = new BasicNameValuePair("task", "addComments");
		list.add(valuePair);
		valuePair = new BasicNameValuePair("message", message);
		list.add(valuePair);
		
		request.setCallingClassObject(this);
		request.setNamevaluepair(list);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
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
				DialogController.selectImage(GroupDetailActivity.this);
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
	
	private void saveImage(Bitmap bitmap){
		imageUrl = Environment.getExternalStorageDirectory() + "/image.jpeg";
		FileOutputStream out = null;
		try { 
		    out = new FileOutputStream(imageUrl);
		    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
		    // PNG is a lossless format, the compression factor (100) is ignored 
		} catch (Exception e) {
		    e.printStackTrace();
		} finally { 
		    try { 
		        if (out != null) {
		            out.close();
		        } 
		    } catch (IOException e) {
		        e.printStackTrace();
		    } 
		} 
	}

	public void response(CommentSave commentSave) {
		
	}
}
