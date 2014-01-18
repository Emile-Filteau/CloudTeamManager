package com.cloudteammanager.dal.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.text.format.DateUtils;
import android.util.Log;

public class CalendarManager {
	static Cursor cursor;
	
		public static final String[] FIELDS = { CalendarContract.Calendars.NAME,
		  CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
		  CalendarContract.Calendars.CALENDAR_COLOR,
		  CalendarContract.Calendars.VISIBLE };

		  public static final Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/calendars");

		  ContentResolver contentResolver;
		  Set<String> calendars = new HashSet<String>();

		  public  CalendarManager(Context ctx) {
		    contentResolver = ctx.getContentResolver();
		  }

		  public static ArrayList<Event> getCalendarEvents(Context context) {
			  	ArrayList<Event> events = new ArrayList<Event>();
			    ContentResolver contentResolver = context.getContentResolver();

			    // Fetch a list of all calendars synced with the device, their display names and whether the

			    cursor = contentResolver.query(Uri.parse("content://com.android.calendar/calendars"),
			                (new String[] { "_id", "name", "visible"}), null, null, null);

			    HashSet<String> calendarIds = new HashSet<String>();

			    try
			    {
			        System.out.println("Count="+cursor.getCount());
			        if(cursor.getCount() > 0)
			        {
			            System.out.println("the control is just inside of the cursor.count loop");
			        while (cursor.moveToNext()) {

			             String _id = cursor.getString(0);
			             String displayName = cursor.getString(1);
			             Boolean selected = !cursor.getString(2).equals("0");

			            System.out.println("Id: " + _id + " Display Name: " + displayName + " Selected: " + selected);
			            calendarIds.add(_id);
			        }
			    }
			    }
			    catch(AssertionError ex)
			    {
			        ex.printStackTrace();
			    }
			    catch(Exception e)
			    {
			        e.printStackTrace();
			    }


			    // For each calendar, display all the events from the previous week to the end of next week.        
			    for (String id : calendarIds) {
			        Uri.Builder builder = Uri.parse("content://com.android.calendar/instances/when").buildUpon();
			        //Uri.Builder builder = Uri.parse("content://com.android.calendar/calendars").buildUpon();
			        long now = new Date().getTime();
			        ContentUris.appendId(builder, now - DateUtils.DAY_IN_MILLIS * 1);
			        ContentUris.appendId(builder, now + DateUtils.DAY_IN_MILLIS * 10000);

			        Cursor eventCursor = contentResolver.query(builder.build(),
			                new String[]  { "title", "begin", "end", "allDay"}, "CALENDAR_ID=" + 1,
			                null, "startDay ASC, startMinute ASC");

			        System.out.println("eventCursor count="+eventCursor.getCount());
			        if(eventCursor.getCount()>0)
			        {

			            if(eventCursor.moveToFirst())
			            {
			                do
			                {
			                    Object mbeg_date,beg_date,beg_time,end_date,end_time;  
			                    
			                    final String title = eventCursor.getString(0);
			                    final Date begin = new Date(eventCursor.getLong(1));
			                    final Date end = new Date(eventCursor.getLong(2));
			                    final Boolean allDay = !eventCursor.getString(3).equals("0");
			                    System.out.println(title+begin.toString()+end.toString());
			                    events.add(new Event("",title,begin,end,"",0));

			                }
			                while(eventCursor.moveToNext());
			            }
			        }
			        break;
			    }
			    return events;
		  
		  }
		  
		  public void saveNewEvent(Context context,Event event) {
			  long calID = 1;
			  long startMillis = 0; 
			  long endMillis = 0;
			  Calendar beginTime = Calendar.getInstance();
			  beginTime.setTime(event.getStart_date());
			  startMillis = beginTime.getTimeInMillis();
			  Calendar endTime = Calendar.getInstance();
			  endTime.setTime(event.getEnd_date());
			  endMillis = endTime.getTimeInMillis();
	
			  ContentResolver cr = context.getContentResolver();
			  ContentValues values = new ContentValues();
			  values.put(Events.DTSTART, startMillis);
			  values.put(Events.DTEND, endMillis);
			  values.put(Events.TITLE, event.getName());
			  values.put(Events.DESCRIPTION, event.getDescription());
			  values.put(Events.CALENDAR_ID, calID);
			  values.put(Events.EVENT_TIMEZONE, "America/Los_Angeles");
			  Uri uri = cr.insert(Events.CONTENT_URI, values);
		  }
		  
		  public void goToNewEventFormOnCalendarActivity(Context context,Event event) {
			  long calID = 1;
			  long startMillis = 0; 
			  long endMillis = 0;
			  Calendar beginTime = Calendar.getInstance();
			  beginTime.setTime(event.getStart_date());
			  startMillis = beginTime.getTimeInMillis();
			  Calendar endTime = Calendar.getInstance();
			  endTime.setTime(event.getEnd_date());
			  endMillis = endTime.getTimeInMillis();
			  
			  Intent intent = new Intent(Intent.ACTION_EDIT);  

			  intent.setType("vnd.android.cursor.item/event");

			  intent.putExtra("title", event.getName());

			  intent.putExtra("description", event.getDescription());

			  intent.putExtra("beginTime", startMillis);

			  intent.putExtra("endTime", endMillis);

			  context.startActivity(intent);
		  }
		  
		  public void openCalendarActivity(Context context) {
			// A date-time specified in milliseconds since the epoch.
			  Calendar beginTime = Calendar.getInstance();
			  beginTime.setTime(new Date());
			  long startMillis = beginTime.getTimeInMillis();
			 
			  Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
			  builder.appendPath("time");
			  ContentUris.appendId(builder, startMillis);
			  Intent intent = new Intent(Intent.ACTION_VIEW)
			      .setData(builder.build());
			  context.startActivity(intent);
		  }
		
}
