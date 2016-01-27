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
import com.bean.DiretoryItemsData;
import com.bean.GroupDetailData;
import com.bean.GroupDetailItemsData;
import com.bean.GroupItemsData;
import com.bean.GroupsData;
import com.bean.HomeSliderItem;
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

				slider.setSliderList(slidList);

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
						item.setIcon(jArrNotice.getJSONObject(i).getString("icon"));

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

						item.setStartPoll(jArrPoll.getJSONObject(i).getString("startPoll"));
						item.setEndPoll(jArrPoll.getJSONObject(i).getString("endPoll"));

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
		List<GroupItemsData> groupItemsDataList = groupsData.getGroupItemsDataList();

		try {

			JSONArray jArrRWA = new JSONArray(response);
			for (int i = 0; i < jArrRWA.length(); i++) {
				GroupItemsData groupItemsData = new GroupItemsData();

				groupItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				groupItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				groupItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				groupItemsData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				groupItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				groupItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));
				groupItemsData.setCreatedDate(jArrRWA.getJSONObject(i).getString("createdDate"));
				groupItemsData.setMembers(jArrRWA.getJSONObject(i).getString("members"));
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

			groupDetailItemsData.setId(innerJsonObject.getString("id"));
			groupDetailItemsData.setDesc(innerJsonObject.getString("desc"));
			groupDetailItemsData.setGenre(innerJsonObject.getString("genre"));
			groupDetailItemsData.setImage(innerJsonObject.getString("image"));
			groupDetailItemsData.setTitle(innerJsonObject.getString("title"));
			groupDetailItemsData.setCreatedby(innerJsonObject.getString("createdby"));
			groupDetailItemsData.setCreatedDate(innerJsonObject.getString("createdDate"));

			groupDetailItemsDataList.add(groupDetailItemsData);
			JSONArray jArrRWA = jsonObject.getJSONArray("comment");
			for (int i = 0; i < jArrRWA.length(); i++) {
				Comments comments = new Comments();
				comments.setCommentby(jArrRWA.getJSONObject(i).getString("commentby"));
				comments.setDescrption(jArrRWA.getJSONObject(i).getString("descrption"));
				comments.setProfilephoto(jArrRWA.getJSONObject(i).getString("profilephoto"));
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

				groupDetailItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				groupDetailItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				groupDetailItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				groupDetailItemsData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				groupDetailItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				groupDetailItemsData.setCreatedby(jArrRWA.getJSONObject(i).getString("createdby"));
				groupDetailItemsData.setCreatedDate(jArrRWA.getJSONObject(i).getString("createdDate"));

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

	public CheckRequestData parseCheckRequestServices(String response){
		CheckRequestData requestServicesData = new CheckRequestData();
		List<CheckRequestItemsData> servicesItemsDatasList = requestServicesData.getCheckRequestItemsDatasList();

		try {

			JSONObject joObject = new JSONObject(response);
			JSONArray jArrRWA = joObject.getJSONArray("myservice");
			for (int i = 0; i < jArrRWA.length(); i++) {
				CheckRequestItemsData servicesItemsData = new CheckRequestItemsData();

				servicesItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				servicesItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				servicesItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				servicesItemsData.setSercice_id(jArrRWA.getJSONObject(i).getString("sercice_id"));
				servicesItemsData.setServiceName(jArrRWA.getJSONObject(i).getString("serviceName"));
				servicesItemsData.setService_status(jArrRWA.getJSONObject(i).getString("service_status"));
				servicesItemsData.setCreatedDate(jArrRWA.getJSONObject(i).getString("createdDate"));
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
			String error = jobject.getString("error");
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
			if(!jObject.isNull("booking_list")){
				JSONArray jArrRWA = jObject.getJSONArray("booking_list");
				for (int i = 0; i < jArrRWA.length(); i++) {
					BookingItemsData bookingItemsData = new BookingItemsData();

					bookingItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
					bookingItemsData.setBooking_start_date(jArrRWA.getJSONObject(i).getString("booking_start_date"));
					bookingItemsData.setCreatedby(jArrRWA.getJSONObject(i).getString("createdby"));
					bookingItemsData.setCreatedDate(jArrRWA.getJSONObject(i).getString("createdDate"));
					bookingItemsData.setEndtime(jArrRWA.getJSONObject(i).getString("endtime"));
					bookingItemsData.setFacility_Id(jArrRWA.getJSONObject(i).getString("facility_Id"));
					bookingItemsData.setFacilityName(jArrRWA.getJSONObject(i).getString("facilityName"));
					bookingItemsData.setRwaid(jArrRWA.getJSONObject(i).getString("rwaid"));
					bookingItemsData.setStarttime(jArrRWA.getJSONObject(i).getString("starttime"));
					bookingItemsData.setTime(jArrRWA.getJSONObject(i).getString("time"));

					bookingItemsDataList.add(bookingItemsData);
				}
			}
			
			if(!jObject.isNull("rwa_facility")){
				JSONArray jArrRWA = jObject.getJSONArray("rwa_facility");
				for (int i = 0; i < jArrRWA.length(); i++) {
					BookingOptionFacilityData bookingOptionFacilityData = new BookingOptionFacilityData();

					bookingOptionFacilityData.setId(jArrRWA.getJSONObject(i).getString("id"));
					bookingOptionFacilityData.setCreatedDate(jArrRWA.getJSONObject(i).getString("createdDate"));
					bookingOptionFacilityData.setFacility_id(jArrRWA.getJSONObject(i).getString("facility_id"));
					bookingOptionFacilityData.setFacilityImg(jArrRWA.getJSONObject(i).getString("facilityImg"));
					bookingOptionFacilityData.setRwaid(jArrRWA.getJSONObject(i).getString("rwaid"));
					bookingOptionFacilityData.setFacilityName(jArrRWA.getJSONObject(i).getString("facilityName"));

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

				noticeBoardItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				noticeBoardItemsData.setComment(jArrRWA.getJSONObject(i).getString("comments"));
				noticeBoardItemsData.setStatus(jArrRWA.getJSONObject(i).getString("status"));
				noticeBoardItemsData.setIcon(jArrRWA.getJSONObject(i).getString("icon"));
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

	public NoticeBoardDetailData parseNoticeBoardDetailData(String response){
		NoticeBoardDetailData noticeBoardDetailData = new NoticeBoardDetailData();
		List<NoticeBoardDetailItemsData> noticeBoardDetailItemsDataList = noticeBoardDetailData.getNoticeBoardDetailItemsData();
		List<Comments> noticeBoardCommentDatasList = noticeBoardDetailData.getCommentList();

		try {
			JSONObject jsonObject = new JSONObject(response);
			JSONObject innerJsonObject = jsonObject.getJSONObject("0");
			NoticeBoardDetailItemsData noticeBoardItemsData = new NoticeBoardDetailItemsData();

			noticeBoardItemsData.setId(innerJsonObject.getString("id"));
			noticeBoardItemsData.setDesc(innerJsonObject.getString("desc"));
			noticeBoardItemsData.setGenre(innerJsonObject.getString("genre"));
			noticeBoardItemsData.setImage(innerJsonObject.getString("image"));
			noticeBoardItemsData.setTitle(innerJsonObject.getString("title"));
			noticeBoardItemsData.setCreatedby(innerJsonObject.getString("createdby"));
			noticeBoardItemsData.setReleaseYear(innerJsonObject.getString("releaseYear"));
			noticeBoardDetailItemsDataList.add(noticeBoardItemsData);

			JSONArray jArrRWA = jsonObject.getJSONArray("comment");
			for (int i = 0; i < jArrRWA.length(); i++) {
				Comments comments = new Comments();
				comments.setCommentby(jArrRWA.getJSONObject(i).getString("commentby"));
				comments.setDescrption(jArrRWA.getJSONObject(i).getString("descrption"));
				comments.setProfilephoto(jArrRWA.getJSONObject(i).getString("profilephoto"));
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

		try {

			JSONObject JsonObject = new JSONObject(response);
			JSONArray jArrRWA = JsonObject.getJSONArray("myservice");
			for (int i = 0; i < jArrRWA.length(); i++) {
				DiretoryItemsData diretoryItemsData = new DiretoryItemsData();

				diretoryItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				diretoryItemsData.setMobile(jArrRWA.getJSONObject(i).getString("mobile"));
				diretoryItemsData.setLandline(jArrRWA.getJSONObject(i).getString("landline"));
				diretoryItemsData.setExtno(jArrRWA.getJSONObject(i).getString("extno"));
				diretoryItemsData.setHouseno(jArrRWA.getJSONObject(i).getString("houseno"));
				diretoryItemsData.setCreatedDate(jArrRWA.getJSONObject(i).getString("createdDate"));
				diretoryItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				diretoryItemsData.setMobile_icon(jArrRWA.getJSONObject(i).getString("mobile_icon"));
				diretoryItemsData.setLandline_icon(jArrRWA.getJSONObject(i).getString("landline_icon"));
				diretoryItemsData.setExtno_icon(jArrRWA.getJSONObject(i).getString("extno_icon"));

				diretoryItemsDatasList.add(diretoryItemsData);
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

				spidyPickItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				spidyPickItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				spidyPickItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				spidyPickItemsData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				spidyPickItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				spidyPickItemsData.setComments(jArrRWA.getJSONObject(i).getString("comments"));
				spidyPickItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));
				spidyPickItemsData.setReleaseYear(jArrRWA.getJSONObject(i).getString("releaseYear"));

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
			JSONObject innerJsonObject = jsonObject.getJSONObject("0");
			SpidyPickDetailItemsData spidyPickItemsData = new SpidyPickDetailItemsData();

			spidyPickItemsData.setId(innerJsonObject.getString("id"));
			spidyPickItemsData.setDesc(innerJsonObject.getString("desc"));
			spidyPickItemsData.setGenre(innerJsonObject.getString("genre"));
			spidyPickItemsData.setComment(innerJsonObject.getString("comments"));
			spidyPickItemsData.setByline(innerJsonObject.getString("byline"));
			spidyPickItemsData.setTags(innerJsonObject.getString("tags"));
			spidyPickItemsData.setImage(innerJsonObject.getString("image"));
			spidyPickItemsData.setTitle(innerJsonObject.getString("title"));
			spidyPickItemsData.setReleaseYear(innerJsonObject.getString("releaseYear"));

			spidyPickItemsDatasList.add(spidyPickItemsData);
			JSONArray jArrRWA = jsonObject.getJSONArray("comment");
			for (int i = 0; i < jArrRWA.length(); i++) {
				Comments comments = new Comments();
				comments.setCommentby(jArrRWA.getJSONObject(i).getString("commentby"));
				comments.setDescrption(jArrRWA.getJSONObject(i).getString("descrption"));
				comments.setProfilephoto(jArrRWA.getJSONObject(i).getString("profilephoto"));
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

				opinionPollsItemsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				opinionPollsItemsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				opinionPollsItemsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));
				opinionPollsItemsData.setImage(jArrRWA.getJSONObject(i).getString("image"));
				opinionPollsItemsData.setStartPoll(jArrRWA.getJSONObject(i).getString("startPoll"));
				opinionPollsItemsData.setEndPoll(jArrRWA.getJSONObject(i).getString("endPoll"));
				opinionPollsItemsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				opinionPollsItemsData.setUrl(jArrRWA.getJSONObject(i).getString("url"));

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

				opinionPollsData.setId(jArrRWA.getJSONObject(i).getString("id"));
				opinionPollsData.setTitle(jArrRWA.getJSONObject(i).getString("title"));
				opinionPollsData.setCreatedby(jArrRWA.getJSONObject(i).getString("createdby"));
				opinionPollsData.setDesc(jArrRWA.getJSONObject(i).getString("desc"));
				opinionPollsData.setGenre(jArrRWA.getJSONObject(i).getString("genre"));

				JSONArray innerjArrRWA = jArrRWA.getJSONObject(i).getJSONArray("option");
				for (int j = 0; j < innerjArrRWA.length(); j++) {
					OpinionPollsDetailItemsData opinionPollsDetailItemsData = new OpinionPollsDetailItemsData();
					opinionPollsDetailItemsData.setId(innerjArrRWA.getJSONObject(j).getString("id"));
					opinionPollsDetailItemsData.setOptions(innerjArrRWA.getJSONObject(j).getString("options"));
					opinionPollsDetailItemsData.setVotes(innerjArrRWA.getJSONObject(j).getString("votes"));
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
			opinionPollsData.setMsg(jsonObject.getString("msg"));
			JSONArray jArrRWA1 = jsonObject.getJSONArray("option");
			JSONArray jArrRWA2 = jsonObject.getJSONArray("totalcounts");
			JSONArray jArrRWA3 = jsonObject.getJSONArray("color");
			for (int i = 0; i < jArrRWA1.length(); i++) {
				opinionOptionList.add(jArrRWA1.getString(i));
			}
			for (int i = 0; i < jArrRWA2.length(); i++) {
				opinionTotalDataList.add(jArrRWA1.getString(i));
			}
			for (int i = 0; i < jArrRWA3.length(); i++) {
				opinionColorDataList.add(jArrRWA1.getString(i));
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
			commentSave.setError(jsonObject.getString("error"));
		} catch (JSONException e) {
			commentSave.setException("JsonParseException");
			e.printStackTrace();
		}


		return commentSave;
	}

}
