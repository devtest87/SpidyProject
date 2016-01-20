package com.bean;

import java.util.ArrayList;
import java.util.List;

public class OpinionPollsDetailsData {
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	private String title;
	private String desc;
	private String createdby;
	private String genre;
	
	private List<OpinionPollsDetailItemsData> opinionPollsDetailItemsDataList = new ArrayList<OpinionPollsDetailItemsData>();
	public List<OpinionPollsDetailItemsData> getOpinionPollsDetailItemsDataList() {
		return opinionPollsDetailItemsDataList;
	}
	public void setOpinionPollsDetailItemsDataList(
			List<OpinionPollsDetailItemsData> opinionPollsDetailItemsDataList) {
		this.opinionPollsDetailItemsDataList = opinionPollsDetailItemsDataList;
	}
	
}
