package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.DTO;


import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.degrees.qualification;

public class companyFieldQualificationDTO extends baseEntityDTO<Long>{

	private Long id;
	private String companyFieldName;
	private String companyName;
	private companyField companyField;
	private Long companyFieldId;
	private String qualificationName;
	private Long qualificationId;
	private qualification qualification;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyFieldName() {
		return companyFieldName;
	}
	public void setCompanyFieldName(String companyFieldName) {
		this.companyFieldName = companyFieldName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public companyField getCompanyField() {
		return companyField;
	}
	public void setCompanyField(companyField companyField) {
		this.companyField = companyField;
	}
	public Long getCompanyFieldId() {
		return companyFieldId;
	}
	public void setCompanyFieldId(Long companyFieldId) {
		this.companyFieldId = companyFieldId;
	}
	public String getQualificationName() {
		return qualificationName;
	}
	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
	public Long getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}
	public qualification getQualification() {
		return qualification;
	}
	public void setQualification(qualification qualification) {
		this.qualification = qualification;
	}
	
	
}
