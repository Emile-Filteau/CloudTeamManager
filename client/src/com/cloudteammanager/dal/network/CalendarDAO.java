package com.cloudteammanager.dal.network;

import java.util.ArrayList;
import java.util.Date;
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
			params.put("events", events_array);
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		Log.i("test", "Params : " + params.toString());
		String result = HTTPRequester.executeRequest(controller, "syncCalendar", params);
		Log.i("test", "Result : " + result);
		
	}
	
	public static List<Event> getTeamEvents(Integer team_id) {
		List<Event> events = new ArrayList<Event>();
		JSONObject params = new JSONObject();
		try {
			params.put("team_id", team_id);
			
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		String result = HTTPRequester.executeRequest(controller, "getTeamEvents", params);
		
		try {
			JSONArray events_array = new JSONArray(result);
			for(int i=0;i<events_array.length();i++) {
				JSONObject event_object = new JSONObject(events_array.getString(i));
				Event event = new Event(event_object.getString("name"), new Date(event_object.getInt("START_DATE")), new Date(event_object.getInt("END_DATE")), event_object.getString("description"), event_object.getInt("priority"));
				events.add(event);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return events;
	}
}
