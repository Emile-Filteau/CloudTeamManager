package com.cloudteammanager.dal.network;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.cloudteammanager.dal.Team;
import com.cloudteammanager.dal.User;

public class TeamDAO {
	
	private static String controller = "teammanager";
	
	public static List<Team> getUserTeams(Integer user_id) {
		List<Team> teams = new ArrayList<Team>();
		JSONObject params = new JSONObject();
		try {
			params.put("user_id", user_id);
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		String result = HTTPRequester.executeRequest(controller, "getUserTeams", params);
		try {
			JSONArray team_array = new JSONArray(result);
			
		} catch (JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		return teams;
	}
}
