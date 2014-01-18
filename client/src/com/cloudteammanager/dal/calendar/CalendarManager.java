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
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
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

		  public static ArrayList<Event> readCalendar(Context context) {
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
			                    events.add(new Event("",title,begin,end,"",0));

			                }
			                while(eventCursor.moveToNext());
			            }
			        }
			        break;
			    }
			    return events;
		  
		  }
 
}
