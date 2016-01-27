package com.bean;

import java.util.ArrayList;
import java.util.List;

public class DirectoryData {
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
	
	private List<DiretoryItemsData> diretoryItemsDatasList = new ArrayList<DiretoryItemsData>();
	private List<DirectoryRwaDetail> diretoryRwasDatasList = new ArrayList<DirectoryRwaDetail>();
	public List<DirectoryRwaDetail> getDiretoryRwasDatasList() {
		return diretoryRwasDatasList;
	}
	public void setDiretoryRwasDatasList(
			List<DirectoryRwaDetail> diretoryRwasDatasList) {
		this.diretoryRwasDatasList = diretoryRwasDatasList;
	}
	public List<DiretoryItemsData> getDiretoryItemsDatasList() {
		return diretoryItemsDatasList;
	}
	public void setDiretoryItemsDatasList(
			List<DiretoryItemsData> diretoryItemsDatasList) {
		this.diretoryItemsDatasList = diretoryItemsDatasList;
	}
}
