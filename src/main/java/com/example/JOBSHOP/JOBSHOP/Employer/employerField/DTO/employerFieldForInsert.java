package com.example.JOBSHOP.JOBSHOP.Employer.employerField.DTO;

public class employerFieldForInsert {

	private Long employerId;
	private Long companyFieldId;
	
	public employerFieldForInsert() {}
	public employerFieldForInsert(Long employerId, Long companyFieldId) {
		super();
		this.employerId = employerId;
		this.companyFieldId = companyFieldId;
	}
	
	public Long getEmployerId() {
		return employerId;
	}
	public void setEmployerId(Long employerId) {
		this.employerId = employerId;
	}
	public Long getCompanyFieldId() {
		return companyFieldId;
	}
	public void setCompanyFieldId(Long companyFieldId) {
		this.companyFieldId = companyFieldId;
	}
	
	
	
}
