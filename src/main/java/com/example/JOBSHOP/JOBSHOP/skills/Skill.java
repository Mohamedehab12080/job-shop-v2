package com.example.JOBSHOP.JOBSHOP.skills;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity
public class Skill extends baseEntity<Long>{

	@Column(unique = true)
	private String skillName;

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	
}
