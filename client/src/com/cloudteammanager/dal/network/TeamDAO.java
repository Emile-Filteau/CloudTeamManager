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
			for(int i=0; i<team_array.length();i++) {
				JSONObject team_obj = new JSONObject(team_array.getString(i));
				Team t = new Team(team_obj.getInt("id"), team_obj.getString("name"));
				teams.add(t);
			}
			
		} catch (JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		return teams;
	}
	
	public static Team createTeam(String teamName, Integer userId) {
		Team team = null;
		JSONObject params = new JSONObject();
		try {
			params.put("team_name", teamName);
			params.put("user_id", userId);
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		String result = HTTPRequester.executeRequest(controller, "createTeam", params);
		try {
			JSONObject team_obj = new JSONObject(result);
			team = new Team(team_obj.getInt("id"), team_obj.getString("name"));
			
		} catch (JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		return team;
	}
	
	public static List<User> getTeamMembers(Integer team_id) {
		List<User> users = new ArrayList<User>();
		JSONObject params = new JSONObject();
		try {
			params.put("team_id", team_id);
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		String result = HTTPRequester.executeRequest(controller, "getTeamMembers", params);
		try {
			JSONArray user_array = new JSONArray(result);
			for(int i=0; i<user_array.length();i++) {
				JSONObject user_obj = new JSONObject(user_array.getString(i));
				User user = new User(user_obj.getInt("id"), user_obj.getString("username"), user_obj.getString("email"));
				users.add(user);
			}
			
		} catch (JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		return users;
	}
	
	public static User addMemberToTeam(Integer team_id, String username) {
		User user = null;
		JSONObject params = new JSONObject();
		try {
			params.put("username", username);
			params.put("team_id", team_id);
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		String result = HTTPRequester.executeRequest(controller, "addMemberToTeam", params);
		
		try {
			JSONObject json_result = new JSONObject(result);
			user = new User(json_result.getInt("id"), json_result.getString("username"), json_result.getString("email"));
		} catch (JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		return user;
	}
}
