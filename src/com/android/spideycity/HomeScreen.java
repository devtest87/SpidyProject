package com.android.spideycity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fragment.item.DrawerAdapter;
import com.fragment.item.HomeFragment;
import com.fragment.item.SlideDrawerItems;

public class HomeScreen extends AppCompatActivity implements  OnItemClickListener, OnClickListener {


	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;
	private ListView drawerList;

	private DrawerAdapter adapter;
	private SlideDrawerItems sldItm;
	private boolean isDrawerOpen = false;

	
	LinearLayout newslin ;
	LinearLayout assignmentlin ;
	LinearLayout notificationlin ;
	LinearLayout buslin ;
	LinearLayout eventslin ;
	LinearLayout noticelin ;

	String currentSidePannelSelected = "";
	//	JoinAClassFragment joinfragment;


	//	@Override
	//	protected void onResume() {
	//		// TODO Auto-generated method stub
	//		super.onResume();
	//	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home_screen);
		
		overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);


		initNavigationDrawer();

		currentSidePannelSelected = "channel";
		setNewFragment(currentSidePannelSelected);


		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#683cad")));

		setNewFragment("Home");

	}

	//	@Override
	//	protected void onResume() {
	//		super.onResume();
	//		
	//		
	//		getActionBar().setTitle("sdfgh");
	//		
	////		try{
	////			chanlfragment.updateheader();
	////		}catch(Throwable t){
	////			t.printStackTrace();
	////		}
	//	}
	//	

	private void initNavigationDrawer() {
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 
				R.drawable.ic_drawer, 
				R.string.app_name, 
				R.string.app_name){

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				isDrawerOpen = false;
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				isDrawerOpen = true;
			}		};

			drawerLayout.setDrawerListener(drawerToggle);


			ActionBar actionBar = getSupportActionBar();
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
			actionBar.setCustomView(R.layout.custom_titlebar);

			View v = actionBar.getCustomView();

			TextView ttitle = (TextView)v.findViewById(R.id.GalleryTitleTv);
			ImageView drawerIcon = (ImageView)v.findViewById(R.id.drawer);
			ImageView addButton = (ImageView)v.findViewById(R.id.addbutton);
			//		  
			actionBar.setDisplayHomeAsUpEnabled(false);
			actionBar.setHomeButtonEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(true);

			drawerLayout.setDrawerListener(drawerToggle);


			drawerIcon.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					if(!isDrawerOpen)
						drawerLayout.openDrawer(Gravity.LEFT);
					else
						drawerLayout.closeDrawer(Gravity.LEFT);
				}
			});

			addButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});

			
