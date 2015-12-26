package com.bean;


public class LoginData {

//	{"error":"success",
//		"uid":"4",
//		"rwaid":"1",
//		"rwaname":"Alaksha Apartment",
//		"photo":"",
//		"user":{"name":"LalitIndiaToday",
//		"email":"lalit.mtech@gmail.com","mobile":"99999999",
//		"updateddate":"0000-00-00 00:00:00"
//			}
//	}
	
	private String exception = "noexception";
	private String error_msg;
	private String error;
	
	private String uid;
	private String rwaid;
	private String rwaname;
	private String photo;
	private User user;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getRwaid() {
		return rwaid;
	}
	public void setRwaid(String rwaid) {
		this.rwaid = rwaid;
	}
	public String getRwaname() {
		return rwaname;
	}
	public void setRwaname(String rwaname) {
		this.rwaname = rwaname;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	}
