package com.cloudteammanager.dal.calendar;

import java.util.Calendar;
import java.util.Date;

public class Event implements Comparable{
	private String name;
	private Date start_date;
	private Date end_date;
	private String description;
	private int priority;
	
	public Event(String name, Date start_date, Date end_date, String description, int priority) {
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.description = description;
		this.priority = priority;
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
	
	@Override
	public int compareTo(Object another) {
		if (start_date.getTime() < ((Event) another).start_date.getTime()) {
			return -1;
		}
		else if (start_date.getTime() > ((Event) another).start_date.getTime()) {
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public String toString() {
		return "Start date: " + start_date.getTime() + "    End date: " + end_date.getTime();
	}
}
