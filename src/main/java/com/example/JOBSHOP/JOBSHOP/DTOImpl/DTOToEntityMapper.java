package com.example.JOBSHOP.JOBSHOP.DTOImpl;

import com.example.JOBSHOP.JOBSHOP.DTOs.UserDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.companyAdministratorDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.companyFieldDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.employerDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.employerFieldDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.jobSeekerDTO;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.User;
import com.example.JOBSHOP.JOBSHOP.models.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.models.companyField;
import com.example.JOBSHOP.JOBSHOP.models.employerField;
import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;

public class DTOToEntityMapper {
	
	public static Application mapDTOToApplication(applicationDTO app)
	{
		Application dto=new Application();
		dto.setId(app.getId());
		dto.setCreatedBy(app.getCreatedBy());
		dto.setCreatedDate(app.getCreatedDate());
		dto.setLastModifiedBy(app.getLastModifiedBy());
		dto.setLastModifiedDate(app.getLastModifiedDate());
		dto.setStatusCode(app.getStatuseCode());
		dto.setExperience(app.getExperience());
		dto.setSkills(app.getSkills());
		dto.setJobSeeker(app.getJobSeeker());
		dto.setPost(app.getPost());
		return dto;
	}
	public static employerField mapDTOToEmployerField(employerFieldDTO employerField)
	{
		employerField dto=new employerField();
		dto.setId(employerField.getId());
		dto.setCreatedBy(employerField.getCreatedBy());
		dto.setCreatedDate(employerField.getCreatedDate());
		dto.setLastModifiedBy(employerField.getLastModifiedBy());
		dto.setLastModifiedDate(employerField.getLastModifiedDate());
		dto.setStatusCode(employerField.getStatuseCode());
//		dto.setEmployer(employerField.getEmployer());
		dto.setCompanyField(employerField.getCompanyField());
		return dto;
	}
	public static companyField mapDTOToCompanyField(companyFieldDTO companyField)
	{
		companyField dto=new companyField();
		dto.setId(companyField.getId());
		dto.setCreatedBy(companyField.getCreatedBy());
		dto.setCreatedDate(companyField.getCreatedDate());
		dto.setLastModifiedBy(companyField.getLastModifiedBy());
		dto.setLastModifiedDate(companyField.getLastModifiedDate());
		dto.setStatusCode(companyField.getStatuseCode());
//		dto.setCompanyAdmin(companyField.getCompanyAdmin());
		dto.setFieldName(companyField.getFieldName());
		dto.setRequiredQualifications(companyField.getRequiredQualifications());
		dto.setSkills(companyField.getSkills());
		return dto;
	}
	public static User mapDTOToUser(UserDTO user)
	{
		User dto=new User();
		dto.setId(user.getId());
		dto.setCreatedBy(user.getCreatedBy());
		dto.setAddress(user.getAddress());
		dto.setContacts(user.getContacts());
		dto.setCreatedDate(user.getCreatedDate());
		dto.setLastModifiedBy(user.getLastModifiedBy());
		dto.setLastModifiedDate(user.getLastModifiedDate());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setUserName(user.getUserName());
		dto.setStatusCode(user.getStatuseCode());
		dto.setUserType(user.getUserType());
		return dto;
	}
	
	public static Employer mapDTOToEmployer(employerDTO employer)
	{
		Employer dto=new Employer();
		dto.setId(employer.getId());
		dto.setCreatedBy(employer.getCreatedBy());
		dto.setAddress(employer.getAddress());
		dto.setCompanyAdmin(employer.getCompanyAdmin());
		dto.setContacts(employer.getContacts());
		dto.setCreatedDate(employer.getCreatedDate());
		dto.setLastModifiedBy(employer.getLastModifiedBy());
		dto.setLastModifiedDate(employer.getLastModifiedDate());
		dto.setEmail(employer.getEmail());
		dto.setPassword(employer.getPassword());
		dto.setUserName(employer.getUserName());
		dto.setEmployerFields(employer.getEmployerFields());
		dto.setStatusCode(employer.getStatuseCode());
		dto.setPosts(employer.getPosts());
		dto.setPostCount(employer.getPostCount());
		dto.setUserType(employer.getUserType());
		return dto;
	}
	public static jobSeeker mapDTOToJobSeeker(jobSeekerDTO jobSeeker)
	{
		jobSeeker dto=new jobSeeker();
		dto.setId(jobSeeker.getId());
		dto.setCreatedBy(jobSeeker.getCreatedBy());
		dto.setAddress(jobSeeker.getAddress());
		dto.setContacts(jobSeeker.getContacts());
		dto.setCreatedDate(jobSeeker.getCreatedDate());
		dto.setLastModifiedBy(jobSeeker.getLastModifiedBy());
		dto.setLastModifiedDate(jobSeeker.getLastModifiedDate());
		dto.setEmail(jobSeeker.getEmail());
		dto.setPassword(jobSeeker.getPassword());
		dto.setUserName(jobSeeker.getUserName());
		dto.setStatusCode(jobSeeker.getStatuseCode());
		dto.setUserType(jobSeeker.getUserType());
		dto.setEducation(jobSeeker.getEducation());
		dto.setApplications(jobSeeker.getApplications());
		dto.setApplicationCount(jobSeeker.getApplicationCount());
		dto.setSkills(jobSeeker.getSkills());
		dto.setEmploymentState(jobSeeker.getEmploymentState()); 
		return dto;
	}
	
	public static companyAdministrator mapDTOToCompanyAdmin(companyAdministratorDTO companyAdmin)
	{
		companyAdministrator dto=new companyAdministrator();
		dto.setId(companyAdmin.getId());
		dto.setCreatedBy(companyAdmin.getCreatedBy());
		dto.setAddress(companyAdmin.getAddress());
		dto.setCompanyName(companyAdmin.getCompanyName());
		dto.setContacts(companyAdmin.getContacts());
		dto.setCreatedDate(companyAdmin.getCreatedDate());
		dto.setLastModifiedBy(companyAdmin.getLastModifiedBy());
		dto.setLastModifiedDate(companyAdmin.getLastModifiedDate());
		dto.setEmail(companyAdmin.getEmail());
		dto.setPassword(companyAdmin.getPassword());
		dto.setUserName(companyAdmin.getUserName());
//		dto.setCompanyFields(companyAdmin.getCompanyFields());
		dto.setStatusCode(companyAdmin.getStatuseCode());
//		dto.setEmployers(companyAdmin.getEmployers());
		dto.setUserType(companyAdmin.getUserType());
		return dto;
	}
}
