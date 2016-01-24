package com.bean;

public class BookingOptionFacilityData {
	private String id;
	private boolean isSelected;
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRwaid() {
		return rwaid;
	}
	public void setRwaid(String rwaid) {
		this.rwaid = rwaid;
	}
	public String getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(String facility_id) {
		this.facility_id = facility_id;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getFacilityImg() {
		return facilityImg;
	}
	public void setFacilityImg(String facilityImg) {
		this.facilityImg = facilityImg;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	private String rwaid;
	private String facility_id;
	private String facilityName;
	private String facilityImg;
	private String createdDate;
}
