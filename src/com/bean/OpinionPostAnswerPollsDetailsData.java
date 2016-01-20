package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OpinionPostAnswerPollsDetailsData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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

	
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTotalVote() {
		return totalVote;
	}
	public void setTotalVote(String totalVote) {
		this.totalVote = totalVote;
	}
	public List<String> getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(List<String> optionsList) {
		this.optionsList = optionsList;
	}
	public List<String> getTotalCountList() {
		return totalCountList;
	}
	public void setTotalCountList(List<String> totalCountList) {
		this.totalCountList = totalCountList;
	}
	public List<String> getColorList() {
		return colorList;
	}
	public void setColorList(List<String> colorList) {
		this.colorList = colorList;
	}
	private String totalVote;
	private List<String> optionsList = new ArrayList<String>();
	private List<String> totalCountList = new ArrayList<String>();
	private List<String> colorList = new ArrayList<String>();
	
}
