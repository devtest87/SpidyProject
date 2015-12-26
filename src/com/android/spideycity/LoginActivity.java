package com.android.spideycity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bean.LoginData;
import com.bean.RequestBean;
import com.network.NetworkCall;


public class LoginActivity extends Activity {

	private EditText idbox, pwdbox;
	private Button submit, register;
	private SharedPreferences mSharedPreferences ;
	private SharedPreferences.Editor editor;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.login);
        
		mSharedPreferences = getSharedPreferences("schoolapp",MODE_PRIVATE);
        
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

//				String username = String.valueOf(idbox.getText());
//				String password = String.valueOf(pwdbox.getText());
//				
//				if(username.equalsIgnoreCase("")){
//					Toast.makeText(LoginActivity.this,R.string.emptyusername, Toast.LENGTH_SHORT).show();					
//				}else if(username.length()<4){
//					Toast.makeText(LoginActivity.this,R.string.namelengthmin, Toast.LENGTH_SHORT).show();
//				}
//				
//				else if(password.equalsIgnoreCase("")){
//					Toast.makeText(LoginActivity.this,R.string.emptypassword, Toast.LENGTH_SHORT).show();					
//				}else{
//				
//					RequestBean request = new RequestBean();
//					request.setActivity(LoginActivity.this);
//					
//					try {
//						
//						List<NameValuePair> npL = new ArrayList<NameValuePair>();
//
//						npL.add(new BasicNameValuePair("email", username));
//						npL.add(new BasicNameValuePair("password", password));
//						
//						request.setNamevaluepair(npL);
//						
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					
//					request.setMethodName("login");
//					NetworkCall networkCall = new NetworkCall(request);
//					networkCall.execute("");
//					
//				}
				
				Intent intent = new Intent(LoginActivity.this,HomeScreen.class);
				startActivity(intent);
				
			}
		});
    }
    
    public void loginresponse(LoginData loginData){
    	
    	if(loginData != null){
    	
    		
//    		if(loginData.getError_code() == 1){
//
//    			editor = mSharedPreferences.edit();
//    			editor.putBoolean("islogin", true);
//    			editor.commit();
//    			
//    			Intent i = new Intent(LoginActivity.this, HomeScreen.class);
//				startActivity(i);
//				this.finish();
//				
//    		}
    		
    	}
    }

}
