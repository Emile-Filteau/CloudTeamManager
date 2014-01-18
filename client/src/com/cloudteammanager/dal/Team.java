package com.cloudteammanager.dal;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {
	private int id;
	private String name;
	private List<User> users;
	
	public Team(int id, String name) {
		this.id = id;
		this.name = name;
		this.users = new ArrayList<User>();
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

}
