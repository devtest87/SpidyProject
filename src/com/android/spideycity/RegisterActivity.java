package com.android.spideycity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.MediaColumns;
import android.support.v4.content.CursorLoader;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bean.RegisterData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.DialogController;
import com.utils.NetworkRequestName;
import com.utils.Utils;


public class RegisterActivity extends Activity {

	private EditText dname;
	private EditText fname;
	private EditText lname;
	private EditText password;
	private EditText mnumber;
	private EditText lnumber;
	private EditText ymail;
	private EditText yhno;
	private EditText yaddrs;

	private CheckBox cb_erwa1;
	private CheckBox cb_erwa2;
	private CheckBox cb_headfamily;
	private CheckBox cb_termscondition;

	private Button submit;
	private Button reset;
	private Button uploadphotoBTN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.registration);

		dname = (EditText)findViewById(R.id.dnamebox);
		fname = (EditText)findViewById(R.id.fnamebox);
		lname = (EditText)findViewById(R.id.lnamebox);
		password = (EditText)findViewById(R.id.lpasswordbox);
		mnumber = (EditText)findViewById(R.id.mnumrbox);
		lnumber = (EditText)findViewById(R.id.landlinenumber);
		ymail = (EditText)findViewById(R.id.youremail);
		yhno = (EditText)findViewById(R.id.yourhouseno);
		yaddrs = (EditText)findViewById(R.id.youraddress);
		uploadphotoBTN = (Button)findViewById(R.id.uploadphoto);

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
					String passw = String.valueOf(password.getText());
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

					if(firstname.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_fname, Toast.LENGTH_SHORT).show();
					}else if(lastname.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_lname, Toast.LENGTH_SHORT).show();
					}else if(mobilenumber.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_mnumber, Toast.LENGTH_SHORT).show();
					}else if(youremail.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_youremail, Toast.LENGTH_SHORT).show();
					}else if(yourhousenumber.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_hnumber, Toast.LENGTH_SHORT).show();
					}else if(passw.equalsIgnoreCase("")){
						Toast.makeText(RegisterActivity.this, R.string.empaty_password, Toast.LENGTH_SHORT).show();
					}else{

						jObj.put("name",name);
						jObj.put("firstname", firstname);
						jObj.put("lastname", lastname);
						jObj.put("password", passw);
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
	
	public void registerresponse(RegisterData registerdata){
		
		if(registerdata!=null){
			
			if(registerdata.getError().equalsIgnoreCase("success")){
				Toast.makeText(RegisterActivity.this, registerdata.getSuccess_msg(), Toast.LENGTH_SHORT).show();
				Intent i = new Intent(RegisterActivity.this, HomeScreen.class);
				startActivity(i);
				this.finish();
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
}
