package com.example.loginrestservice;

public class Login {

	private final long id;
	private final String username;
	private final String password;

	public Login(long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return username;
	}
	
	public String getPassword(){
		return password;
	}
}
