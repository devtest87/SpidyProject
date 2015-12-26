package com.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.itemsActivity.BookingActivity;
import com.android.itemsActivity.DirectoryActivity;
import com.android.itemsActivity.GroupsActivity;
import com.android.itemsActivity.NoticeBoardActivity;
import com.android.itemsActivity.OpinionPollActivity;
import com.android.itemsActivity.RWAsActivity;
import com.android.itemsActivity.RequestServicesActivity;
import com.android.itemsActivity.ServicesActivity;
import com.android.itemsActivity.SpidyPickActivity;
import com.android.spideycity.LoginActivity;
import com.android.spideycity.R;
import com.android.spideycity.RegisterActivity;
import com.bean.BookingsData;
import com.bean.DirectoryData;
import com.bean.GroupsData;
import com.bean.LoginData;
import com.bean.NoticeBoardData;
import com.bean.OpinionPollsData;
import com.bean.RWAsData;
import com.bean.RegisterData;
import com.bean.RequestBean;
import com.bean.RequestServicesData;
import com.bean.ServicesData;
import com.bean.SliderData;
import com.bean.spidyPickData;
import com.fragment.item.HomeFragment;
import com.parser.MyParser;
import com.utils.PrintLog;


public class NetworkCall extends AsyncTask<String, integer, Object>  
{

	private Activity activity;
	private RequestBean request;
	private MyParser myParser;
	private ProgressDialog mProgressDialog;

	private static final String LoginURL = "http://top-story.in/api/login.php";
	private static final String registerURL = "http://top-story.in/api/register.php";
	private static final String sliderTopURL = "http://top-story.in/api/news_slider.json";
	private static final String rwasURL = "http://top-story.in/api/rwa_list.json";
	private static final String groupsURL = "http://top-story.in/api/group-slider.php";
	private static final String servicesURL = "http://top-story.in/api/service_list.json";
	private static final String bookingsURL = "http://top-story.in/api/booking_list.php";
	private static final String noticeBoardURL = "http://top-story.in/api/notice_list.json";
	private static final String directoryURL = "http://top-story.in/api/service_list.json";
	private static final String spidyPickURL = "http://top-story.in/api/news_slider.json";
	private static final String opinionPollsURL = "http://top-story.in/api/poll_list.json";
	
	
	private static final String NEWSURL = "http://golfapp.net/app/webservices/nearby.php";
	private static final String LOCATIONURL = "http://fourthscreenlabs.com/buslocation.json";
	private static final String TAG = "NetworkCall";


