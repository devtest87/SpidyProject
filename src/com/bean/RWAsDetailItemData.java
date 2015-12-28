package com.bean;

import org.json.JSONObject;

public class RWAsDetailItemData {
	private String exception = "noexception";
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	private String error_msg;
	private String error;
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public JSONObject getQuickLookLablel() {
		return quickLookLablel;
	}
	public void setQuickLookLablel(JSONObject quickLookLablel) {
		this.quickLookLablel = quickLookLablel;
	}
	public JSONObject getQuickLook() {
		return quickLook;
	}
	public void setQuickLook(JSONObject quickLook) {
		this.quickLook = quickLook;
	}
	public JSONObject getCdetailslabel() {
		return cdetailslabel;
	}
	public void setCdetailslabel(JSONObject cdetailslabel) {
		this.cdetailslabel = cdetailslabel;
	}
	public JSONObject getCdetails() {
		return cdetails;
	}
	public void setCdetails(JSONObject cdetails) {
		this.cdetails = cdetails;
	}
	private String title;
	private String address;
	private String desc;
	private String image;
	private String releaseYear;
	private String createdby;
	private String genre;
	private JSONObject quickLookLablel;
	private JSONObject quickLook;
	private JSONObject cdetailslabel;
	private JSONObject cdetails;
	
	
}
