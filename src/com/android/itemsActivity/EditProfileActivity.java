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
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore.MediaColumns;
import android.support.v4.content.CursorLoader;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.adapter.RWASearchAdapter;
import com.android.cityspidey.R;
import com.bean.ProfileData;
import com.bean.RegisterData;
import com.bean.RequestBean;
import com.bean.RwaSearchData;
import com.network.NetworkCall;
import com.parser.MyParser;
import com.utils.DialogController;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.Utils;
import com.utils.PreferenceHelper.PreferenceKey;


public class EditProfileActivity extends Activity {

	private String mRwaId;
	private EditText dname;
	private EditText fname;
	private EditText lname;
	private EditText password;
	private EditText mnumber;
	private EditText lnumber;
	private EditText ymail;
	private EditText yhno;
	private EditText yaddrs;

	private RwaSearchData mRwaSearchData;
	private  InputMethodManager inputManager;

	private Button save;
	private Button revert;
	private ImageView uploadphotoBTN;
	private AutoCompleteTextView rwaNameAutoTV;
	private RWASearchAdapter mRwaSearchAdapter;
	private ProgressBar mProgressBar;
	private String mSelectedText = "";
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_profile);

		dname = (EditText)findViewById(R.id.et_displayname);
		fname = (EditText)findViewById(R.id.et_firstname);
		lname = (EditText)findViewById(R.id.et_lastname);
		password = (EditText)findViewById(R.id.et_password);
		mnumber = (EditText)findViewById(R.id.et_mobile);
		lnumber = (EditText)findViewById(R.id.et_landline);
		ymail = (EditText)findViewById(R.id.et_email);
