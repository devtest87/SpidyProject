package com.parser;

// Test line added by PK

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.bean.BookingItemsData;
import com.bean.BookingOptionFacilityData;
import com.bean.BookingsData;
import com.bean.CheckRequestData;
import com.bean.CheckRequestItemsData;
import com.bean.CommentSave;
import com.bean.Comments;
import com.bean.CreateGroupDetailData;
import com.bean.DeleteServicesData;
import com.bean.DirectoryData;
import com.bean.DirectoryRwaDetail;
import com.bean.DiretoryItemsData;
import com.bean.GroupDetailData;
import com.bean.GroupDetailItemsData;
import com.bean.GroupItemsData;
import com.bean.GroupsData;
import com.bean.HomeSliderItem;
import com.bean.JoinGroupData;
import com.bean.LoginData;
import com.bean.NoticeBoardData;
import com.bean.NoticeBoardDetailData;
import com.bean.NoticeBoardDetailItemsData;
import com.bean.NoticeBoardItemsData;
import com.bean.OpinionPollsData;
import com.bean.OpinionPollsDetailItemsData;
import com.bean.OpinionPollsDetailsData;
import com.bean.OpinionPollsItemsData;
import com.bean.OpinionPostAnswerPollsDetailsData;
import com.bean.RWAFacilityData;
import com.bean.RWAItemsData;
import com.bean.RWAsData;
import com.bean.RWAsDetailItemData;
import com.bean.RegisterData;
import com.bean.RequestServicesData;
import com.bean.ServicesData;
import com.bean.ServicesItemsData;
import com.bean.SliderData;
import com.bean.SpidyPickData;
import com.bean.SpidyPickDetailData;
import com.bean.SpidyPickDetailItemsData;
import com.bean.SpidyPickItemsData;
import com.bean.User;

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
			String error = jObj.optString("error");

			login.setError(error);

			if(error.equalsIgnoreCase("success"))
			{

				login.setUid(jObj.optString("uid"));
				login.setRwaid(jObj.optString("rwaid"));
				login.setRwaname(jObj.optString("rwaname"));
				login.setPhoto(jObj.optString("photo"));

				User user = new User();

				jObj = jObj.getJSONObject("user");

				user.setName(jObj.optString("name"));
				user.setEmail(jObj.optString("email"));
				user.setMobile(jObj.optString("mobile"));
				user.setUpdateddate(jObj.optString("updateddate"));

				login.setUser(user);
			}
			else
			{
				login.setError_msg(jObj.optString("error_msg"));

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

			String error = jObj.optString("error");
			reg.setError(error);

			if(error.equalsIgnoreCase("success"))
			{
				reg.setSuccess_msg(jObj.optString("success_msg"));
			}else{
				reg.setError_msg(jObj.optString("error_msg"));
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

			if(jObj.optString("error").equalsIgnoreCase("success")){
				slider.setError(jObj.optString("error"));

				JSONArray jArrTop = jObj.getJSONArray("top");

				List<HomeSliderItem> slidList = new ArrayList<HomeSliderItem>();
				for (int i = 0; i < jArrTop.length(); i++) {
					HomeSliderItem item = new HomeSliderItem();

					item.setId(jArrTop.getJSONObject(i).optString("id"));
					item.setDesc(jArrTop.getJSONObject(i).optString("desc"));
					item.setGenre(jArrTop.getJSONObject(i).optString("genre"));
					item.setImage(jArrTop.getJSONObject(i).optString("image"));
					item.setReleaseYear(jArrTop.getJSONObject(i).optString("releaseYear"));
					item.setTitle(jArrTop.getJSONObject(i).optString("title"));
					item.setUrl(jArrTop.getJSONObject(i).optString("url"));

					slidList.add(item);
				}

				slider.setSliderList(slidList);

				if(jObj.getJSONArray("notice")!=null){
					JSONArray jArrNotice = jObj.getJSONArray("notice");
					for (int i = 0; i < jArrNotice.length(); i++) {
						HomeSliderItem item = new HomeSliderItem();

						item.setId(jArrNotice.getJSONObject(i).optString("id"));
						item.setDesc(jArrNotice.getJSONObject(i).optString("desc"));
						item.setGenre(jArrNotice.getJSONObject(i).optString("genre"));
						item.setImage(jArrNotice.getJSONObject(i).optString("image"));
						item.setReleaseYear(jArrNotice.getJSONObject(i).optString("releaseYear"));
						item.setTitle(jArrNotice.getJSONObject(i).optString("title"));
						item.setUrl(jArrNotice.getJSONObject(i).optString("url"));
						item.setIcon(jArrNotice.getJSONObject(i).optString("icon"));

						slider.setNoticeitem(item);
					}
				}

				if(jObj.getJSONArray("poll")!=null){
					JSONArray jArrPoll = jObj.getJSONArray("poll");
					for (int i = 0; i < jArrPoll.length(); i++) {
						HomeSliderItem item = new HomeSliderItem();

						item.setId(jArrPoll.getJSONObject(i).optString("id"));
						item.setDesc(jArrPoll.getJSONObject(i).optString("desc"));
						item.setGenre(jArrPoll.getJSONObject(i).optString("genre"));
						item.setImage(jArrPoll.getJSONObject(i).optString("image"));

						item.setStartPoll(jArrPoll.getJSONObject(i).optString("startPoll"));
						item.setEndPoll(jArrPoll.getJSONObject(i).optString("endPoll"));

						item.setTitle(jArrPoll.getJSONObject(i).optString("title"));
						item.setUrl(jArrPoll.getJSONObject(i).optString("url"));

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

				rwaItemsData.setId(jArrRWA.getJSONObject(i).optString("id"));
				rwaItemsData.setDesc(jArrRWA.getJSONObject(i).optString("desc"));
				rwaItemsData.setGenre(jArrRWA.getJSONObject(i).optString("genre"));
				rwaItemsData.setImage(jArrRWA.getJSONObject(i).optString("image"));
				rwaItemsData.setAdr(jArrRWA.getJSONObject(i).optString("adr"));
				rwaItemsData.setCity(jArrRWA.getJSONObject(i).optString("city"));
				rwaItemsData.setTitle(jArrRWA.getJSONObject(i).optString("title"));
				rwaItemsData.setUrl(jArrRWA.getJSONObject(i).optString("url"));
				rwaItemsData.setFurl(jArrRWA.getJSONObject(i).optString("furl"));
				rwaItemsData.setReleaseYear(jArrRWA.getJSONObject(i).optString("releaseYear"));

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
			rwasDetailData.setError(object.optString("error"));
			rwasDetailData.setId(object.optString("id"));
			rwasDetailData.setAddress(object.optString("adr"));
			rwasDetailData.setDesc(object.optString("desc"));
			rwasDetailData.setGenre(object.optString("genre"));
			rwasDetailData.setImage(object.optString("image"));
			rwasDetailData.setReleaseYear(object.optString("releaseYear"));
			rwasDetailData.setTitle(object.optString("title"));
			rwasDetailData.setGenre(object.optString("genre"));
			rwasDetailData.setCreatedby(object.optString("createdby"));


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

				rwaFacilityData.setId(jArrRWA.getJSONObject(i).optString("id"));
				rwaFacilityData.setDesc(jArrRWA.getJSONObject(i).optString("desc"));
				rwaFacilityData.setCreatedby(jArrRWA.getJSONObject(i).optString("createdby"));
				rwaFacilityData.setImage(jArrRWA.getJSONObject(i).optString("image"));
				rwaFacilityData.setFacility_Id(jArrRWA.getJSONObject(i).optString("facility_Id"));
				rwaFacilityData.setRwaId(jArrRWA.getJSONObject(i).optString("rwaId"));
				rwaFacilityData.setFacilityName(jArrRWA.getJSONObject(i).optString("facilityName"));

				rwaFacilityDataList.add(rwaFacilityData);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}


		return rwaFacilityDataList;
	}

	public GroupsData parseGroups(String response){
		GroupsData groupsData = new GroupsData();
		List<GroupItemsData> groupItemsDataList = groupsData.getGroupItemsDataList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				GroupItemsData groupItemsData = new GroupItemsData();

				groupItemsData.setId(jArrRWA.getJSONObject(i).optString("id"));
				groupItemsData.setDesc(jArrRWA.getJSONObject(i).optString("desc"));
				groupItemsData.setGenre(jArrRWA.getJSONObject(i).optString("genre"));
				groupItemsData.setImage(jArrRWA.getJSONObject(i).optString("image"));
				groupItemsData.setTitle(jArrRWA.getJSONObject(i).optString("title"));
				groupItemsData.setUrl(jArrRWA.getJSONObject(i).optString("url"));
				groupItemsData.setCreatedDate(jArrRWA.getJSONObject(i).optString("createdDate"));
				groupItemsData.setMembers(jArrRWA.getJSONObject(i).optString("members"));
				groupItemsDataList.add(groupItemsData);
			}

		} catch (JSONException e) {
			groupsData.setException("JsonParseException");
			e.printStackTrace();
		}


		return groupsData;
	}


	public GroupDetailData parseGroupsDetail(String response){
		GroupDetailData groupDetailData = new GroupDetailData();
		List<GroupDetailItemsData> groupDetailItemsDataList = groupDetailData.getGroupDetailItemsDataList();
		List<Comments> groupDetailDataCommentDatasList = groupDetailData.getCommentList();

		try {

			JSONObject jsonObject = new JSONObject(response);
			JSONObject innerJsonObject = jsonObject.getJSONObject("0");
			GroupDetailItemsData groupDetailItemsData = new GroupDetailItemsData();

			groupDetailItemsData.setId(innerJsonObject.optString("id"));
			groupDetailItemsData.setDesc(innerJsonObject.optString("desc"));
			groupDetailItemsData.setGenre(innerJsonObject.optString("genre"));
			groupDetailItemsData.setImage(innerJsonObject.optString("image"));
			groupDetailItemsData.setMembers(innerJsonObject.optString("members"));
			groupDetailItemsData.setTitle(innerJsonObject.optString("title"));
			groupDetailItemsData.setCreatedby(innerJsonObject.optString("createdby"));
			groupDetailItemsData.setCreatedDate(innerJsonObject.optString("createdDate"));

			groupDetailItemsDataList.add(groupDetailItemsData);
			JSONArray jArrRWA = jsonObject.getJSONArray("comment");
			for (int i = 0; i < jArrRWA.length(); i++) {
				Comments comments = new Comments();
				comments.setCommentby(jArrRWA.getJSONObject(i).optString("commentby"));
				comments.setDescrption(jArrRWA.getJSONObject(i).optString("descrption"));
				comments.setProfilephoto(jArrRWA.getJSONObject(i).optString("profilephoto"));
				groupDetailDataCommentDatasList.add(comments);
			}

		} catch (JSONException e) {
			groupDetailData.setException("JsonParseException");
			e.printStackTrace();
		}

		return groupDetailData;
	}
	
	public CreateGroupDetailData parseCreateGroup(String response){
		CreateGroupDetailData groupDetailData = new CreateGroupDetailData();
		List<GroupDetailItemsData> groupDetailItemsDataList = groupDetailData.getGroupDetailItemsDataList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				GroupDetailItemsData groupDetailItemsData = new GroupDetailItemsData();

				groupDetailItemsData.setId(jArrRWA.getJSONObject(i).optString("id"));
				groupDetailItemsData.setDesc(jArrRWA.getJSONObject(i).optString("desc"));
				groupDetailItemsData.setGenre(jArrRWA.getJSONObject(i).optString("genre"));
				groupDetailItemsData.setImage(jArrRWA.getJSONObject(i).optString("image"));
				groupDetailItemsData.setTitle(jArrRWA.getJSONObject(i).optString("title"));
				groupDetailItemsData.setCreatedby(jArrRWA.getJSONObject(i).optString("createdby"));
				groupDetailItemsData.setCreatedDate(jArrRWA.getJSONObject(i).optString("createdDate"));

				groupDetailItemsDataList.add(groupDetailItemsData);
			}

		} catch (JSONException e) {
			groupDetailData.setException("JsonParseException");
			e.printStackTrace();
		}


		return groupDetailData;
	}


	public ServicesData parseServices(String response){
		ServicesData servicesData = new ServicesData();
		List<ServicesItemsData> servicesItemsDatasList = servicesData.getServicesItemsDatasList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				ServicesItemsData servicesItemsData = new ServicesItemsData();

				servicesItemsData.setId(jArrRWA.getJSONObject(i).optString("id"));
				servicesItemsData.setDesc(jArrRWA.getJSONObject(i).optString("desc"));
				servicesItemsData.setGenre(jArrRWA.getJSONObject(i).optString("genre"));
				servicesItemsData.setCreatedDate(jArrRWA.getJSONObject(i).optString("createdDate"));
				servicesItemsData.setMobile(jArrRWA.getJSONObject(i).optString("mobile"));
				servicesItemsData.setTitle(jArrRWA.getJSONObject(i).optString("title"));
				servicesItemsData.setUrl(jArrRWA.getJSONObject(i).optString("url"));
				servicesItemsData.setServiceProvider(jArrRWA.getJSONObject(i).optString("serviceProvider"));
				servicesItemsData.setIcon(jArrRWA.getJSONObject(i).optString("icon"));


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
		try {

			JSONObject jSONObject = new JSONObject(response);
			requestServicesData.setError(jSONObject.optString("error"));

		} catch (JSONException e) {
			requestServicesData.setException("JsonParseException");
			e.printStackTrace();
		}


		return requestServicesData;
	}

	public CheckRequestData parseCheckRequestServices(String response){
		CheckRequestData requestServicesData = new CheckRequestData();
		List<CheckRequestItemsData> servicesItemsDatasList = requestServicesData.getCheckRequestItemsDatasList();

		try {

			JSONObject joObject = new JSONObject(response);
			JSONArray jArrRWA = joObject.getJSONArray("myservice");
			for (int i = 0; i < jArrRWA.length(); i++) {
				CheckRequestItemsData servicesItemsData = new CheckRequestItemsData();

				servicesItemsData.setId(jArrRWA.getJSONObject(i).optString("id"));
				servicesItemsData.setDesc(jArrRWA.getJSONObject(i).optString("desc"));
				servicesItemsData.setGenre(jArrRWA.getJSONObject(i).optString("genre"));
				servicesItemsData.setSercice_id(jArrRWA.getJSONObject(i).optString("sercice_id"));
				servicesItemsData.setServiceName(jArrRWA.getJSONObject(i).optString("serviceName"));
				servicesItemsData.setService_status(jArrRWA.getJSONObject(i).optString("service_status"));
				servicesItemsData.setCreatedDate(jArrRWA.getJSONObject(i).optString("createdDate"));
				servicesItemsData.setDeletebutton(jArrRWA.getJSONObject(i).optString("deletebutton"));
				servicesItemsData.setServiceIcon(jArrRWA.getJSONObject(i).optString("serviceIcon"));
				servicesItemsDatasList.add(servicesItemsData);
			}

		} catch (JSONException e) {
			requestServicesData.setException("JsonParseException");
			e.printStackTrace();
		}


		return requestServicesData;
	}

	public DeleteServicesData parseDeleteRequestServices(String response){
		DeleteServicesData deleteServicesData = new DeleteServicesData();

		try {

			JSONObject jobject = new JSONObject(response);
			String error = jobject.optString("error");
			deleteServicesData.setError(error);

		} catch (JSONException e) {
			deleteServicesData.setException("JsonParseException");
			e.printStackTrace();
		}


		return deleteServicesData;
	}

	public BookingsData parseBooings(String response){
		BookingsData bookingsData = new BookingsData();
		List<BookingItemsData> bookingItemsDataList = bookingsData.getBookingItemsDataList();
		List<BookingOptionFacilityData> bookingOptionFacilityDataList = bookingsData.getBookingOptionFacilityDataList();

		try {

			JSONObject jObject = new JSONObject(response);
			bookingsData.setError(jObject.optString("error"));
			bookingsData.setError_msg(jObject.optString("error_msg"));
			if(!jObject.isNull("booking_list")){
				JSONArray jArrRWA = jObject.getJSONArray("booking_list");
				for (int i = 0; i < jArrRWA.length(); i++) {
					BookingItemsData bookingItemsData = new BookingItemsData();

					bookingItemsData.setId(jArrRWA.getJSONObject(i).optString("id"));
					bookingItemsData.setBooking_start_date(jArrRWA.getJSONObject(i).optString("booking_start_date"));
					bookingItemsData.setCreatedby(jArrRWA.getJSONObject(i).optString("createdby"));
					bookingItemsData.setCreatedDate(jArrRWA.getJSONObject(i).optString("createdDate"));
					bookingItemsData.setEndtime(jArrRWA.getJSONObject(i).optString("endtime"));
					bookingItemsData.setFacility_Id(jArrRWA.getJSONObject(i).optString("facility_Id"));
					bookingItemsData.setFacilityName(jArrRWA.getJSONObject(i).optString("facilityName"));
					bookingItemsData.setRwaid(jArrRWA.getJSONObject(i).optString("rwaid"));
					bookingItemsData.setStarttime(jArrRWA.getJSONObject(i).optString("starttime"));
					bookingItemsData.setTime(jArrRWA.getJSONObject(i).optString("time"));

					bookingItemsDataList.add(bookingItemsData);
				}
			}
			
			if(!jObject.isNull("rwa_facility")){
				JSONArray jArrRWA = jObject.getJSONArray("rwa_facility");
				for (int i = 0; i < jArrRWA.length(); i++) {
					BookingOptionFacilityData bookingOptionFacilityData = new BookingOptionFacilityData();

					bookingOptionFacilityData.setId(jArrRWA.getJSONObject(i).optString("id"));
					bookingOptionFacilityData.setCreatedDate(jArrRWA.getJSONObject(i).optString("createdDate"));
					bookingOptionFacilityData.setFacility_id(jArrRWA.getJSONObject(i).optString("facility_id"));
					bookingOptionFacilityData.setFacilityImg(jArrRWA.getJSONObject(i).optString("facilityImg"));
					bookingOptionFacilityData.setRwaid(jArrRWA.getJSONObject(i).optString("rwaid"));
					bookingOptionFacilityData.setFacilityName(jArrRWA.getJSONObject(i).optString("facilityName"));

					bookingOptionFacilityDataList.add(bookingOptionFacilityData);
				}
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

				noticeBoardItemsData.setId(jArrRWA.getJSONObject(i).optString("id"));
				noticeBoardItemsData.setComment(jArrRWA.getJSONObject(i).optString("comments"));
				noticeBoardItemsData.setStatus(jArrRWA.getJSONObject(i).optString("status"));
				noticeBoardItemsData.setIcon(jArrRWA.getJSONObject(i).optString("icon"));
				noticeBoardItemsData.setDesc(jArrRWA.getJSONObject(i).optString("desc"));
				noticeBoardItemsData.setGenre(jArrRWA.getJSONObject(i).optString("genre"));
				noticeBoardItemsData.setImage(jArrRWA.getJSONObject(i).optString("image"));
				noticeBoardItemsData.setTitle(jArrRWA.getJSONObject(i).optString("title"));
				noticeBoardItemsData.setUrl(jArrRWA.getJSONObject(i).optString("url"));
				noticeBoardItemsData.setReleaseYear(jArrRWA.getJSONObject(i).optString("releaseYear"));

				noticeBoardItemsDatasList.add(noticeBoardItemsData);
			}

		} catch (JSONException e) {
			noticeBoardData.setException("JsonParseException");
			e.printStackTrace();
		}


		return noticeBoardData;
	}

	public NoticeBoardDetailData parseNoticeBoardDetailData(String response){
		NoticeBoardDetailData noticeBoardDetailData = new NoticeBoardDetailData();
		List<NoticeBoardDetailItemsData> noticeBoardDetailItemsDataList = noticeBoardDetailData.getNoticeBoardDetailItemsData();
		List<Comments> noticeBoardCommentDatasList = noticeBoardDetailData.getCommentList();

		try {
			JSONObject jsonObject = new JSONObject(response);
			noticeBoardDetailData.setError(jsonObject.optString("error"));
			noticeBoardDetailData.setError_msg(jsonObject.optString("error_msg"));
			JSONObject innerJsonObject = jsonObject.getJSONObject("0");
			NoticeBoardDetailItemsData noticeBoardItemsData = new NoticeBoardDetailItemsData();

			noticeBoardItemsData.setId(innerJsonObject.optString("id"));
			noticeBoardItemsData.setDesc(innerJsonObject.optString("desc"));
			noticeBoardItemsData.setGenre(innerJsonObject.optString("genre"));
			noticeBoardItemsData.setImage(innerJsonObject.optString("image"));
			noticeBoardItemsData.setTitle(innerJsonObject.optString("title"));
			noticeBoardItemsData.setCreatedby(innerJsonObject.optString("createdby"));
			noticeBoardItemsData.setReleaseYear(innerJsonObject.optString("releaseYear"));
			noticeBoardDetailItemsDataList.add(noticeBoardItemsData);

			JSONArray jArrRWA = jsonObject.getJSONArray("comment");
			for (int i = 0; i < jArrRWA.length(); i++) {
				Comments comments = new Comments();
				comments.setCommentby(jArrRWA.getJSONObject(i).optString("commentby"));
				comments.setDescrption(jArrRWA.getJSONObject(i).optString("descrption"));
				comments.setProfilephoto(jArrRWA.getJSONObject(i).optString("profilephoto"));
				noticeBoardCommentDatasList.add(comments);
			}

		} catch (JSONException e) {
			noticeBoardDetailData.setException("JsonParseException");
			e.printStackTrace();
		}


		return noticeBoardDetailData;
	}

	public DirectoryData parseDirectory(String response){
		DirectoryData directoryData = new DirectoryData();
		List<DiretoryItemsData> diretoryItemsDatasList = directoryData.getDiretoryItemsDatasList();
		List<DirectoryRwaDetail> diretoryRwaDatasList = directoryData.getDiretoryRwasDatasList();

		try {

			JSONObject JsonObject = new JSONObject(response);
			directoryData.setError(JsonObject.optString("error"));
			directoryData.setError_msg(JsonObject.optString("error_msg"));
			JSONArray jArrRWA = JsonObject.getJSONArray("myservice");
			for (int i = 0; i < jArrRWA.length(); i++) {
				DiretoryItemsData diretoryItemsData = new DiretoryItemsData();

				diretoryItemsData.setId(jArrRWA.getJSONObject(i).optString("id"));
				diretoryItemsData.setMobile(jArrRWA.getJSONObject(i).optString("mobile"));
				diretoryItemsData.setLandline(jArrRWA.getJSONObject(i).optString("landline"));
				diretoryItemsData.setExtno(jArrRWA.getJSONObject(i).optString("extno"));
				diretoryItemsData.setHouseno(jArrRWA.getJSONObject(i).optString("houseno"));
				diretoryItemsData.setCreatedDate(jArrRWA.getJSONObject(i).optString("createdDate"));
				diretoryItemsData.setTitle(jArrRWA.getJSONObject(i).optString("title"));
				diretoryItemsData.setMobile_icon(jArrRWA.getJSONObject(i).optString("mobile_icon"));
				diretoryItemsData.setLandline_icon(jArrRWA.getJSONObject(i).optString("landline_icon"));
				diretoryItemsData.setExtno_icon(jArrRWA.getJSONObject(i).optString("extno_icon"));

				diretoryItemsDatasList.add(diretoryItemsData);
			}
			
			jArrRWA = JsonObject.getJSONArray("rwadetail");
			for (int i = 0; i < jArrRWA.length(); i++) {
				DirectoryRwaDetail directoryRwaDetail = new DirectoryRwaDetail();

				directoryRwaDetail.setId(jArrRWA.getJSONObject(i).optString("id"));
				directoryRwaDetail.setAddress(jArrRWA.getJSONObject(i).optString("address"));
				directoryRwaDetail.setEmail(jArrRWA.getJSONObject(i).optString("email"));
				directoryRwaDetail.setPhone(jArrRWA.getJSONObject(i).optString("phone"));
				directoryRwaDetail.setTitle(jArrRWA.getJSONObject(i).optString("title"));
				directoryRwaDetail.setWebsite(jArrRWA.getJSONObject(i).optString("website"));

				diretoryRwaDatasList.add(directoryRwaDetail);
			}

		} catch (JSONException e) {
			directoryData.setException("JsonParseException");
			e.printStackTrace();
		}


		return directoryData;
	}

	public SpidyPickData parseSPidyPick(String response){
		SpidyPickData spidyPickData = new SpidyPickData();
		List<SpidyPickItemsData> spidyPickItemsDatasList = spidyPickData.getSpidyPickItemsDatasL();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				SpidyPickItemsData spidyPickItemsData = new SpidyPickItemsData();

				spidyPickItemsData.setId(jArrRWA.getJSONObject(i).optString("id"));
				spidyPickItemsData.setDesc(jArrRWA.getJSONObject(i).optString("desc"));
				spidyPickItemsData.setGenre(jArrRWA.getJSONObject(i).optString("genre"));
				spidyPickItemsData.setImage(jArrRWA.getJSONObject(i).optString("image"));
				spidyPickItemsData.setTitle(jArrRWA.getJSONObject(i).optString("title"));
				spidyPickItemsData.setComments(jArrRWA.getJSONObject(i).optString("comments"));
				spidyPickItemsData.setUrl(jArrRWA.getJSONObject(i).optString("url"));
				spidyPickItemsData.setReleaseYear(jArrRWA.getJSONObject(i).optString("releaseYear"));

				spidyPickItemsDatasList.add(spidyPickItemsData);
			}

		} catch (JSONException e) {
			spidyPickData.setException("JsonParseException");
			e.printStackTrace();
		}


		return spidyPickData;
	}

	public SpidyPickDetailData parseSPidyPickDetail(String response){
		SpidyPickDetailData spidyPickData = new SpidyPickDetailData();
		List<SpidyPickDetailItemsData> spidyPickItemsDatasList = spidyPickData.getSpidyPickDetailItemsDataList();
		List<Comments> spidyPickItemsCommentDatasList = spidyPickData.getCommentList();

		try {

			JSONObject jsonObject = new JSONObject(response);
			spidyPickData.setError(jsonObject.optString("error"));
			spidyPickData.setError_msg(jsonObject.optString("error_msg"));
			JSONObject innerJsonObject = jsonObject.getJSONObject("0");
			SpidyPickDetailItemsData spidyPickItemsData = new SpidyPickDetailItemsData();

			spidyPickItemsData.setId(innerJsonObject.optString("id"));
			spidyPickItemsData.setDesc(innerJsonObject.optString("desc"));
			spidyPickItemsData.setGenre(innerJsonObject.optString("genre"));
			spidyPickItemsData.setComment(innerJsonObject.optString("comments"));
			spidyPickItemsData.setByline(innerJsonObject.optString("byline"));
			spidyPickItemsData.setTags(innerJsonObject.optString("tags"));
			spidyPickItemsData.setImage(innerJsonObject.optString("image"));
			spidyPickItemsData.setTitle(innerJsonObject.optString("title"));
			spidyPickItemsData.setReleaseYear(innerJsonObject.optString("releaseYear"));

			spidyPickItemsDatasList.add(spidyPickItemsData);
			JSONArray jArrRWA = jsonObject.getJSONArray("comment");
			for (int i = 0; i < jArrRWA.length(); i++) {
				Comments comments = new Comments();
				comments.setCommentby(jArrRWA.getJSONObject(i).optString("commentby"));
				comments.setDescrption(jArrRWA.getJSONObject(i).optString("descrption"));
				comments.setCreatedDate(jArrRWA.getJSONObject(i).optString("createdDate"));
				comments.setProfilephoto(jArrRWA.getJSONObject(i).optString("profilephoto"));
				spidyPickItemsCommentDatasList.add(comments);
			}

		} catch (JSONException e) {
			spidyPickData.setException("JsonParseException");
			e.printStackTrace();
		}
		return spidyPickData;
	}

	public OpinionPollsData parseOpinionPolls(String response){
		OpinionPollsData opinionPollsData = new OpinionPollsData();
		List<OpinionPollsItemsData> opinionPollsItemsDataList = opinionPollsData.getOpinionPollsItemsDataList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				OpinionPollsItemsData opinionPollsItemsData = new OpinionPollsItemsData();

				opinionPollsItemsData.setId(jArrRWA.getJSONObject(i).optString("id"));
				opinionPollsItemsData.setDesc(jArrRWA.getJSONObject(i).optString("desc"));
				opinionPollsItemsData.setGenre(jArrRWA.getJSONObject(i).optString("genre"));
				opinionPollsItemsData.setImage(jArrRWA.getJSONObject(i).optString("image"));
				opinionPollsItemsData.setStartPoll(jArrRWA.getJSONObject(i).optString("startPoll"));
				opinionPollsItemsData.setEndPoll(jArrRWA.getJSONObject(i).optString("endPoll"));
				opinionPollsItemsData.setTitle(jArrRWA.getJSONObject(i).optString("title"));
				opinionPollsItemsData.setUrl(jArrRWA.getJSONObject(i).optString("url"));

				opinionPollsItemsDataList.add(opinionPollsItemsData);
			}

		} catch (JSONException e) {
			opinionPollsData.setException("JsonParseException");
			e.printStackTrace();
		}


		return opinionPollsData;
	}

	public OpinionPollsDetailsData parseOpinionPollsDetail(String response){
		OpinionPollsDetailsData opinionPollsData = new OpinionPollsDetailsData();
		List<OpinionPollsDetailItemsData> opinionPollsItemsDataList = opinionPollsData.getOpinionPollsDetailItemsDataList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {

				opinionPollsData.setId(jArrRWA.getJSONObject(i).optString("id"));
				opinionPollsData.setTitle(jArrRWA.getJSONObject(i).optString("title"));
				opinionPollsData.setCreatedby(jArrRWA.getJSONObject(i).optString("createdby"));
				opinionPollsData.setDesc(jArrRWA.getJSONObject(i).optString("desc"));
				opinionPollsData.setGenre(jArrRWA.getJSONObject(i).optString("genre"));

				JSONArray innerjArrRWA = jArrRWA.getJSONObject(i).getJSONArray("option");
				for (int j = 0; j < innerjArrRWA.length(); j++) {
					OpinionPollsDetailItemsData opinionPollsDetailItemsData = new OpinionPollsDetailItemsData();
					opinionPollsDetailItemsData.setId(innerjArrRWA.getJSONObject(j).optString("id"));
					opinionPollsDetailItemsData.setOptions(innerjArrRWA.getJSONObject(j).optString("options"));
					opinionPollsDetailItemsData.setVotes(innerjArrRWA.getJSONObject(j).optString("votes"));
					opinionPollsItemsDataList.add(opinionPollsDetailItemsData);
				}

			}

		} catch (JSONException e) {
			opinionPollsData.setException("JsonParseException");
			e.printStackTrace();
		}


		return opinionPollsData;
	}

	public OpinionPostAnswerPollsDetailsData parseOpinionPostAnswerPolls(String response){
		OpinionPostAnswerPollsDetailsData opinionPollsData = new OpinionPostAnswerPollsDetailsData();
		List<String> opinionOptionList = opinionPollsData.getOptionsList();
		List<String> opinionTotalDataList = opinionPollsData.getTotalCountList();
		List<String> opinionColorDataList = opinionPollsData.getColorList();

		try {

			JSONObject jsonObject = new JSONObject(response);
			opinionPollsData.setError(jsonObject.optString("error"));
			opinionPollsData.setError_msg(jsonObject.optString("error_msg"));
			opinionPollsData.setMsg(jsonObject.optString("msg"));
			JSONArray jArrRWA1 = jsonObject.getJSONArray("option");
			JSONArray jArrRWA2 = jsonObject.getJSONArray("totalcounts");
			JSONArray jArrRWA3 = jsonObject.getJSONArray("color");
			for (int i = 0; i < jArrRWA1.length(); i++) {
				opinionOptionList.add(jArrRWA1.optString(i));
			}
			for (int i = 0; i < jArrRWA2.length(); i++) {
				opinionTotalDataList.add(jArrRWA1.optString(i));
			}
			for (int i = 0; i < jArrRWA3.length(); i++) {
				opinionColorDataList.add(jArrRWA1.optString(i));
			}
		} catch (JSONException e) {
			opinionPollsData.setException("JsonParseException");
			e.printStackTrace();
		}


		return opinionPollsData;
	}
	
	public CommentSave parseCommentSave(String response){
		CommentSave commentSave = new CommentSave();
		try {
			JSONObject jsonObject = new JSONObject(response);
			commentSave.setError(jsonObject.optString("error"));
			commentSave.setError_msg(jsonObject.optString("error_msg"));
		} catch (JSONException e) {
			commentSave.setException("JsonParseException");
			e.printStackTrace();
		}


		return commentSave;
	}


	public JoinGroupData parseJoinGroupsDetail(String response) {
		JoinGroupData commentSave = new JoinGroupData();
		try {
			JSONObject jsonObject = new JSONObject(response);
			commentSave.setError(jsonObject.optString("error"));
			commentSave.setError_msg(jsonObject.optString("error_msg"));
		} catch (JSONException e) {
			commentSave.setException("JsonParseException");
			e.printStackTrace();
		}


		return commentSave;
	}

}
