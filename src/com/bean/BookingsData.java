package com.bean;

import java.util.ArrayList;
import java.util.List;

public class BookingsData {
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
	
	private List<String> facilitiesIdsItemsDataList = new ArrayList<String>();
	public List<String> getFacilitiesIdsItemsDataList() {
		return facilitiesIdsItemsDataList;
	}
	public void setFacilitiesIdsItemsDataList(
			List<String> facilitiesIdsItemsDataList) {
		this.facilitiesIdsItemsDataList = facilitiesIdsItemsDataList;
	}
	public List<String> getFacilitiesNameItemsDataList() {
		return facilitiesNameItemsDataList;
	}
	public void setFacilitiesNameItemsDataList(
			List<String> facilitiesNameItemsDataList) {
		this.facilitiesNameItemsDataList = facilitiesNameItemsDataList;
	}
	private List<String> facilitiesNameItemsDataList = new ArrayList<String>();
	private List<BookingItemsData> bookingItemsDataList = new ArrayList<BookingItemsData>();
	public List<BookingItemsData> getBookingItemsDataList() {
		return bookingItemsDataList;
	}
	public void setBookingItemsDataList(List<BookingItemsData> bookingItemsDataList) {
		this.bookingItemsDataList = bookingItemsDataList;
	}
	
}
