package com.cloudteammanager.dal;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
	private Integer id;
	private String username;
	private String email;
	
	//private List<Team> teams; 
	
	public User() {
		id = -1;
		username = "";
		email = "";
	}
	
	public User(Integer id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	
	public User(Parcel in) {
		this.id = in.readInt();
		this.username = in.readString();
		this.email = in.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(username);
		dest.writeString(email);
	}	
	
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in); 
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
