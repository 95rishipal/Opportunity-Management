package com.rishipal.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.JoinColumn;
import java.time.LocalDate;

@Entity
@Table(name="Opportunity")
public class Opportunity {
//	------ Columns -------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int oppid;
	private String description;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate endDate;
	private String location;
	private String skills;
	
	
// ------------------ Join Tables ---------------------------------	
//	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE })
//	@JoinTable(name = "Rel_Opp_Skill", joinColumns = @JoinColumn(name = "oppid"), 
//	  inverseJoinColumns = @JoinColumn(name = "skillid"))
//	private Set<Skill> skills = new HashSet<>();
//	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "Rel_Opp_User", joinColumns = @JoinColumn(name = "oppid"), 
	  inverseJoinColumns = @JoinColumn(name = "userid"))
	private Set<User> users = new HashSet<>();
	
// ---------------- Constructor ----------------------------
	public Opportunity(int oppid, String description, LocalDate endDate, String location, String skills) {
		this.oppid = oppid;
		this.description = description;
		this.endDate = endDate;
		this.location = location;
		this.skills = skills;
	}




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
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
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
	
	@Override
	public String toString() {
		return "Opportunity [oppid=" + oppid + ", description=" + description + ", endDate=" + endDate + ", location="
				+ location + ", skills=" + skills + ", users=" + users + "]";
	}
	
}
