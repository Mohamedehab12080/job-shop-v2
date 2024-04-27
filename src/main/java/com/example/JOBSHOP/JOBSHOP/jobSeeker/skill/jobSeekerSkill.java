package com.example.JOBSHOP.JOBSHOP.jobSeeker.skill;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class jobSeekerSkill extends baseEntity<Long>{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="jobSeeker_id")
	private jobSeeker jobSeeker;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="skill_id")
	private Skill skill;

	private String skillDegree;
	

	public String getSkillDegree() {
		return skillDegree;
	}

	public void setSkillDegree(String skillDegree) {
		this.skillDegree = skillDegree;
	}

	public jobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(jobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	
}
