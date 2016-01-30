package com.bean;

public class Comments {
	private String descrption;
	public String getDescrption() {
		return descrption;
	}
	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	public String getCommentby() {
		return commentby;
	}
	public void setCommentby(String commentby) {
		this.commentby = commentby;
	}
	public String getProfilephoto() {
		return profilephoto;
	}
	public void setProfilephoto(String profilephoto) {
		this.profilephoto = profilephoto;
	}
	private String  commentby;
	private String  createdDate;
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	private String profilephoto;
}
