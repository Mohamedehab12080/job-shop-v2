package com.example.JOBSHOP.JOBSHOP.jobSeeker.service;

import java.util.ArrayList;
import java.util.List;

public class applicationReturnedSkillsAndQualifications {

	private List<String> remainedSkills=new ArrayList<String>();
	private List<String> remainedQualifications=new ArrayList<String>();
	
	
	public List<String> getRemainedSkills() {
		return remainedSkills;
	}
	public void setRemainedSkills(List<String> remainedSkills) {
		this.remainedSkills = remainedSkills;
	}
	public List<String> getRemainedQualifications() {
		return remainedQualifications;
	}
	public void setRemainedQualifications(List<String> remainedQualifications) {
		this.remainedQualifications = remainedQualifications;
	}
	
}
