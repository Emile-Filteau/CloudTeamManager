package com.cloudteammanager.dal.network;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.cloudteammanager.dal.User;

public class UserDAO {
	
	private static String controller = "usermanager";
	
	public static User authenticate(String username, String password) {
		User user = null;
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

	public static User register(String username, String password, String email) {
		User user = null;
		JSONObject params = new JSONObject();
		try {
			params.put("username", username);
			params.put("password", password);
			params.put("email", email);
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		String result = HTTPRequester.executeRequest(controller, "register", params);
		if(result != null) {
			try {
				JSONObject json_result = new JSONObject(result);
				user = new User(json_result.getInt("id"), json_result.getString("username"), json_result.getString("email"));
			} catch (JSONException e) {
				Log.e("UserDAO::authenticate", e.getMessage());
			}
		}
		return user;
	}
}
