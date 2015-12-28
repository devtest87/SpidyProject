package com.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.bean.BookingsData;
import com.bean.DirectoryData;
import com.bean.GroupsData;
import com.bean.HomeSliderItem;
import com.bean.LoginData;
import com.bean.NoticeBoardData;
import com.bean.NoticeBoardItemsData;
import com.bean.OpinionPollsData;
import com.bean.RWAFacilityData;
import com.bean.RWAItemsData;
import com.bean.RWAsData;
import com.bean.RWAsDetailItemData;
import com.bean.RegisterData;
import com.bean.RequestServicesData;
import com.bean.ServicesData;
import com.bean.ServicesItemsData;
import com.bean.SliderData;
import com.bean.User;
import com.bean.spidyPickData;

public class MyParser
{
	Context mContext;


	public MyParser(Context ctx) 
	{
		this.mContext=ctx;
	}


	public LoginData parseLogin(String response) {

		LoginData login = new LoginData();
		try 
		{
			JSONObject	jObj = new JSONObject(response);
			String error = jObj.getString("error");

			login.setError(error);

			if(error.equalsIgnoreCase("success"))
			{

				login.setUid(jObj.getString("uid"));
				login.setRwaid(jObj.getString("rwaid"));
				login.setRwaname(jObj.getString("rwaname"));
				login.setPhoto(jObj.getString("photo"));

				User user = new User();

				jObj = jObj.getJSONObject("user");

				user.setName(jObj.getString("name"));
				user.setEmail(jObj.getString("email"));
				user.setMobile(jObj.getString("mobile"));
				user.setUpdateddate(jObj.getString("updateddate"));

				login.setUser(user);
			}
			else
			{
				login.setError_msg(jObj.getString("error_msg"));

			}

		} catch (Exception e) {
			login.setException("JsonParseException");
		}
		return login;

	}

	public RegisterData register(String response){
		RegisterData reg = new RegisterData();

		try 
		{
			JSONObject jObj = new JSONObject(response);

			String error = jObj.getString("error");
			reg.setError(error);

			if(error.equalsIgnoreCase("success"))
			{
				reg.setSuccess_msg(jObj.getString("success_msg"));
			}else{
				reg.setError_msg(jObj.getString("error_msg"));
			}

		} catch (Exception e) {
			reg.setException("JsonParseException");
		}
		return reg;
	}

	public SliderData parseTopSlider(String response){
		SliderData slider = new SliderData();

		try {

			JSONObject jObj = new JSONObject(response);

			if(jObj.getString("error").equalsIgnoreCase("success")){
				slider.setError(jObj.getString("error"));

				JSONArray jArrTop = jObj.getJSONArray("top");

				List<HomeSliderItem> slidList = new ArrayList<HomeSliderItem>();
				for (int i = 0; i < jArrTop.length(); i++) {
					HomeSliderItem item = new HomeSliderItem();

					item.setId(jArrTop.getJSONObject(i).getString("id"));
					item.setDesc(jArrTop.getJSONObject(i).getString("desc"));
					item.setGenre(jArrTop.getJSONObject(i).getString("genre"));
					item.setImage(jArrTop.getJSONObject(i).getString("image"));
					item.setReleaseYear(jArrTop.getJSONObject(i).getString("releaseYear"));
					item.setTitle(jArrTop.getJSONObject(i).getString("title"));
					item.setUrl(jArrTop.getJSONObject(i).getString("url"));

					slidList.add(item);
				}

				if(jObj.getJSONArray("notice")!=null){
					JSONArray jArrNotice = jObj.getJSONArray("notice");
					for (int i = 0; i < jArrNotice.length(); i++) {
						HomeSliderItem item = new HomeSliderItem();

						item.setId(jArrNotice.getJSONObject(i).getString("id"));
						item.setDesc(jArrNotice.getJSONObject(i).getString("desc"));
						item.setGenre(jArrNotice.getJSONObject(i).getString("genre"));
						item.setImage(jArrNotice.getJSONObject(i).getString("image"));
						item.setReleaseYear(jArrNotice.getJSONObject(i).getString("releaseYear"));
						item.setTitle(jArrNotice.getJSONObject(i).getString("title"));
						item.setUrl(jArrNotice.getJSONObject(i).getString("url"));

						slider.setNoticeitem(item);
					}
				}

				if(jObj.getJSONArray("poll")!=null){
					JSONArray jArrPoll = jObj.getJSONArray("poll");
					for (int i = 0; i < jArrPoll.length(); i++) {
						HomeSliderItem item = new HomeSliderItem();

						item.setId(jArrPoll.getJSONObject(i).getString("id"));
						item.setDesc(jArrPoll.getJSONObject(i).getString("desc"));
						item.setGenre(jArrPoll.getJSONObject(i).getString("genre"));
						item.setImage(jArrPoll.getJSONObject(i).getString("image"));
						item.setReleaseYear(jArrPoll.getJSONObject(i).getString("releaseYear"));
						item.setTitle(jArrPoll.getJSONObject(i).getString("title"));
						item.setUrl(jArrPoll.getJSONObject(i).getString("url"));

						slider.setPollitem(item);
					}
				}
			}

		} catch (JSONException e) {
			slider.setException("JsonParseException");
			e.printStackTrace();
		}


		return slider;
	}


