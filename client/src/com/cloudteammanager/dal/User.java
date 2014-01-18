package com.cloudteammanager.dal;

public class User {
	private Integer id;
	private String username;
	private String email;
	
	//private List<Team> teams; 
	
	public User() {
		id = -1;
		username = "";
		email = "";
	}
	
	public User(String id, String username, String email) {
		
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
}
