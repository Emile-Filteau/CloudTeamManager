package com.cloudteammanager.dal.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.util.Log;


public class HTTPRequester {
	
	public static String executeRequest(String controller, String action, JSONObject params) {
		String result = "";
		InputStream is = null;
		
		try {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("action", action));
			
			if(params != null) {
				nameValuePairs.add(new BasicNameValuePair("params", params.toString()));
			}
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("127.0.0.1" + "/" + controller + ".php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			result=sb.toString();
		}
		catch(Exception e) {
			Log.i("HTTPRequester::executeRequest", "Error in HTTP connection " + e.getMessage());
			return null;
		}
		return result;
	}
	
}
