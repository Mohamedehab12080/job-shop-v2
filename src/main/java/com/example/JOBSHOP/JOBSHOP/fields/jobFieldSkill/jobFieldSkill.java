package com.example.JOBSHOP.JOBSHOP.fields.jobFieldSkill;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class jobFieldSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="Skill_id")
	private Skill skill;
	
	@ManyToOne
	@JoinColumn(name="jobModel_id")
	private jobModel jobModel;

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

	public jobModel getJobModel() {
		return jobModel;
	}

	public void setJobModel(jobModel jobModel) {
		this.jobModel = jobModel;
	}
	
}
