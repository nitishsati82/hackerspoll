package com.poll.app.services;


import com.poll.app.data.UserData;

public interface UserServices {
	public UserData getUserDetails(String userIndex);
	public UserData userLogin(UserData userData,String sessionnId);
	public int userLogOut(int userIndex,String sessionnId);
	public UserData userSignUp(UserData userData);
	public UserData updateUserDetails(UserData userData);
	public String getPassword(String email);
}
