package com.rishipal.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rishipal.model.Skill;
import com.rishipal.repo.SkillRepo;

@Controller("skill")
public class SkillRes {
	@Autowired
	private SkillRepo skillrepo;
	
	@GetMapping("/skill/add")
	@ResponseBody
	public String addSkill() {
		Skill s1 = new Skill();
		Skill s2 = new Skill();
		s1.setName("Java");
		s2.setName("Python");
		skillrepo.save(s1);
		skillrepo.save(s2);
		return "Skills Created";
	}
}
