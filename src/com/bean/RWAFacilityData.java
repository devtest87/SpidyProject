package com.bean;

public class RWAFacilityData {
	private String id;
	private boolean isExpand;
	public boolean isExpand() {
		return isExpand;
	}
	public void setExpand(boolean isExpand) {
		this.isExpand = isExpand;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	private String rwaId;
	public String getRwaId() {
		return rwaId;
	}
	public void setRwaId(String rwaId) {
		this.rwaId = rwaId;
	}
	public String getFacility_Id() {
		return facility_Id;
	}
	public void setFacility_Id(String facility_Id) {
		this.facility_Id = facility_Id;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	private String facility_Id;
	private String  facilityName;
	private String image;
	private String desc;
	private String createdby;
	
	
}
