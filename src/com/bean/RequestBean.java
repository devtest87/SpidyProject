package com.bean;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import com.utils.NetworkRequestName;

import android.R.bool;
import android.app.Activity;

@SuppressWarnings("rawtypes")
public class RequestBean {

	private Activity activity;
	private List<NameValuePair> namevaluepair;
	
	private JSONObject jsonReq;
	
	private NetworkRequestName networkRequestName;
	public NetworkRequestName getNetworkRequestName() {
		return networkRequestName;
	}
	public void setNetworkRequestName(NetworkRequestName networkRequestName) {
		this.networkRequestName = networkRequestName;
	}
	private Object callingClassObject;
	private String LoadingMessage="";
	private boolean isFreshStarted = false;
	
	
	public boolean isFreshStarted() {
		return isFreshStarted;
	}
	public void setFreshStarted(boolean isFreshStarted) {
		this.isFreshStarted = isFreshStarted;
	}
	public String getLoadingMessage() {
		return LoadingMessage;
	}
	public void setLoadingMessage(String loadingMessage) {
		LoadingMessage = loadingMessage;
	}
	public Object getCallingClassObject() {
		return callingClassObject;
	}
	public void setCallingClassObject(Object callingClassObject) {
		this.callingClassObject = callingClassObject;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public List<NameValuePair> getNamevaluepair() {
		return namevaluepair;
	}
	public void setNamevaluepair(List<NameValuePair> namevaluepair) {
		this.namevaluepair = namevaluepair;
	}
	public JSONObject getJsonReq() {
		return jsonReq;
	}
	public void setJsonReq(JSONObject jsonReq) {
		this.jsonReq = jsonReq;
	}
	
	
	
	
//	public List<NameValuePair> getParams() {
//		return params;
//	}
//	public void setParams(List<NameValuePair> params) {
//		this.params = params;
//	}
	
}
