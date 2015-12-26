package com.fragment.item;

import java.util.ArrayList;
import java.util.List;

public class SlideDrawerItems {

	String option = "Home, News, Assignments, Notification, Bus, Events, Notice, Log Out";
	
	
	public static List<items> drwrItm = new ArrayList<items>();
	
	
	public List<items> getItem(){
		return drwrItm;
	}
	
	public SlideDrawerItems(){
		//for Home 
		
		items items = new items();
		items.setClicked(false);
		items.setItemtext("Home");
		drwrItm.add(items);
		
		//for Message
		
		 items = new items();
		items.setClicked(false);
		items.setItemtext("News");
		drwrItm.add(items);
		
		//for Community
		
		 items = new items();
		items.setClicked(false);
		items.setItemtext("Assignments");
		drwrItm.add(items);
		
		//for find concept
		
		 items = new items();
		items.setClicked(false);
		items.setItemtext("Notification");
		drwrItm.add(items);
		
		
		//for Join a class
		
		 items = new items();
		items.setClicked(false);
		items.setItemtext("Bus");
		drwrItm.add(items);
		
		//for Create my class
		 items = new items();
		items.setClicked(false);
		items.setItemtext("Events");
		drwrItm.add(items);
		
		//for Logout
		 items = new items();
		items.setClicked(false);
		items.setItemtext("Notice");
		drwrItm.add(items);
		
		//for Logout
		 items = new items();
		items.setClicked(false);
		items.setItemtext("Log Out");
		drwrItm.add(items);
	}
	
	
	
	
}
