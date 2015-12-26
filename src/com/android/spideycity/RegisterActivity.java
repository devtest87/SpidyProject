package com.android.spideycity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.bean.RegisterData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends Activity {

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

	private Button submit;
	private Button reset;

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

		cb_erwa1 = (CheckBox)findViewById(R.id.rwa1);
		cb_erwa2 = (CheckBox)findViewById(R.id.rwa2);
		cb_headfamily = (CheckBox)findViewById(R.id.headfamily);
		cb_termscondition = (CheckBox)findViewById(R.id.termscondition);

		submit = (Button)findViewById(R.id.submit);
		reset = (Button)findViewById(R.id.reset);



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
					}else{

						jObj.put("name",name);
						jObj.put("firstname", firstname);
						jObj.put("lastname", lastname);
						jObj.put("mobile", mobilenumber);
						jObj.put("landline", landnumber);
						jObj.put("email", youremail);
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
				finish();
			}else{
				Toast.makeText(RegisterActivity.this, registerdata.getError_msg(), Toast.LENGTH_SHORT).show();
			}
		}
	}
}
