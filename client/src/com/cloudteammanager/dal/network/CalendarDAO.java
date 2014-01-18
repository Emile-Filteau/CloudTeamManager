package com.cloudteammanager.dal.network;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.cloudteammanager.dal.Team;
import com.cloudteammanager.dal.User;
import com.cloudteammanager.dal.calendar.Event;

public class CalendarDAO {
	
	private static String controller = "calendarmanager";
	
	public static void syncCalendar(Integer user_id, List<Event> events) {
		JSONObject params = new JSONObject();
		try {
			params.put("user_id", user_id);
			JSONArray events_array = new JSONArray();
			for(Event event : events) {
				JSONObject event_object = new JSONObject();
				event_object.put("name", event.getName());
				event_object.put("description", event.getDescription());
				event_object.put("start_date", event.getStart_date().getTime());
				event_object.put("end_date", event.getEnd_date().getTime());
				events_array.put(event_object);
			}
			params.put("events", events_array.toString());
			Log.i("test", params.toString());
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		HTTPRequester.executeRequest(controller, "syncCalendar", params);
	}
}
