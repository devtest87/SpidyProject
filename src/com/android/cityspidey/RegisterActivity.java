package com.android.cityspidey;

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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.adapter.RWASearchAdapter;
import com.bean.RegisterData;
import com.bean.RequestBean;
import com.bean.RwaSearchData;
import com.customview.DelayAutoCompleteTextView;
import com.network.NetworkCall;
import com.parser.MyParser;
import com.utils.DialogController;
import com.utils.NetworkRequestName;
import com.utils.Utils;


public class RegisterActivity extends Activity {

	private String mRwaId;
	private EditText dname;
	private EditText fname;
	private EditText lname;
	private EditText mnumber;
	private EditText lnumber;
	private EditText ymail;
	private EditText yhno;
	private EditText yaddrs;

	private CheckBox cb_erwa1;
	private CheckBox cb_erwa2;
	private CheckBox cb_headfamily;
	private CheckBox cb_termscondition;
	private RwaSearchData mRwaSearchData;
	private  InputMethodManager inputManager;

	private Button submit;
	private Button reset;
	private Button uploadphotoBTN;
	private DelayAutoCompleteTextView rwaNameAutoTV;
	private RWASearchAdapter mRwaSearchAdapter;
	private ProgressBar mProgressBar;
	private String mSelectedText = "";
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.registration);

		dname = (EditText)findViewById(R.id.dnamebox);
		fname = (EditText)findViewById(R.id.fnamebox);
		lname = (EditText)findViewById(R.id.lnamebox);
		mnumber = (EditText)findViewById(R.id.mnumrbox);
		lnumber = (EditText)findViewById(R.id.landlinenumber);
		ymail = (EditText)findViewById(R.id.youremail);
		yhno = (EditText)findViewById(R.id.yourhouseno);
		yaddrs = (EditText)findViewById(R.id.youraddress);
		uploadphotoBTN = (Button)findViewById(R.id.uploadphoto);
		rwaNameAutoTV = (DelayAutoCompleteTextView) findViewById(R.id.rwaname);
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

		cb_erwa1 = (CheckBox)findViewById(R.id.rwa1);
		cb_erwa2 = (CheckBox)findViewById(R.id.rwa2);
		cb_headfamily = (CheckBox)findViewById(R.id.headfamily);
		cb_termscondition = (CheckBox)findViewById(R.id.termscondition);

		submit = (Button)findViewById(R.id.submit);
		reset = (Button)findViewById(R.id.reset);

		uploadphotoBTN.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DialogController.selectImage(RegisterActivity.this);
			}
		});

		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				RequestBean request = new RequestBean();

				try {


					JSONObject jObj = new JSONObject();

					String name = String.valueOf(dname.getText());
					String firstname = String.valueOf(fname.getText());
					String lastname = String.valueOf(lname.getText());
					String mobilenumber = String.valueOf(mnumber.getText());
					String landnumber = String.valueOf(lnumber.getText());
					String youremail = String.valueOf(ymail.getText());
					String yourhousenumber = String.valueOf(yhno.getText());
					String youraddress = String.valueOf(yaddrs.getText());

					
					int edirectorymob = 1;
					if(cb_erwa1.isChecked() == true){
						edirectorymob = 1;
					}else{
						edirectorymob = 0;
					}

					int edirectorylan = 1;
					if(cb_erwa2.isChecked() == true){
						edirectorylan = 1;
					}else{
						edirectorylan = 0;
					}

					int headfamily = 1;
					if(cb_headfamily.isChecked() == true){
						headfamily = 1;
					}else{
						headfamily = 0;
					}

					int termscon = 1;
					if(cb_termscondition.isChecked() == true){
						termscon = 1;
					}else{
						termscon = 0;
					}

					if(mSelectedText.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_rwa, Toast.LENGTH_SHORT).show();
					}else if(firstname.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_fname, Toast.LENGTH_SHORT).show();
					}else if(lastname.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_lname, Toast.LENGTH_SHORT).show();
					}else if(mobilenumber.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_mnumber, Toast.LENGTH_SHORT).show();
					}else if(youremail.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_youremail, Toast.LENGTH_SHORT).show();
					}else if(yourhousenumber.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_hnumber, Toast.LENGTH_SHORT).show();
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
						jObj.put("streetname", yourhousenumber);
						jObj.put("locality", youraddress);
						jObj.put("headoffamily", headfamily);
						jObj.put("edirectorymobile",edirectorymob);
						jObj.put("edirectorylandline",edirectorylan);
						jObj.put("termandcondition",termscon);

						request.setActivity(RegisterActivity.this);

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
				Toast.makeText(RegisterActivity.this, registerdata.getSuccess_msg(), Toast.LENGTH_SHORT).show();
				setResult(RESULT_OK);
				finish();
			}else{
				Toast.makeText(RegisterActivity.this, registerdata.getError_msg(), Toast.LENGTH_SHORT).show();
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
			}
		}

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