//		yhno = (EditText)findViewById(R.id.yourhouseno);
		yaddrs = (EditText)findViewById(R.id.et_address);
		uploadphotoBTN = (ImageView)findViewById(R.id.iv_photo);
		rwaNameAutoTV = (AutoCompleteTextView) findViewById(R.id.rwaname);
		mProgressBar = (ProgressBar)findViewById(R.id.progress);
		//rwaNameAutoTV.setThreshold(2);
		rwaNameAutoTV.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if(mProgressBar.getVisibility() == View.INVISIBLE){
					String searchResult = s.toString();
					if(!mSelectedText.equalsIgnoreCase(searchResult)){
						mSelectedText = "";
					}
					loadRwaSuggestion(searchResult);
				}
			}
		});
		 inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		
		rwaNameAutoTV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mRwaId = mRwaSearchData.getRwaSearchItemList().get(arg2).getId();
				mSelectedText = mRwaSearchData.getRwaSearchItemList().get(arg2).getLabel();
			}
		});

		save = (Button)findViewById(R.id.btn_save);
		revert = (Button)findViewById(R.id.btn_revert);

		uploadphotoBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogController.selectImage(EditProfileActivity.this);
			}
		});

		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				RequestBean request = new RequestBean();

				try {


					JSONObject jObj = new JSONObject();

					String name = String.valueOf(dname.getText());
					String firstname = String.valueOf(fname.getText());
					String lastname = String.valueOf(lname.getText());
					String passw = String.valueOf(password.getText());
					String mobilenumber = String.valueOf(mnumber.getText());
					String landnumber = String.valueOf(lnumber.getText());
					String youremail = String.valueOf(ymail.getText());
					//String yourhousenumber = String.valueOf(yhno.getText());
					String youraddress = String.valueOf(yaddrs.getText());

					

					if(mSelectedText.equalsIgnoreCase("")){
						Toast.makeText(EditProfileActivity.this, R.string.empaty_rwa, Toast.LENGTH_SHORT).show();
					}else if(firstname.equalsIgnoreCase("")){
						Toast.makeText(EditProfileActivity.this, R.string.empaty_fname, Toast.LENGTH_SHORT).show();
					}else if(lastname.equalsIgnoreCase("")){
						Toast.makeText(EditProfileActivity.this, R.string.empaty_lname, Toast.LENGTH_SHORT).show();
					}else if(mobilenumber.equalsIgnoreCase("")){
						Toast.makeText(EditProfileActivity.this, R.string.empaty_mnumber, Toast.LENGTH_SHORT).show();
					}else if(youremail.equalsIgnoreCase("")){
						Toast.makeText(EditProfileActivity.this, R.string.empaty_youremail, Toast.LENGTH_SHORT).show();
					}else if(passw.equalsIgnoreCase("")){
						Toast.makeText(EditProfileActivity.this, R.string.empaty_password, Toast.LENGTH_SHORT).show();
					}else{

						jObj.put("name",name);
						jObj.put("firstname", firstname);
						jObj.put("lastname", lastname);
						jObj.put("rwa_id", mRwaId);
						jObj.put("mobile", mobilenumber);
						jObj.put("landline", landnumber);
						jObj.put("email", youremail);
						jObj.put("email", youremail);
						jObj.put("profilephoto", imageUrl);
						jObj.put("locality", youraddress);

						request.setActivity(EditProfileActivity.this);

						request.setJsonReq(jObj);

						request.setNetworkRequestName(NetworkRequestName.REGISTER);
						NetworkCall networkCall = new NetworkCall(request);
						networkCall.execute("");

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		loadProfileData();
	}
	
	protected void loadProfileData() {
		RequestBean request = new RequestBean();
		request.setActivity(this);
		request.setNetworkRequestName(NetworkRequestName.PROFILE);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair valuePair = new BasicNameValuePair("user_id", PreferenceHelper.getSingleInstance(getApplicationContext()).getString(PreferenceKey.USER_ID));
		list.add(valuePair);
		request.setNamevaluepair(list);
		request.setCallingClassObject(this);
		NetworkCall networkCall = new NetworkCall(request);
		networkCall.execute("");
	}
	
	
	
	protected void loadRwaSuggestion(final String suggestion) {
		mProgressBar.setVisibility(View.VISIBLE);
		new Thread(){
			public void run(){
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				NameValuePair valuePair = new BasicNameValuePair("term", suggestion);
				list.add(valuePair);
				NetworkCall networkCall = new NetworkCall(null);
				mRwaSearchData = networkCall.searchRwa(myParser, list);
				handler.sendEmptyMessage(0);
			}
		}.start();
	}
	
	private MyParser myParser = new MyParser();
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			mProgressBar.setVisibility(View.INVISIBLE);
			mRwaSearchAdapter = new RWASearchAdapter(getApplicationContext(), mRwaSearchData.getRwaSearchItemList());
			rwaNameAutoTV.setAdapter(mRwaSearchAdapter);
			rwaNameAutoTV.showDropDown();
		};
	};

	public void registerresponse(RegisterData registerdata){
		
		if(registerdata!=null){
			
			if(registerdata.getError().equalsIgnoreCase("success")){
				Toast.makeText(EditProfileActivity.this, registerdata.getSuccess_msg(), Toast.LENGTH_SHORT).show();
				setResult(RESULT_OK);
				finish();
			}else{
				Toast.makeText(EditProfileActivity.this, registerdata.getError_msg(), Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	private String imageUrl;
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
				Utils.saveImage(imageUrl, thumbnail);
				uploadphotoBTN.setImageBitmap(thumbnail);
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
				Utils.saveImage(imageUrl, bm);
				uploadphotoBTN.setImageBitmap(bm);
			}
		}

	}

	public void response(ProfileData profileData) {
		
	}

	public void response(RwaSearchData rwaSearchData) {
		mRwaSearchData = rwaSearchData;
		mRwaSearchAdapter = new RWASearchAdapter(getApplicationContext(), rwaSearchData.getRwaSearchItemList());
		rwaNameAutoTV.setAdapter(mRwaSearchAdapter);
		rwaNameAutoTV.showDropDown();
//		 inputManager.hideSoftInputFromWindow(rwaNameAutoTV.getWindowToken(),
//	                InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
