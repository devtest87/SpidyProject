package com.android.spideycity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bean.LoginData;
import com.bean.RequestBean;
import com.network.NetworkCall;
import com.utils.NetworkRequestName;
import com.utils.PreferenceHelper;
import com.utils.PreferenceHelper.PreferenceKey;


public class LoginActivity extends Activity {

	private EditText idbox, pwdbox;
	private Button submit, register;
	PreferenceHelper preferenceHelper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        preferenceHelper = PreferenceHelper.getSingleInstance(getApplicationContext());
        
        if(preferenceHelper.getBoolean(PreferenceKey.IS_LOGIN)){
        	Intent i = new Intent(LoginActivity.this, HomeScreen.class);
			startActivity(i);
			this.finish();
        }
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.login);
        
        
        idbox  = (EditText) findViewById(R.id.emailbox);
        pwdbox  = (EditText) findViewById(R.id.passwordbox);
        submit  = (Button) findViewById(R.id.submit);
        register  = (Button) findViewById(R.id.register);
        
        
        register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			
				Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
				
			}
		});
        
        submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String username = String.valueOf(idbox.getText());
				String password = String.valueOf(pwdbox.getText());
				
				if(username.equalsIgnoreCase("")){
					Toast.makeText(LoginActivity.this,R.string.emptyusername, Toast.LENGTH_SHORT).show();	
					return;
				}else if(username.length()<4){
					Toast.makeText(LoginActivity.this,R.string.namelengthmin, Toast.LENGTH_SHORT).show();
					return;
				}
				
				else if(password.equalsIgnoreCase("")){
					Toast.makeText(LoginActivity.this,R.string.emptypassword, Toast.LENGTH_SHORT).show();	
					return;
				}else{
				
					RequestBean request = new RequestBean();
					request.setActivity(LoginActivity.this);
					
					try {
						
						List<NameValuePair> npL = new ArrayList<NameValuePair>();

						npL.add(new BasicNameValuePair("email", username));
						npL.add(new BasicNameValuePair("password", password));
						
						request.setNamevaluepair(npL);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					request.setNetworkRequestName(NetworkRequestName.LOGIN);
					NetworkCall networkCall = new NetworkCall(request);
					networkCall.execute("");
					
				}
			}
		});
    }
    
    public void loginresponse(LoginData loginData){
    	
    	if(loginData != null){
    		Intent intent = new Intent(LoginActivity.this,HomeScreen.class);
			startActivity(intent);
    		
    		if(loginData.getError().equalsIgnoreCase("success")){

    			preferenceHelper.setString(PreferenceKey.USER_ID, loginData.getUid());
    			preferenceHelper.setString(PreferenceKey.RWAS_ID, loginData.getRwaid());
    			preferenceHelper.setString(PreferenceKey.RWAS_NAME, loginData.getRwaname());
    			preferenceHelper.setBoolean(PreferenceKey.IS_LOGIN, true);
    			
    			Intent i = new Intent(LoginActivity.this, HomeScreen.class);
				startActivity(i);
				this.finish();
				
    		}
    		
    	}
    }

}