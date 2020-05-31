package com.rishipal.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
//	------------- Columns ------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	@Column(nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	private String password;
	private String gid;
	private String token;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "users")
	private Set<Opportunity> oppos = new HashSet<>();

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


	public Set<Opportunity> getOppos() {
		return oppos;
	}


	public void setOppos(Set<Opportunity> oppos) {
		this.oppos = oppos;
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
				+ gid + ", oppos=" + oppos + "]";
	}


	
}