//			newslin = (LinearLayout)findViewById(R.id.news);
//			assignmentlin = (LinearLayout)findViewById(R.id.assignment);
//			notificationlin = (LinearLayout)findViewById(R.id.notification);
//			buslin = (LinearLayout)findViewById(R.id.bus);
//			eventslin = (LinearLayout)findViewById(R.id.eventslin);
//			noticelin = (LinearLayout)findViewById(R.id.notice);
//
//			newslin.setOnClickListener(this);
//			assignmentlin.setOnClickListener(this);
//			notificationlin.setOnClickListener(this);
//			buslin.setOnClickListener(this);
//			eventslin.setOnClickListener(this);
//			noticelin.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		if(v.equals(newslin)){
			currentSidePannelSelected ="News";
		}else if(v.equals(assignmentlin)){
			currentSidePannelSelected ="Assignments";
		}else if(v.equals(notificationlin)){
			currentSidePannelSelected ="Notification";
		}else if(v.equals(buslin)){
			currentSidePannelSelected ="Bus";
		}else if(v.equals(eventslin)){
			currentSidePannelSelected ="Events";
		}else if(v.equals(noticelin)){
			currentSidePannelSelected ="Notice";
		}
		setNewFragment(currentSidePannelSelected);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);


	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}



	FrameLayout frameLayoutMain ;
	public static FragmentManager fragmentManager ;
	private void setNewFragment(String fragmentName) {

		fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		frameLayoutMain = (FrameLayout) findViewById(R.id.frameContentMain);

		if(fragmentName.equals("Home")) {
			frameLayoutMain.setVisibility(View.VISIBLE);
			HomeFragment fragment = new HomeFragment();
			fragmentTransaction.replace(R.id.frameContentMain, fragment);
			fragmentTransaction.commit();
		}else if(fragmentName.equals("News")){

//			Intent intent = new Intent(HomeScreen.this, ListScreen.class);
//			intent.putExtra("From", "NEWS");
//			startActivity(intent);


		}else if(fragmentName.equals("Assignments")){

//			Intent intent = new Intent(HomeScreen.this, ListScreen.class);
//			intent.putExtra("From", "ASSIGNMENTS");
//			startActivity(intent);

		}else if(fragmentName.equals("Notification")){

//			Intent intent = new Intent(HomeScreen.this, ListScreen.class);
//			intent.putExtra("From", "NOTIFICATIONS");
//			startActivity(intent);


		}else if(fragmentName.equals("Bus")){

//			Intent intent = new Intent(HomeScreen.this, LocationOnMap.class);
//			intent.putExtra("From", "BUS");
//			startActivity(intent);


		}else if(fragmentName.equals("Events")){

//			Intent intent = new Intent(HomeScreen.this, ListScreen.class);
//			intent.putExtra("From", "EVENTS");
//			startActivity(intent);

		}else if(fragmentName.equals("Notice")){

//			Intent intent = new Intent(HomeScreen.this, ListScreen.class);
//			intent.putExtra("From", "NOTICE");
//			startActivity(intent);

		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

		drawerLayout.closeDrawers();
		optionClicked(position);
		adapter.notifyDataSetChanged();

		switch (position) {

		case 0:
			currentSidePannelSelected ="Home";
			setNewFragment(currentSidePannelSelected);

			break;

		case 1:

			currentSidePannelSelected ="News";
			setNewFragment(currentSidePannelSelected);

			break;

		case 2:

			currentSidePannelSelected ="Assignments";
			setNewFragment(currentSidePannelSelected);

			break;

		case 3:

			currentSidePannelSelected ="Notification";
			setNewFragment(currentSidePannelSelected);

			break;

		case 4:

			currentSidePannelSelected ="Bus";
			setNewFragment(currentSidePannelSelected);

			break;


		case 5:

			currentSidePannelSelected ="Events";
			setNewFragment(currentSidePannelSelected);

			break;

		case 6:

			currentSidePannelSelected ="Notice";
			setNewFragment(currentSidePannelSelected);

			break;

		case 7:

			showDialog();

			break;
		}
	}


	private void showDialog() throws NotFoundException {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeScreen.this);

		// set title
		alertDialogBuilder.setTitle("Log Out");

		// set dialog message
		alertDialogBuilder
		.setMessage("Are you sure, you want to log out from app?")
		.setCancelable(false)
		.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, close
				// current activity

				Intent i = new Intent(HomeScreen.this, LoginActivity.class);
				startActivity(i);

				HomeScreen.this.finish();

			}
		})
		.setNegativeButton("No",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, just close
				// the dialog box and do nothing
				dialog.cancel();
			}
		});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();	}

	//	private void setActionBarTitle(String title){
	//
	//		//getSupportActionBar().setTitle(title);
	//		getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Eduposse</font>"));
	//
	//	}


	private void optionClicked(int i)
	{

		for (int j = 0; j < SlideDrawerItems.drwrItm.size(); j++) {

			if(j == i){
				sldItm.getItem().get(j).setClicked(true);
			}else{
				sldItm.getItem().get(j).setClicked(false);

			}
		}
		adapter.notifyDataSetChanged();	

	}

}


//	private static long back_pressed;
//	@Override
//	public void onBackPressed() {
//		
//		
//		if (currentSidePannelSelected.equals("channel")) {
//			if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
//	        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
//	        back_pressed = System.currentTimeMillis();
//			
//		} else if (currentSidePannelSelected.equals("createmyclass") ||
//				currentSidePannelSelected.equals("community") ||
//				currentSidePannelSelected.equals("Message") ||
//				currentSidePannelSelected.equals("Find Concept") ||
//				currentSidePannelSelected.equals("Join a Class")) {
//		
//			try{
//				chanlfragment.updateheader();
//			}catch(Throwable t){
//				t.printStackTrace();
//			}
//			
//			
//			fragmentManager = getSupportFragmentManager();
//			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//			frameLayoutMain = (FrameLayout) findViewById(R.id.frameContentMain);
//			 frameLayoutMain1 = (FrameLayout) findViewById(R.id.frameContentMain1);
//			
//				frameLayoutMain.setVisibility(View.GONE);
//				frameLayoutMain1.setVisibility(View.VISIBLE);
//				if (frameLayoutMain1.getChildCount() == 0) {
//					ClassFragment1 fragment = new ClassFragment1(getSupportFragmentManager());
//					Bundle b=new Bundle();
//					b.putBoolean("FRAGFLAG", true);
//					fragment.setArguments(b);
//					fragmentTransaction.replace(R.id.frameContentMain1, fragment, ConstantValues.INSTITUTE_LIST);
//					fragmentTransaction.commit();
//				}
//
//		
//		}
//	}
//}
