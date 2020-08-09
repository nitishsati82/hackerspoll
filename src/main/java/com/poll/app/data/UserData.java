package com.poll.app.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="poll_admin")
public class UserData implements Serializable{
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "login_id")
	private String login_id;
	
	@Column(name = "user_name")
	private String user_name;

	
	@Column(name = "login_success")
	private String login_success="N"; 
	
	@Column(name = "session_index")
	private String session_index="NA";

	public String getPassword() {
		return password;
	}

	public String getLogin_id() {
		return login_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getLogin_success() {
		return login_success;
	}

	public String getSession_index() {
		return session_index;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setLogin_success(String login_success) {
		this.login_success = login_success;
	}

	public void setSession_index(String session_index) {
		this.session_index = session_index;
	}
	
}