	public RWAsData parseRWAs(String response){
		RWAsData rwAsData = new RWAsData();
		List<RWAItemsData> rwaItemsDatasList = rwAsData.getRwaItemsDatasList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				RWAItemsData rwaItemsData = new RWAItemsData();

				rwaItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				rwaItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				rwaItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				rwaItemsData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				rwaItemsData.setAdr(jArrRWA.getJSONObject(i).getString("adr"));
				rwaItemsData.setCity(jArrRWA.getJSONObject(i).getString("city"));
				rwaItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				rwaItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));
				rwaItemsData.setFurl(jArrRWA.getJSONObject(i).getString("furl"));
				rwaItemsData.setReleaseYear(jArrRWA.getJSONObject(i).getString("releaseYear"));

				rwaItemsDatasList.add(rwaItemsData);
			}

		} catch (JSONException e) {
			rwAsData.setException("JsonParseException");
			e.printStackTrace();
		}


		return rwAsData;
	}

	public RWAsDetailItemData parseRWAsDetail(String response){
		RWAsDetailItemData rwasDetailData = new RWAsDetailItemData();
		try {

			JSONObject object = new JSONObject(response);
			rwasDetailData.setId(object.getString("id"));
			rwasDetailData.setAddress(object.getString("adr"));
			rwasDetailData.setDesc(object.getString("desc"));
			rwasDetailData.setGenre(object.getString("genre"));
			rwasDetailData.setImage(object.getString("image"));
			rwasDetailData.setReleaseYear(object.getString("releaseYear"));
			rwasDetailData.setTitle(object.getString("title"));
			rwasDetailData.setGenre(object.getString("genre"));
			rwasDetailData.setCreatedby(object.getString("createdby"));
			
			
			if(object.has("cdetailslabel")){
				rwasDetailData.setCdetailslabel(object.getJSONObject("cdetailslabel"));
				rwasDetailData.setCdetails(object.getJSONObject("cdetails"));
			}
			if(object.has("quicklooklabel")){
				rwasDetailData.setQuickLookLablel(object.getJSONObject("quicklooklabel"));
				rwasDetailData.setQuickLook(object.getJSONObject("quicklook"));
			}

		} catch (JSONException e) {
			rwasDetailData.setException("JsonParseException");
			e.printStackTrace();
		}


		return rwasDetailData;
	}
	
	public List<RWAFacilityData> parseRWAsFacility(String response){
		List<RWAFacilityData> rwaFacilityDataList = new ArrayList<RWAFacilityData>();
		
		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				RWAFacilityData rwaFacilityData = new RWAFacilityData();

				rwaFacilityData.setId(jArrRWA.getJSONObject(i).getString("id"));
				rwaFacilityData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				rwaFacilityData.setCreatedby(jArrRWA.getJSONObject(i).getString("createdby"));
				rwaFacilityData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				rwaFacilityData.setFacility_Id(jArrRWA.getJSONObject(i).getString("facility_Id"));
				rwaFacilityData.setRwaId(jArrRWA.getJSONObject(i).getString("rwaId"));
				rwaFacilityData.setFacilityName(jArrRWA.getJSONObject(i).getString("facilityName"));

				rwaFacilityDataList.add(rwaFacilityData);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}


		return rwaFacilityDataList;
	}

	public GroupsData parseGroups(String response){
		GroupsData groupsData = new GroupsData();
		List<RWAItemsData> rwaItemsDatasList = groupsData.getRwaItemsDatasList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				RWAItemsData rwaItemsData = new RWAItemsData();

				rwaItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				rwaItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				rwaItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				rwaItemsData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				rwaItemsData.setAdr(jArrRWA.getJSONObject(i).getString("adr"));
				rwaItemsData.setCity(jArrRWA.getJSONObject(i).getString("city"));
				rwaItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				rwaItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));
				rwaItemsData.setFurl(jArrRWA.getJSONObject(i).getString("furl"));
				rwaItemsData.setReleaseYear(jArrRWA.getJSONObject(i).getString("releaseYear"));

				rwaItemsDatasList.add(rwaItemsData);
			}

		} catch (JSONException e) {
			groupsData.setException("JsonParseException");
			e.printStackTrace();
		}


		return groupsData;
	}

	public ServicesData parseServices(String response){
		ServicesData servicesData = new ServicesData();
		List<ServicesItemsData> servicesItemsDatasList = servicesData.getServicesItemsDatasList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				ServicesItemsData servicesItemsData = new ServicesItemsData();

				servicesItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				servicesItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				servicesItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				servicesItemsData.setCreatedDate(jArrRWA.getJSONObject(i).getString("createdDate"));
				servicesItemsData.setMobile(jArrRWA.getJSONObject(i).getString("mobile"));
				servicesItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				servicesItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));
				servicesItemsData.setServiceProvider(jArrRWA.getJSONObject(i).getString("serviceProvider"));
				servicesItemsData.setIcon(jArrRWA.getJSONObject(i).getString("icon"));
				

				servicesItemsDatasList.add(servicesItemsData);
			}

		} catch (JSONException e) {
			servicesData.setException("JsonParseException");
			e.printStackTrace();
		}


		return servicesData;
	}

	public RequestServicesData parseRequestServices(String response){
		RequestServicesData requestServicesData = new RequestServicesData();
		List<ServicesItemsData> servicesItemsDatasList = requestServicesData.getServicesItemsDatasList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				ServicesItemsData servicesItemsData = new ServicesItemsData();

				servicesItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				servicesItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				servicesItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				servicesItemsData.setCreatedDate(jArrRWA.getJSONObject(i).getString("createdDate"));
				servicesItemsData.setMobile(jArrRWA.getJSONObject(i).getString("mobile"));
				servicesItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				servicesItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));
				servicesItemsData.setServiceProvider(jArrRWA.getJSONObject(i).getString("serviceProvider"));

				servicesItemsDatasList.add(servicesItemsData);
			}

		} catch (JSONException e) {
			requestServicesData.setException("JsonParseException");
			e.printStackTrace();
		}


		return requestServicesData;
	}

	public BookingsData parseBooings(String response){
		BookingsData bookingsData = new BookingsData();
		List<RWAItemsData> rwaItemsDatasList = bookingsData.getRwaItemsDatasList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				RWAItemsData rwaItemsData = new RWAItemsData();

				rwaItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				rwaItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				rwaItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				rwaItemsData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				rwaItemsData.setAdr(jArrRWA.getJSONObject(i).getString("adr"));
				rwaItemsData.setCity(jArrRWA.getJSONObject(i).getString("city"));
				rwaItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				rwaItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));
				rwaItemsData.setFurl(jArrRWA.getJSONObject(i).getString("furl"));
				rwaItemsData.setReleaseYear(jArrRWA.getJSONObject(i).getString("releaseYear"));

				rwaItemsDatasList.add(rwaItemsData);
			}

		} catch (JSONException e) {
			bookingsData.setException("JsonParseException");
			e.printStackTrace();
		}


		return bookingsData;
	}

	public NoticeBoardData parseNoticeBoard(String response){
		NoticeBoardData noticeBoardData = new NoticeBoardData();
		List<NoticeBoardItemsData> noticeBoardItemsDatasList = noticeBoardData.getNoticeBoardItemsDatasList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				NoticeBoardItemsData noticeBoardItemsData = new NoticeBoardItemsData();

				noticeBoardItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				noticeBoardItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				noticeBoardItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				noticeBoardItemsData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				noticeBoardItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				noticeBoardItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));
				noticeBoardItemsData.setReleaseYear(jArrRWA.getJSONObject(i).getString("releaseYear"));

				noticeBoardItemsDatasList.add(noticeBoardItemsData);
			}

		} catch (JSONException e) {
			noticeBoardData.setException("JsonParseException");
			e.printStackTrace();
		}


		return noticeBoardData;
	}

	public DirectoryData parseDirectory(String response){
		DirectoryData directoryData = new DirectoryData();
		List<RWAItemsData> rwaItemsDatasList = directoryData.getRwaItemsDatasList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				RWAItemsData rwaItemsData = new RWAItemsData();

				rwaItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				rwaItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				rwaItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				rwaItemsData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				rwaItemsData.setAdr(jArrRWA.getJSONObject(i).getString("adr"));
				rwaItemsData.setCity(jArrRWA.getJSONObject(i).getString("city"));
				rwaItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				rwaItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));
				rwaItemsData.setFurl(jArrRWA.getJSONObject(i).getString("furl"));
				rwaItemsData.setReleaseYear(jArrRWA.getJSONObject(i).getString("releaseYear"));

				rwaItemsDatasList.add(rwaItemsData);
			}

		} catch (JSONException e) {
			directoryData.setException("JsonParseException");
			e.printStackTrace();
		}


		return directoryData;
	}

	public spidyPickData parseSPidyPick(String response){
		spidyPickData spidyPickData = new spidyPickData();
		List<RWAItemsData> rwaItemsDatasList = spidyPickData.getRwaItemsDatasList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				RWAItemsData rwaItemsData = new RWAItemsData();

				rwaItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				rwaItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				rwaItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				rwaItemsData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				rwaItemsData.setAdr(jArrRWA.getJSONObject(i).getString("adr"));
				rwaItemsData.setCity(jArrRWA.getJSONObject(i).getString("city"));
				rwaItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				rwaItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));
				rwaItemsData.setFurl(jArrRWA.getJSONObject(i).getString("furl"));
				rwaItemsData.setReleaseYear(jArrRWA.getJSONObject(i).getString("releaseYear"));

				rwaItemsDatasList.add(rwaItemsData);
			}

		} catch (JSONException e) {
			spidyPickData.setException("JsonParseException");
			e.printStackTrace();
		}


		return spidyPickData;
	}

	public OpinionPollsData parseOpinionPolls(String response){
		OpinionPollsData opinionPollsData = new OpinionPollsData();
		List<RWAItemsData> rwaItemsDatasList = opinionPollsData.getRwaItemsDatasList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				RWAItemsData rwaItemsData = new RWAItemsData();

				rwaItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				rwaItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				rwaItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				rwaItemsData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				rwaItemsData.setAdr(jArrRWA.getJSONObject(i).getString("adr"));
				rwaItemsData.setCity(jArrRWA.getJSONObject(i).getString("city"));
				rwaItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				rwaItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));
				rwaItemsData.setFurl(jArrRWA.getJSONObject(i).getString("furl"));
				rwaItemsData.setReleaseYear(jArrRWA.getJSONObject(i).getString("releaseYear"));

				rwaItemsDatasList.add(rwaItemsData);
			}

		} catch (JSONException e) {
			opinionPollsData.setException("JsonParseException");
			e.printStackTrace();
		}


		return opinionPollsData;
	}

}
