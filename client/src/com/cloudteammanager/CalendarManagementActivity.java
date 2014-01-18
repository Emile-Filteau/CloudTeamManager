package com.cloudteammanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.cloudteammanager.dal.SyncManager;
import com.cloudteammanager.dal.User;
import com.cloudteammanager.dal.calendar.CalendarManager;
import com.cloudteammanager.dal.calendar.Event;
import com.cloudteammanager.listview.expandable.Group;
import com.cloudteammanager.listview.expandable.MyExpandableListAdapter;
import com.cloudteammanager.utils.Pair;
import com.cloudteammanager.utils.PostTask;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

public class CalendarManagementActivity extends Activity {
	SparseArray<Group> groups = new SparseArray<Group>();
	private User user;
	CalendarManager calendarManager; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_management);
		user = (User)this.getIntent().getExtras().getParcelable("user");
		this.calendarManager= new CalendarManager(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calendar_management, menu);
		
		createData();
	    ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
	    MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
	        groups);
	    listView.setAdapter(adapter);
	    return true;
	  }

	  public void createData() {
		List<Event> listeEvents = CalendarManager.getCalendarEvents(this);
		ArrayList<String> diffDate = new ArrayList<String>();
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);
		String datePrecedente = null;
		for(Event event:listeEvents){
			if(datePrecedente==null){
				datePrecedente=df.format(event.getStart_date());
				diffDate.add(datePrecedente);
			}
			else{
				if(!diffDate.get(diffDate.size()-1).equals(df.format(event.getStart_date())))
					diffDate.add(df.format(event.getStart_date()));
			}
				
			
		}
		
		  
	    for (int j = 0; j < diffDate.size(); j++) {
	      Group group = new Group(diffDate.get(j));
	      for (int i = 0; i < listeEvents.size(); i++) {
	    	  if(diffDate.get(j).equals(df.format(listeEvents.get(i).getStart_date()))){
	    		  String minute = Integer.toString(listeEvents.get(i).getStart_date().getMinutes());
	    		  if(minute.equals("0"))
	    			  	minute="00";
	    		  group.children.add(listeEvents.get(i).getStart_date().getHours()+":"+minute +"   "+ listeEvents.get(i).getName());
	    	  }
	      }
	      groups.append(j, group);
	    }
	  }
		
	 public void syncCalendar(View v){
		 SyncManager syncManager = new SyncManager();
		 syncManager.syncCalendar(this, user.getId(), CalendarManager.getCalendarEvents(this), 
				 new Pair<String, String>("Calendar", "synchronizing calendar..."),
				 new PostTask() {
					public void run(Object obj) {
						new AlertDialog.Builder(CalendarManagementActivity.this)
				        .setIcon(android.R.drawable.ic_dialog_alert)
				        .setTitle("Calendar")
				        .setMessage("calendar synchronized successfully")
				        .setPositiveButton("OK", new DialogInterface.OnClickListener()
				         {
				        	public void onClick(DialogInterface arg0, int arg1) {
				        	}
				         }).show();
					}
		 });
	 }
	 
	 public void addEvent(View v) {
		 Intent i = new Intent(getApplicationContext(), CreateEventActivity.class);
		 startActivity(i);
	 }
}
