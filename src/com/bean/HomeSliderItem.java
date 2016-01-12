package com.bean;

public class HomeSliderItem {

//	{
//	    "id": "19",
//	    "title": "Noida Extension : Death by Breath",
//	    "desc": "As residents choke on dust pollution, The Noida Extension Flat Owners Welfare Association, (NEFOWA) has demanded that cleaning of the roads on a daily basis be made mandatory now.",
//	    "image": "http:\/\/top-story.in\/uploads\/spidey\/201511\/pollution1_111315044946.jpg",
//	    "releaseYear": "2015-11-13 16:49:46",
//	    "genre": "SPIDEY PICK",
//	    "url": "newsdetails_19.json"
//	  }
	
//	poll": [
//    {
//      "id": "4",
//      "title": "Is Noida completely ready for the growing residential infrastructure?",
//      "desc": "",
//      "startPoll": "2015-12-03 14:41:18",
//      "endPoll": "2015-12-25 14:41:18",
//      "image": "http:\/\/top-story.in\/uploads\/noticeboard\/noida.jpg",
//      "genre": "SPIDEY PICK",
//      "url": "polldetails_4.json"
//    }
//  ],
	
	private String id;
	private String title;
	private String desc;
	private String image;
	private String releaseYear;
	private String genre;
	private String url;
	
	private String startPoll;
	private String endPoll;
	
	private String icon;
	
	
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getStartPoll() {
		return startPoll;
	}
	public void setStartPoll(String startPoll) {
		this.startPoll = startPoll;
	}
	public String getEndPoll() {
		return endPoll;
	}
	public void setEndPoll(String endPoll) {
		this.endPoll = endPoll;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
