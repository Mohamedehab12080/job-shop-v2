package com.example.JOBSHOP.JOBSHOP.degrees.DTO;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;

public class qualificationDTO extends baseEntityDTO<Long>{

	String qualificationName;

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
	
	
}
