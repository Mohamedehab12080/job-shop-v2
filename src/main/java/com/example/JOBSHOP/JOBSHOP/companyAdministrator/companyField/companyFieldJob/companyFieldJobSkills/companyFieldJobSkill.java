package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills;

import com.example.JOBSHOP.JOBSHOP.skills.Skill;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJob;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class companyFieldJobSkill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne // One companyField to many companyFieldJobSkill
	@JoinColumn(name="companyFieldJob_id")
	private companyFieldJob companyFieldJob;
	 
	@ManyToOne // One skill to many companyFieldJobSkill
	@JoinColumn(name="skill_id")
	private Skill companyFieldJobSkill;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public companyFieldJob getCompanyFieldJob() {
		return companyFieldJob;
	}

	public void setCompanyFieldJob(companyFieldJob companyFieldJob) {
		this.companyFieldJob = companyFieldJob;
	}

	public Skill getCompanyFieldJobSkill() {
		return companyFieldJobSkill;
	}

	public void setCompanyFieldJobSkill(Skill companyFieldJobSkill) {
		this.companyFieldJobSkill = companyFieldJobSkill;
	}

}
