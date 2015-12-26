package com.bean;

import java.util.List;

public class SliderData {

	private String exception = "noexception";
	private String error_msg;
	private String error;
	
	private List<HomeSliderItem> sliderList;
	private HomeSliderItem pollitem;
	private HomeSliderItem noticeitem;
	
	public HomeSliderItem getPollitem() {
		return pollitem;
	}

	public void setPollitem(HomeSliderItem pollitem) {
		this.pollitem = pollitem;
	}

	public HomeSliderItem getNoticeitem() {
		return noticeitem;
	}

	public void setNoticeitem(HomeSliderItem noticeitem) {
		this.noticeitem = noticeitem;
	}

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

	public List<HomeSliderItem> getSliderList() {
		return sliderList;
	}

	public void setSliderList(List<HomeSliderItem> sliderList) {
		this.sliderList = sliderList;
	}
}
