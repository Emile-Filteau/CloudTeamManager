package com.cloudteammanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.cloudteammanager.dal.calendar.CalendarManager;
import com.cloudteammanager.dal.calendar.Event;
import com.cloudteammanager.listview.expandable.Group;
import com.cloudteammanager.listview.expandable.MyExpandableListAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.util.SparseArray;
import android.view.Menu;
import android.widget.ExpandableListView;

public class CalendarManagementActivity extends Activity {
	SparseArray<Group> groups = new SparseArray<Group>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_management);
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
		/*CalendarManager calendarManager = new CalendarManager(this);
		ArrayList<Event> listeEvents = calendarManager.getCalendarEvents(this);
		ArrayList<String> diffDate = new ArrayList<String>();

		String datePrecedente = null;
		for(Event event:listeEvents){
			if(datePrecedente==null){
				datePrecedente=event.getStart_date().getDay() + " " + event.getStart_date().getDate() + " " + event.getStart_date().getMonth() ;
				diffDate.add(datePrecedente);
			}
			else{
				if(diffDate.get(diffDate.size()-1).equals(event.getStart_date().getDay() + " " + event.getStart_date().getDate() + " " + event.getStart_date().getMonth()))
					diffDate.add(event.getStart_date().getDay() + " " + event.getStart_date().getDate() + " " + event.getStart_date().getMonth());
			}
				
			
		}
		
		  
	    for (int j = 1; j < diffDate.size(); j++) {
	      Group group = new Group(diffDate.get(j));
	      for (int i = 0; i < 5; i++) {
	        group.children.add("Sub Item" + i);
	      }
	      groups.append(j, group);
	    }*/
	  }
		
		

}
