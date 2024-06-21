package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class jobSkillModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="skill_id")
	private Skill skill;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="jobModel_id")
	private jobModel jobModel;
	
	public jobModel getJobModel() {
		return jobModel;
	}

	public void setJobModel(jobModel jobModel) {
		this.jobModel = jobModel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	 
	
}
