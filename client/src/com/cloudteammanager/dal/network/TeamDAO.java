package com.cloudteammanager.dal.network;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.cloudteammanager.dal.User;

public class TeamDAO {
	
	private static String controller = "usermanager";
	
	public static List<Team> getUserTeams(Integer user_id) {
		List<Team> team = new ArrayList<Team>();
		JSONObject params = new JSONObject();
		try {
			params.put("username", username);
			params.put("password", password);
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		String result = HTTPRequester.executeRequest(controller, "authenticate", params);
		try {
			JSONObject json_result = new JSONObject(result);
			user = new User(json_result.getInt("id"), json_result.getString("username"), json_result.getString("email"));
		} catch (JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		return user;
	}
}
