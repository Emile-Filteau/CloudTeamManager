package com.cloudteammanager.dal;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
	public static int STATUS_NOT_ASSIGNED = 0;
	public static int STATUS_ASSIGNED = 1;
	public static int STATUS_IN_PROGRESS = 2;
	public static int STATUS_DONE = 3;
	
	private int id;
	private String name;
	private int member_id;
	private int estimated_time;
	private int took_time;
	private int status;
	private String memberName;
	
	public Task(int id, String name, int member_id, int estimated_time, int took_time, int status, String memberName) {
		this.id = id;
		this.name = name;
		this.member_id = member_id;
		this.estimated_time = estimated_time;
		this.took_time = took_time;
		this.status = status;
		this.memberName = memberName;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMember_id() {
		return member_id;
	}

	public int getEstimated_time() {
		return estimated_time;
	}

	public int getTook_time() {
		return took_time;
	}
	
	public String getMemberName() {
		return memberName;
	}

	public static Parcelable.Creator getCreator() {
		return CREATOR;
	}

	public Task(Parcel in) {
		this.id = in.readInt();
		this.name = in.readString();
		this.member_id = in.readInt();
		this.estimated_time = in.readInt();
		this.took_time = in.readInt();
		this.status = in.readInt();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeInt(member_id);
		dest.writeInt(estimated_time);
		dest.writeInt(took_time);
		dest.writeInt(status);
	}	
	
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Task createFromParcel(Parcel in) {
            return new Task(in); 
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

}
