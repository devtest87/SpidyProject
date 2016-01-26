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
	
	private List<BookingOptionFacilityData> bookingOptionFacilityDataList = new ArrayList<BookingOptionFacilityData>();
	public List<BookingOptionFacilityData> getBookingOptionFacilityDataList() {
		return bookingOptionFacilityDataList;
	}
	public void setBookingOptionFacilityDataList(
			List<BookingOptionFacilityData> bookingOptionFacilityDataList) {
		this.bookingOptionFacilityDataList = bookingOptionFacilityDataList;
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
