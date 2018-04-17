package com.example.sahilgoyal.apnishuttle.serverrequesthandler.models;

public class RegisterResponse{
	private User user;
	private String token;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"RegisterResponse{" + 
			"user = '" + user + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}
