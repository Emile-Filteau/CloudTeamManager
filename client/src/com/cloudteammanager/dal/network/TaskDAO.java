package com.cloudteammanager.dal.network;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.cloudteammanager.dal.Task;

public class TaskDAO {
	
	private static String controller = "taskmanager";
	
	public static List<Task> getTeamTasks(Integer team_id) {
		List<Task> tasks = new ArrayList<Task>();
		JSONObject params = new JSONObject();
		try {
			params.put("team_id", team_id);
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		String result = HTTPRequester.executeRequest(controller, "getTeamTasks", params);
		try {
			JSONArray task_array = new JSONArray(result);
			for(int i=0; i<task_array.length();i++) {
				JSONObject task_obj = new JSONObject(task_array.getString(i));
				Task t = new Task(task_obj.getInt("id"), task_obj.getString("name"), task_obj.getInt("member_id"), 
						task_obj.getInt("estimated_time"), task_obj.getInt("took_time"), task_obj.getInt("status"));
				tasks.add(t);
			}
			
		} catch (JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		return tasks;
	}
	
	public static void createTask(int team_id, String name, int estimated_time, int member_id) {
		JSONObject params = new JSONObject();
		try {
			params.put("team_id", team_id);
			params.put("name", name);
			params.put("estimated_time", estimated_time);
			params.put("member_id", member_id);
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		HTTPRequester.executeRequest(controller, "createTask", params);
	}
}
