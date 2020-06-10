package com.opportunitymanagment.accolite.models;

import java.util.HashSet;
import java.util.Set;



public class User {
//	------------- Columns ------------------

	private int userid;

	private String name;

	private String email;
	private String password;
	private String gid;
	private String token;
	
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "users")
//	private Set<Opportunity> oppos = new HashSet<>();
//------------------------ Constructor -----------------------------------
	public User() {
		
	}
	public User(int userid, String name, String email, String password, String gid, String token) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gid = gid;
		this.token = token;
	}
	
	public User(String name, String email, String password, String gid, String token) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.gid = gid;
		this.token = token;
	}
//	------------ Getters and Setters --------------------
	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getGid() {
		return gid;
	}


	public void setGid(String gid) {
		this.gid = gid;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", email=" + email + ", password=" + password + ", gid="
				+ gid +  "]";
	}


	
}
