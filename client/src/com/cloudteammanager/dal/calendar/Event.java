package com.cloudteammanager.dal.calendar;

import java.util.Date;

public class Event {
	private String user;
	private String name;
	private Date start_date;
	private Date end_date;
	private String description;
	private int priority;
	
	public Event(String user, String name, Date start_date, Date end_date,
			String description, int priority) {
		super();
		this.user = user;
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.description = description;
		this.priority = priority;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}