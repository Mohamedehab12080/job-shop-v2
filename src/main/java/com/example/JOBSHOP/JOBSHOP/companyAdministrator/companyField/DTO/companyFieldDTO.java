package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.companyFieldQualification;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.DTO.companyFieldJobDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.companyFieldSkill;
import com.example.JOBSHOP.JOBSHOP.fields.Field;

public class companyFieldDTO extends baseEntityDTO<Long>{

	private String fieldName;
	
	private Long companyAdministratorId;
	
	private String companyName;
	private Field field;
	private Long fieldId;
	private List <String> qualifications=new ArrayList<String>();
	private List<String> skills=new ArrayList<String>();
	
	private List<String>jobs=new ArrayList<String>();
	
	private List<companyFieldJobDTO> companyFieldJobDTOs=new ArrayList<companyFieldJobDTO>();
	Map<Long,String> skillsMap=new HashMap<Long, String>();
	
	private List<companyFieldSkill> companyFieldSkills=new ArrayList<companyFieldSkill>();
	private List<companyFieldQualification> companyFieldQualifications=new ArrayList<companyFieldQualification>();
	
	
	
	public List<companyFieldJobDTO> getCompanyFieldJobDTOs() {
		return companyFieldJobDTOs;
	}
	public void setCompanyFieldJobDTOs(List<companyFieldJobDTO> companyFieldJobDTOs) {
		this.companyFieldJobDTOs = companyFieldJobDTOs;
	}
	public List<String> getJobs() {
		
		return jobs;
	}
	public void setJobs(List<String> jobs) {
		this.jobs = jobs;
	}
	public Long getFieldId() {
		return fieldId;
	}
	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public List<companyFieldQualification> getCompanyFieldQualifications() {
		return companyFieldQualifications;
	}
	public void setCompanyFieldQualifications(List<companyFieldQualification> companyFieldQualifications) {
		this.companyFieldQualifications = companyFieldQualifications;
	}
	public Map<Long, String> getSkillsMap() {
		return skillsMap;
	}
	public List<String> getQualifications() {
		return qualifications;
	}
	public void setQualifications(List<String> qualifications) {
		this.qualifications = qualifications;
	}
	public void setSkillsMap(Map<Long, String> skillsMap) {
		this.skillsMap = skillsMap;
	}
	public List<companyFieldSkill> getCompanyFieldSkills() {
		return companyFieldSkills;
	}
	public void setCompanyFieldSkills(List<companyFieldSkill> companyFieldSkills) {
		this.companyFieldSkills = companyFieldSkills;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Long getCompanyAdministratorId() {
		return companyAdministratorId;
	}
	public void setCompanyAdministratorId(Long companyAdminId) {
		this.companyAdministratorId = companyAdminId;
	}
//	public List<String> getRequiredQualifications() {
//		return requiredQualifications;
//	}
//	public void setRequiredQualifications(List<String> requiredQualifications) {
//		this.requiredQualifications = requiredQualifications;
//	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	
	
}


