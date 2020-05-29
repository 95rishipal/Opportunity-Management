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
@Table(name="Skill")
public class Skill {
//	------------- Columns ------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int skillid;
	
	@Column(unique = true)
	private String name;
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "skills")
//	private Set<Opportunity> oppos = new HashSet<>(); 
	
// -------------- Getter and Setters ----------------------------------	
	public int getSkillid() {
		return skillid;
	}
	public void setSkillid(int skillid) {
		this.skillid = skillid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public Set<Opportunity> getOppos() {
//		return oppos;
//	}
//	public void setOppos(Set<Opportunity> oppos) {
//		this.oppos = oppos;
//	}
	
}
