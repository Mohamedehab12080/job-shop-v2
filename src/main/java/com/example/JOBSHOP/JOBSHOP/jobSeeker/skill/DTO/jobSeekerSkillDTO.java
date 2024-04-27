package com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;

public class jobSeekerSkillDTO extends baseEntityDTO<Long>{

	private jobSeeker jobSeeker;
	private Skill skill;
	private String skillDegree;
	
	private Long jobSeekerId;
	private Long skillId;
	private String skillName;
	
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		if(skillName.contains("(good)") || skillName.contains("(very-good)") ||skillName.contains("(excellent)"))
		{
			setSkillDegree(skillName.substring(skillName.indexOf("(")+1,skillName.indexOf(")")));
			this.skillName= skillName.substring(0,skillName.indexOf("(")).strip();
		}else 
		{
			this.skillName=skillName;
		}
	}
	public Long getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(Long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
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
	public String getSkillDegree() {
		return skillDegree;
	}
	public void setSkillDegree(String skillDegree) {
		this.skillDegree=skillDegree;
	}
	
	
}
