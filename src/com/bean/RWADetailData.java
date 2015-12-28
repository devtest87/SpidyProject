package com.bean;

import java.util.ArrayList;
import java.util.List;

public class RWADetailData {
	private RWAsDetailItemData rwAsDetailItemData;
	public RWAsDetailItemData getRwAsDetailItemData() {
		return rwAsDetailItemData;
	}
	public void setRwAsDetailItemData(RWAsDetailItemData rwAsDetailItemData) {
		this.rwAsDetailItemData = rwAsDetailItemData;
	}
	public List<RWAFacilityData> getRWAFacilityDataList() {
		return RWAFacilityDataList;
	}
	public void setRWAFacilityDataList(List<RWAFacilityData> rWAFacilityDataList) {
		RWAFacilityDataList = rWAFacilityDataList;
	}
	private List<RWAFacilityData> RWAFacilityDataList = new ArrayList<RWAFacilityData>();
}
