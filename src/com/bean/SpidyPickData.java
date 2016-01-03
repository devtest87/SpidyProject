package com.bean;

import java.util.ArrayList;
import java.util.List;

public class SpidyPickData {
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
	
	private List<SpidyPickItemsData> spidyPickItemsDatasList = new ArrayList<SpidyPickItemsData>();
	public List<SpidyPickItemsData> getSpidyPickItemsDatasL() {
		return spidyPickItemsDatasList;
	}
	public void setSpidyPickItemsDatasL(
			List<SpidyPickItemsData> spidyPickItemsDatasL) {
		this.spidyPickItemsDatasList = spidyPickItemsDatasL;
	}
}
