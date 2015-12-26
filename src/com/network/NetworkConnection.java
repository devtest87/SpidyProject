package com.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;



public class NetworkConnection {
	
	
	public static String  getMessageFromServerForJson(String url,JSONObject json) {
		
		HttpClient client = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
		HttpResponse response;
		try{
//			json = MyEncodeDecode.encode(json);
			HttpPost post = new HttpPost(url);
			Log.e("###############",json.toString());
			StringEntity entity = new StringEntity(json.toString());
			entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			post.setEntity(entity);
			response = client.execute(post);
			/*Checking response */
			if(response!=null){
				InputStream inputStream = response.getEntity().getContent(); //Get the data in the entity
				String   response_string=convertStreamToString(inputStream);
				return response_string;
			}else
			{
				return null;
			}
		}

		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static String networkHit(JSONObject json , String url)
	{
		
		String responseEntity = null;
		url = url.trim();
		url = url.replace(" ", "%20");
		 HttpClient client = new DefaultHttpClient();
		  HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
		  HttpResponse response;
		  try{
		   HttpPost post = new HttpPost(url);
		   Log.e("############### In NetworkCall",json.toString());
		   StringEntity entity = new StringEntity(json.toString());
		   entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		   post.setEntity(entity);
		   response = client.execute(post);
		   /*Checking response */
		   if(response!=null){
		    InputStream inputStream = response.getEntity().getContent(); //Get the data in the entity
		    String   response_string=convertStreamToString(inputStream);
		    
		    Log.e("Response","...................."+response_string);
		    
		    if(response_string.contains("500 Internal Server Error")){
		    	response_string = "IOException"; 
		    }
		    
		    return response_string;
		   }else
		   {
		    return null;
		   }
	
		} catch (UnsupportedEncodingException e) {
			responseEntity = "UnsupportedEncodingException";
			e.printStackTrace();
		}catch (ClientProtocolException e1) {
			responseEntity = "ClientProtocolException";
			e1.printStackTrace();
		} catch (IOException e) {
			responseEntity = "IOException";
			e.printStackTrace();
		} catch (ParseException e) {
			responseEntity = "ParseException";
			e.printStackTrace();
		} 
		return responseEntity;
	}
	
	
	
	
	public static String networkHit(List<NameValuePair> pairs , String url)
	{
		String responseEntity = null;
		url = url.trim();
		url = url.replace(" ", "%20");
		try{
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
			DefaultHttpClient client = new DefaultHttpClient(httpParameters);
			HttpPost post= new HttpPost(url);
			if(pairs!=null)
			post.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));

			HttpResponse response;
			response = client.execute(post);
			responseEntity = EntityUtils.toString(response.getEntity());

		} catch (UnsupportedEncodingException e) {
			responseEntity = "UnsupportedEncodingException";
			e.printStackTrace();
		}catch (ClientProtocolException e1) {
			responseEntity = "ClientProtocolException";
			e1.printStackTrace();
		} catch (IOException e) {
			responseEntity = "IOException";
			e.printStackTrace();
		} catch (ParseException e) {
			responseEntity = "ParseException";
			e.printStackTrace();
		} 
		return responseEntity;
	}
	 private static String convertStreamToString(InputStream is) {

		  BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		  StringBuilder sb = new StringBuilder();

		  String line = null;
		  try {
		   while ((line = reader.readLine()) != null) {
		    sb.append(line + "\n");
		   }
		  } catch (IOException e) {
		   //   FlurryAgent.onError(Definitions.FLURRY_ERROR_NETWORK_OPERATION, e.getMessage(), e.getClass().getName());
		  } finally {
		   try {
		    is.close();
		   } catch (IOException e) {
		    //    FlurryAgent.onError(Definitions.FLURRY_ERROR_NETWORK_OPERATION, e.getMessage(), e.getClass().getName());
		    throw new RuntimeException(e.getMessage());
		   }
		  }
		  return sb.toString();
		 }
}