package com.example.JOBSHOP.JOBSHOP.degrees;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity
public class Qualification extends baseEntity<Long>{

	@Column(unique = true)
	private String qualificationName;

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
	 
	
	
}
