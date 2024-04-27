package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class companyFieldSkill extends baseEntity<Long>{

	@ManyToOne // One companyField to many companyFieldSkill
	@JoinColumn(name="companyField_id")
	private companyField companyField;
	 
	@ManyToOne // One skill to many companyFieldSkill
	@JoinColumn(name="skill_id")
	private Skill companyFieldSkill;

	public companyField getCompanyField() {
		return companyField;
	}

	public void setCompanyField(companyField companyField) {
		this.companyField = companyField;
	}

	public Skill getCompanyFieldSkill() {
		return companyFieldSkill;
	}

	public void setCompanyFieldSkill(Skill companyFieldSkill) {
		this.companyFieldSkill = companyFieldSkill;
	}
	
	
	
}
