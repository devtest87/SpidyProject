package com.bean;

import java.util.ArrayList;
import java.util.List;

public class SpidyPickDetailData {
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
	
	private List<NoticeBoardDetailItemsData> noticeBoardDetailItemsData = new ArrayList<NoticeBoardDetailItemsData>();
	public List<NoticeBoardDetailItemsData> getNoticeBoardDetailItemsData() {
		return noticeBoardDetailItemsData;
	}
	public void setNoticeBoardDetailItemsData(
			List<NoticeBoardDetailItemsData> noticeBoardDetailItemsData) {
		this.noticeBoardDetailItemsData = noticeBoardDetailItemsData;
	}
	
}