	public NetworkCall(RequestBean requset)
	{
		if(requset!=null){

			this.request = requset;
			this.activity = requset.getActivity();
			this.myParser = new MyParser(activity);
		}else{
			Log.e("Network Call Class","no request parameter found");
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Object doInBackground(String... arg0) 
	{
		List<NameValuePair>  namePairList = request.getNamevaluepair();
		Object object = null;
		switch (request.getNetworkRequestName()) {
		case LOGIN:
			object = callLoginWS(namePairList);
			break;
		case REGISTER:
			JSONObject jobject = request.getJsonReq();
			object = callSignUPWS(jobject);
			break;
		case HOMESLIDER:
			object = callhomeslider();
			break;
		case RWAS:
			object = RWAs();
			break;
		case GROUPS:
			object = group();
			break;
		case SERVICES:
			object = services();
			break;
		case REQUEST_SERVICES:
			object = requestService(namePairList);
			break;
		case BOOKINGS:
			object = bookings();
			break;
		case NOTICEBOARDS:
			object = noticeBoards();
			break;
		case DIRECTORY:
			object = directory();
			break;
		case SPIDYPICKS:
			object = spidyPick();
			break;
		case OPINIONPOLLS:
			object = opinionPolls();
			break;

		}
		return object;
		/*if(request.getMethodName().equalsIgnoreCase("login"))
		{
			LoginData logindata = callLoginWS(namePairList);
			return logindata;
		}else if(request.getMethodName().equalsIgnoreCase("register")){
			JSONObject jobject = request.getJsonReq();
			RegisterData logindata = callSignUPWS(jobject);
			return logindata;
		}else if(request.getMethodName().equalsIgnoreCase("homeslider")){
			SliderData sliderdata = callhomeslider();
			return sliderdata;
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.RWAS.toString())){
			RWAsData rwAsData = RWAs();
			return rwAsData;
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.GROUPS.toString())){
			GroupsData rwAsData = group();
			return rwAsData;
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.SERVICES.toString())){
			ServicesData rwAsData = services();
			return rwAsData;
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.BOOKINGS.toString())){
			BookingsData rwAsData = bookings();
			return rwAsData;
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.NOTICEBOARDS.toString())){
			NoticeBoardData rwAsData = noticeBoards();
			return rwAsData;
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.DIRECTORY.toString())){
			DirectoryData rwAsData = directory();
			return rwAsData;
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.SPIDYPICKS.toString())){
			spidyPickData rwAsData = spidyPick();
			return rwAsData;
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.OPINIONPOLLS.toString())){
			OpinionPollsData rwAsData = opinionPolls();
			return rwAsData;
		}

		return null;*/
	}

	@Override
	protected void onCancelled() 
	{
		super.onCancelled();
	}


	@Override
	protected void onPostExecute(Object result) 
	{
		super.onPostExecute(result);


		if(mProgressDialog.isShowing()){
			mProgressDialog.dismiss();
		}
		
		
		switch (request.getNetworkRequestName()) {
		case LOGIN:
			LoginData logindata = (LoginData)result;

			if(logindata.getException().equalsIgnoreCase("UnsupportedEncodingException") || 
					logindata.getException().equalsIgnoreCase("ClientProtocolException") || 
					logindata.getException().equalsIgnoreCase("IOException") || 
					logindata.getException().equalsIgnoreCase("ParseException"))
			{
				Toast.makeText(activity, R.string.Internet_connection, Toast.LENGTH_SHORT).show();
			}
			else if(logindata.getException().equalsIgnoreCase("JsonParseException")){
				Toast.makeText(activity, R.string.parser_exception, Toast.LENGTH_SHORT).show();
			}
			else{

				if(activity instanceof LoginActivity){
					((LoginActivity) activity).loginresponse(logindata);	
				}
			}
			break;
		case REGISTER:
			RegisterData registerdata = (RegisterData)result;

			if(registerdata.getException().equalsIgnoreCase("UnsupportedEncodingException") || 
					registerdata.getException().equalsIgnoreCase("ClientProtocolException") || 
					registerdata.getException().equalsIgnoreCase("IOException") || 
					registerdata.getException().equalsIgnoreCase("ParseException"))
			{
				Toast.makeText(activity, R.string.Internet_connection, Toast.LENGTH_SHORT).show();
			}
			else if(registerdata.getException().equalsIgnoreCase("JsonParseException")){
				Toast.makeText(activity, R.string.parser_exception, Toast.LENGTH_SHORT).show();
			}
			else{

				if(activity instanceof RegisterActivity){
					((RegisterActivity) activity).registerresponse(registerdata);	
				}
			}
			break;
		case HOMESLIDER:
			SliderData sliderdata = (SliderData)result;

			if(sliderdata.getException().equalsIgnoreCase("UnsupportedEncodingException") || 
					sliderdata.getException().equalsIgnoreCase("ClientProtocolException") || 
					sliderdata.getException().equalsIgnoreCase("IOException") || 
					sliderdata.getException().equalsIgnoreCase("ParseException"))
			{
				Toast.makeText(activity, R.string.Internet_connection, Toast.LENGTH_SHORT).show();
			}
			else if(sliderdata.getException().equalsIgnoreCase("JsonParseException")){
				Toast.makeText(activity, R.string.parser_exception, Toast.LENGTH_SHORT).show();
			}
			else{

				if(request.getCallingClassObject() instanceof HomeFragment){
					((HomeFragment) request.getCallingClassObject()).sliderWSresponse(sliderdata);	
				}
			}
			break;
		case RWAS:
			RWAsData rwAsData = (RWAsData)result;
			if(activity instanceof RWAsActivity){
				((RWAsActivity) activity).response(rwAsData);	
			}
			break;
		case GROUPS:
			GroupsData groupsData = (GroupsData)result;
			if(activity instanceof GroupsActivity){
				((GroupsActivity) activity).response(groupsData);	
			}
			break;
		case SERVICES:
			ServicesData servicesData = (ServicesData)result;
			if(activity instanceof ServicesActivity){
				((ServicesActivity) activity).response(servicesData);	
			}
			break;
		case REQUEST_SERVICES:
			RequestServicesData requestServicesData = (RequestServicesData)result;
			if(activity instanceof RequestServicesActivity){
				((RequestServicesActivity) activity).response(requestServicesData);	
			}
			break;
		case BOOKINGS:
			BookingsData bookingsData = (BookingsData)result;
			if(activity instanceof BookingActivity){
				((BookingActivity) activity).response(bookingsData);	
			}
			break;
		case NOTICEBOARDS:
			NoticeBoardData noticeBoardData = (NoticeBoardData)result;
			if(activity instanceof NoticeBoardActivity){
				((NoticeBoardActivity) activity).response(noticeBoardData);	
			}
			break;
		case DIRECTORY:
			DirectoryData directoryData = (DirectoryData)result;
			if(activity instanceof DirectoryActivity){
				((DirectoryActivity) activity).response(directoryData);	
			}
			break;
		case SPIDYPICKS:
			spidyPickData spidyPickData = (spidyPickData)result;
			if(activity instanceof SpidyPickActivity){
				((SpidyPickActivity) activity).response(spidyPickData);	
			}
			break;
		case OPINIONPOLLS:
			OpinionPollsData opinionPollsData = (OpinionPollsData)result;
			if(activity instanceof OpinionPollActivity){
				((OpinionPollActivity) activity).response(opinionPollsData);	
			}
			break;

		}


		/*if(request.getMethodName().equalsIgnoreCase("login")){
			LoginData logindata = (LoginData)result;

			if(logindata.getException().equalsIgnoreCase("UnsupportedEncodingException") || 
					logindata.getException().equalsIgnoreCase("ClientProtocolException") || 
					logindata.getException().equalsIgnoreCase("IOException") || 
					logindata.getException().equalsIgnoreCase("ParseException"))
			{
				Toast.makeText(activity, R.string.Internet_connection, Toast.LENGTH_SHORT).show();
			}
			else if(logindata.getException().equalsIgnoreCase("JsonParseException")){
				Toast.makeText(activity, R.string.parser_exception, Toast.LENGTH_SHORT).show();
			}
			else{

				if(activity instanceof LoginActivity){
					((LoginActivity) activity).loginresponse(logindata);	
				}
			}

		}else if(request.getMethodName().equalsIgnoreCase("register")){
			RegisterData registerdata = (RegisterData)result;

			if(registerdata.getException().equalsIgnoreCase("UnsupportedEncodingException") || 
					registerdata.getException().equalsIgnoreCase("ClientProtocolException") || 
					registerdata.getException().equalsIgnoreCase("IOException") || 
					registerdata.getException().equalsIgnoreCase("ParseException"))
			{
				Toast.makeText(activity, R.string.Internet_connection, Toast.LENGTH_SHORT).show();
			}
			else if(registerdata.getException().equalsIgnoreCase("JsonParseException")){
				Toast.makeText(activity, R.string.parser_exception, Toast.LENGTH_SHORT).show();
			}
			else{

				if(activity instanceof RegisterActivity){
					((RegisterActivity) activity).registerresponse(registerdata);	
				}
			}

		}else if(request.getMethodName().equalsIgnoreCase("homeslider")){
			SliderData sliderdata = (SliderData)result;

			if(sliderdata.getException().equalsIgnoreCase("UnsupportedEncodingException") || 
					sliderdata.getException().equalsIgnoreCase("ClientProtocolException") || 
					sliderdata.getException().equalsIgnoreCase("IOException") || 
					sliderdata.getException().equalsIgnoreCase("ParseException"))
			{
				Toast.makeText(activity, R.string.Internet_connection, Toast.LENGTH_SHORT).show();
			}
			else if(sliderdata.getException().equalsIgnoreCase("JsonParseException")){
				Toast.makeText(activity, R.string.parser_exception, Toast.LENGTH_SHORT).show();
			}
			else{

				if(request.getCallingClassObject() instanceof HomeFragment){
					((HomeFragment) request.getCallingClassObject()).sliderWSresponse(sliderdata);	
				}
			}

		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.RWAS.toString())){
			RWAsData rwAsData = (RWAsData)result;
			if(activity instanceof RWAsActivity){
				((RWAsActivity) activity).response(rwAsData);	
			}
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.GROUPS.toString())){
			GroupsData groupsData = (GroupsData)result;
			if(activity instanceof GroupsActivity){
				((GroupsActivity) activity).response(groupsData);	
			}
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.SERVICES.toString())){
			ServicesData servicesData = (ServicesData)result;
			if(activity instanceof ServicesActivity){
				((ServicesActivity) activity).response(servicesData);	
			}
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.BOOKINGS.toString())){
			BookingsData bookingsData = (BookingsData)result;
			if(activity instanceof BookingActivity){
				((BookingActivity) activity).response(bookingsData);	
			}
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.NOTICEBOARDS.toString())){
			NoticeBoardData noticeBoardData = (NoticeBoardData)result;
			if(activity instanceof NoticeBoardActivity){
				((NoticeBoardActivity) activity).response(noticeBoardData);	
			}
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.DIRECTORY.toString())){
			DirectoryData directoryData = (DirectoryData)result;
			if(activity instanceof DirectoryActivity){
				((DirectoryActivity) activity).response(directoryData);	
			}
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.SPIDYPICKS.toString())){
			spidyPickData spidyPickData = (spidyPickData)result;
			if(activity instanceof SpidyPickActivity){
				((SpidyPickActivity) activity).response(spidyPickData);	
			}
		}else if(request.getMethodName().equalsIgnoreCase(NetworkRequestName.OPINIONPOLLS.toString())){
			OpinionPollsData opinionPollsData = (OpinionPollsData)result;
			if(activity instanceof OpinionPollActivity){
				((OpinionPollActivity) activity).response(opinionPollsData);	
			}
		}*/

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		mProgressDialog = new ProgressDialog(activity);
		mProgressDialog.setMessage("Please wait...");
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setCancelable(true);
		mProgressDialog.show();

	}


	private LoginData callLoginWS(List<NameValuePair> namePair){

		LoginData logindata = new LoginData();
		String response = NetworkConnection.networkHit(namePair,LoginURL);

		//		response = LoadData("getlogin.txt");

		if(response.equalsIgnoreCase("UnsupportedEncodingException") || response.equalsIgnoreCase("ClientProtocolException") || response.equalsIgnoreCase("IOException") || response.equalsIgnoreCase("ParseException")){
			logindata.setException(response);
		}else{
			logindata = myParser.parseLogin(response);
		}
		return logindata;
	}
	
	
	public RegisterData callSignUPWS(JSONObject jObj)
	{
		String response_Reg="";
		RegisterData signUp = new RegisterData();
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(registerURL); 
		MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE); 
		try
		{ 
			
//			if(profilePic!=null && profilePic.trim().length()>0){
//				ContentBody user_pic = new FileBody( new File(profilePic), "image/jpg" );
//				reqEntity.addPart(Constants.profilePic,user_pic);
//			}

//			{"edirectorylandline":0,"landline":"vhhhh","termandcondition":1,"email":"bhhjj","headoffamily":1,"name":"popopo","locality":"vhhjjj","lastname":"hjjjjk","firstname":"popopo","edirectorymobile":0,"streetname":"ghjjj","mobile":"vbhjjj"}
			
			StringBody name = new StringBody(jObj.getString("name"));
			StringBody email = new StringBody(jObj.getString("email"));
			StringBody fname = new StringBody(jObj.getString("firstname"));
			StringBody lname = new StringBody(jObj.getString("lastname"));
			StringBody mobile = new StringBody(jObj.getString("mobile"));
			StringBody landline = new StringBody(jObj.getString("landline"));
			StringBody streetname = new StringBody(jObj.getString("streetname"));
			
			StringBody locality = new StringBody(jObj.getString("locality"));
			StringBody headoffamily = new StringBody(jObj.getString("headoffamily"));
			StringBody edirectorymobile = new StringBody(jObj.getString("edirectorymobile"));
			StringBody edirectorylandline = new StringBody(jObj.getString("edirectorylandline"));
			StringBody termandcondition = new StringBody(jObj.getString("termandcondition"));
			
			
			
			reqEntity.addPart("name",name);
			reqEntity.addPart("email", email);
			reqEntity.addPart("firstname", fname);
			reqEntity.addPart("lastname", lname);
			reqEntity.addPart("mobile", mobile);
			reqEntity.addPart("landline", landline);
			reqEntity.addPart("streetname", streetname);
			reqEntity.addPart("locality", locality);
			reqEntity.addPart("headoffamily", headoffamily);
			reqEntity.addPart("edirectorymobile",edirectorymobile);
			reqEntity.addPart("edirectorylandline",edirectorylandline);
			reqEntity.addPart("termandcondition",termandcondition);
			
			
			post.setEntity(reqEntity);
			HttpResponse response;
			response = client.execute(post);
			HttpEntity resEntity = response.getEntity(); 
			response_Reg=EntityUtils.toString(resEntity);
			
			
		} catch (JSONException e) {
			response_Reg = "JSON request error";
			e.printStackTrace();
		}	
		 catch (UnsupportedEncodingException e) {
			response_Reg = "UnsupportedEncodingException";
			e.printStackTrace();
		}catch (ClientProtocolException e1) {
			response_Reg = "ClientProtocolException";
			e1.printStackTrace();
		} catch (IOException e) {
			response_Reg = "IOException";
			e.printStackTrace();
		} catch (ParseException e) {
			response_Reg = "ParseException";
			e.printStackTrace();
		} 

		if(response_Reg.equalsIgnoreCase("UnsupportedEncodingException") || 
				response_Reg.equalsIgnoreCase("ClientProtocolException") ||
				response_Reg.equalsIgnoreCase("IOException") || 
				response_Reg.equalsIgnoreCase("ParseException")){

			signUp.setException(response_Reg);

		}else{
			signUp = myParser.register(response_Reg);
		}

		return signUp;
	}
	

	public SliderData callhomeslider(){
		List<NameValuePair> pair = null;
		SliderData sliderdata = new SliderData();
		String response = NetworkConnection.networkHit(pair,sliderTopURL);

		if(response.equalsIgnoreCase("UnsupportedEncodingException") || response.equalsIgnoreCase("ClientProtocolException") || response.equalsIgnoreCase("IOException") || response.equalsIgnoreCase("ParseException")){
			sliderdata.setException(response);
		}else{
			sliderdata = myParser.parseTopSlider(response);
		}
		return sliderdata;
		
	}
	
	public RWAsData RWAs(){
		List<NameValuePair> pair = null;
		RWAsData rwAsData;
		String response = NetworkConnection.networkHit(pair,rwasURL);

		PrintLog.show(Log.ERROR, TAG, "" + response);
		if(response.equalsIgnoreCase("UnsupportedEncodingException") || response.equalsIgnoreCase("ClientProtocolException") || response.equalsIgnoreCase("IOException") || response.equalsIgnoreCase("ParseException")){
			rwAsData = new RWAsData();
			rwAsData.setException(response);
		}else{
			rwAsData = myParser.parseRWAs(response);
		}
		return rwAsData;
		
	}
	
	public GroupsData group(){
		List<NameValuePair> pair = null;
		GroupsData groupsData;
		String response = NetworkConnection.networkHit(pair,groupsURL);

		PrintLog.show(Log.ERROR, TAG, "" + response);
		if(response.equalsIgnoreCase("UnsupportedEncodingException") || response.equalsIgnoreCase("ClientProtocolException") || response.equalsIgnoreCase("IOException") || response.equalsIgnoreCase("ParseException")){
			groupsData = new GroupsData();
			groupsData.setException(response);
		}else{
			groupsData = myParser.parseGroups(response);
		}
		return groupsData;
	}
	
	public ServicesData services(){
		List<NameValuePair> pair = null;
		ServicesData servicesData;
		String response = NetworkConnection.networkHit(pair,servicesURL);

		PrintLog.show(Log.ERROR, TAG, "" + response);
		if(response.equalsIgnoreCase("UnsupportedEncodingException") || response.equalsIgnoreCase("ClientProtocolException") || response.equalsIgnoreCase("IOException") || response.equalsIgnoreCase("ParseException")){
			servicesData = new ServicesData();
			servicesData.setException(response);
		}else{
			servicesData = myParser.parseServices(response);
		}
		return servicesData;
		
	}
	
	private RequestServicesData requestService(List<NameValuePair> namePair){

		RequestServicesData requestServicesData = new RequestServicesData();
		String response = NetworkConnection.networkHit(namePair,LoginURL);

		//		response = LoadData("getlogin.txt");

		PrintLog.show(Log.ERROR, TAG, "" + response);
		if(response.equalsIgnoreCase("UnsupportedEncodingException") || response.equalsIgnoreCase("ClientProtocolException") || response.equalsIgnoreCase("IOException") || response.equalsIgnoreCase("ParseException")){
			requestServicesData.setException(response);
		}else{
			requestServicesData = myParser.parseRequestServices(response);
		}
		return requestServicesData;
	}
	
	public BookingsData bookings(){
		List<NameValuePair> pair = null;
		BookingsData bookingsData;
		String response = NetworkConnection.networkHit(pair,bookingsURL);

		PrintLog.show(Log.ERROR, TAG + Thread.currentThread().getStackTrace()[1].getMethodName(), "" + response);
		if(response.equalsIgnoreCase("UnsupportedEncodingException") || response.equalsIgnoreCase("ClientProtocolException") || response.equalsIgnoreCase("IOException") || response.equalsIgnoreCase("ParseException")){
			bookingsData = new BookingsData();
			bookingsData.setException(response);
		}else{
			bookingsData = myParser.parseBooings(response);
		}
		return bookingsData;
		
	}
	
	public NoticeBoardData noticeBoards(){
		List<NameValuePair> pair = null;
		NoticeBoardData noticeBoardData;
		String response = NetworkConnection.networkHit(pair,noticeBoardURL);

		PrintLog.show(Log.ERROR, TAG, "" + response);
		if(response.equalsIgnoreCase("UnsupportedEncodingException") || response.equalsIgnoreCase("ClientProtocolException") || response.equalsIgnoreCase("IOException") || response.equalsIgnoreCase("ParseException")){
			noticeBoardData = new NoticeBoardData();
			noticeBoardData.setException(response);
		}else{
			noticeBoardData = myParser.parseNoticeBoard(response);
		}
		return noticeBoardData;
		
	}
	
	
	public DirectoryData directory(){
		List<NameValuePair> pair = null;
		DirectoryData directoryData;
		String response = NetworkConnection.networkHit(pair,directoryURL);

		PrintLog.show(Log.ERROR, TAG, "" + response);
		if(response.equalsIgnoreCase("UnsupportedEncodingException") || response.equalsIgnoreCase("ClientProtocolException") || response.equalsIgnoreCase("IOException") || response.equalsIgnoreCase("ParseException")){
			directoryData = new DirectoryData();
			directoryData.setException(response);
		}else{
			directoryData = myParser.parseDirectory(response);
		}
		return directoryData;
		
	}
	
	public spidyPickData spidyPick(){
		List<NameValuePair> pair = null;
		spidyPickData spidyPickData;
		String response = NetworkConnection.networkHit(pair,spidyPickURL);

		PrintLog.show(Log.ERROR, TAG, "" + response);
		if(response.equalsIgnoreCase("UnsupportedEncodingException") || response.equalsIgnoreCase("ClientProtocolException") || response.equalsIgnoreCase("IOException") || response.equalsIgnoreCase("ParseException")){
			spidyPickData = new spidyPickData();
			spidyPickData.setException(response);
		}else{
			spidyPickData = myParser.parseSPidyPick(response);
		}
		return spidyPickData;
		
	}
	
	public OpinionPollsData opinionPolls(){
		List<NameValuePair> pair = null;
		OpinionPollsData opinionPollsData;
		String response = NetworkConnection.networkHit(pair,opinionPollsURL);

		PrintLog.show(Log.ERROR, TAG, "" + response);
		if(response.equalsIgnoreCase("UnsupportedEncodingException") || response.equalsIgnoreCase("ClientProtocolException") || response.equalsIgnoreCase("IOException") || response.equalsIgnoreCase("ParseException")){
			opinionPollsData = new OpinionPollsData();
			opinionPollsData.setException(response);
		}else{
			opinionPollsData = myParser.parseOpinionPolls(response);
		}
		return opinionPollsData;
		
	}
	
	
	public String LoadData(String inFile) {
		String tContents = "";

		try {
			InputStream stream = activity.getAssets().open(inFile);

			int size = stream.available();
			byte[] buffer = new byte[size];
			stream.read(buffer);
			stream.close();
			tContents = new String(buffer);
		} catch (IOException e) {
			// Handle exceptions here
		}

		return tContents;

	}

}
