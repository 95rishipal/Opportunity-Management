package com.oppo.accolite.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;


public class Opportunity {
//	------ Columns -------------------

	private int oppid;
	private String description;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate endDate;
	private String location;
	private String skills;
	private int userid;
	private int minxp;
	private int demand;
	
// ------------------ Join Tables ---------------------------------	
//	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE })
//	@JoinTable(name = "Rel_Opp_Skill", joinColumns = @JoinColumn(name = "oppid"), 
//	  inverseJoinColumns = @JoinColumn(name = "skillid"))
//	private Set<Skill> skills = new HashSet<>();
//	
//	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE })
//	@JoinTable(name = "Rel_Opp_User", joinColumns = @JoinColumn(name = "oppid"), 
//	  inverseJoinColumns = @JoinColumn(name = "userid"))
//	private Set<User> users = new HashSet<>();
//	
// ---------------- Constructor ----------------------------
	public Opportunity(int oppid, String description, LocalDate endDate, String location, String skills, int userid, int minxp, int demand) {
		this.oppid = oppid;
		this.description = description;
		this.endDate = endDate;
		this.location = location;
		this.skills = skills;
		this.userid = userid;
		this.minxp = minxp;
		this.demand = demand;
	}
	
	public Opportunity(){}



	//	------------ Getters and Setters -----------------------------
	public int getOppid() {
		return oppid;
	}

	public void setOppid(int oppid) {
		this.oppid = oppid;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	
	public int getMinxp() {
		return minxp;
	}




	public void setMinxp(int minxp) {
		this.minxp = minxp;
	}




	public int getDemand() {
		return demand;
	}




	public void setDemand(int demand) {
		this.demand = demand;
	}




	@Override
	public String toString() {
		return "Opportunity [oppid=" + oppid + ", description=" + description + ", endDate=" + endDate + ", location="
				+ location + ", skills=" + skills + ", users=" + userid + ", demand=" + demand + ", min xp=" + minxp + "]";
	}
	
}
