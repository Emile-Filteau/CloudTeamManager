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
		Log.i("test", "result :" + result );
		try {
			JSONObject json_result = new JSONObject(result);
			user = new User(json_result.getInt("id"), json_result.getString("username"), json_result.getString("email"));
		} catch (JSONException e) {
			Log.e("UserDAO::authenticate", e.getMessage());
		}
		
		return user;
	}
}
