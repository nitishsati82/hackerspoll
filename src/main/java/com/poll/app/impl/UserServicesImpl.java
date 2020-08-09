package com.poll.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poll.app.data.UserData;
import com.poll.app.repos.UserRepos;
import com.poll.app.services.UserServices;
@Service("userServices")
public class UserServicesImpl implements UserServices {
	@Autowired 
	UserRepos userServices;

	@Override
	public UserData getUserDetails(String userIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserData userLogin(UserData userData,String sessionnId) {
		UserData user = userServices.getUserDetails(userData.getLogin_id(),userData.getPassword());
		if(user==null) {
			user = new UserData();
			user.setLogin_success("Email/password not matched.");
			return user;
		}
		if(user!=null && user.getId()>0) {
			user.setLogin_success("Y");
			int sessionupdate = userServices.updateUserSession(sessionnId, user.getId());
			if(sessionupdate>0) {
				user.setPassword("");
				user.setSession_index(sessionnId);
			}
		}
		return user;
	}

	@Override
	public UserData userSignUp(UserData userData) {
		String id = "";
		id = userServices.getUserId(userData.getLogin_id());
		if(id==null)id="";
		if(!id.equals("")) {
			userData.setId(-1);
			return userData;
		}
		return userServices.saveAndFlush(userData);
	}

	@Override
	public UserData updateUserDetails(UserData userData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int userLogOut(int userIndex, String sessionnId) {
		int sessionupdate = userServices.updateUserSessionOut("0",sessionnId, userIndex);
		return sessionupdate;
	}

	@Override
	public String getPassword(String email) {
		String id = userServices.getUserId(email);
		if(id==null) return "N";
		if(!id.equals("")) {
			String Password = userServices.getPassword(email);
			return Password;
		}else {
			return "N";
		}
		
	}

	
}
