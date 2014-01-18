package com.cloudteammanager.dal.network;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.cloudteammanager.dal.User;

public class UserDAO {
	
	private String controller = "user_manager";
	
	
	public UserDAO() {
		
	}
	
	public User authenticate(String username, String password) {
		User user = null;
		JSONObject params = new JSONObject();
		try {
			params.put("username", username);
			params.put("password", password);
		} catch(JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		String result = HTTPRequester.executeRequest(controller, "authenticate", params);
		
		Log.i("UserDAO::authenticate", result);
		
		return user;
	}
}
