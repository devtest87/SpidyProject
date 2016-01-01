package com.bean;

import java.util.ArrayList;
import java.util.List;

public class OpinionPollsData {
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
	
	private List<OpinionPollsItemsData> opinionPollsItemsDataList = new ArrayList<OpinionPollsItemsData>();
	public List<OpinionPollsItemsData> getOpinionPollsItemsDataList() {
		return opinionPollsItemsDataList;
	}
	public void setOpinionPollsItemsDataList(
			List<OpinionPollsItemsData> opinionPollsItemsDataList) {
		this.opinionPollsItemsDataList = opinionPollsItemsDataList;
	}
	
}
