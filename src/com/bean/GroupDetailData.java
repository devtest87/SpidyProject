package com.bean;

import java.util.ArrayList;
import java.util.List;

public class GroupDetailData {
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
	
	private List<GroupDetailItemsData> groupDetailItemsDataList = new ArrayList<GroupDetailItemsData>();
	public List<GroupDetailItemsData> getGroupDetailItemsDataList() {
		return groupDetailItemsDataList;
	}
	public void setGroupDetailItemsDataList(
			List<GroupDetailItemsData> groupDetailItemsDataList) {
		this.groupDetailItemsDataList = groupDetailItemsDataList;
	}
	
	private List<Comments> commentList = new ArrayList<Comments>();
	public List<Comments> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comments> commentList) {
		this.commentList = commentList;
	}
	
}
