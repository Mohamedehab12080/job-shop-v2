package com.example.JOBSHOP.JOBSHOP.DTOImpl;

import com.example.JOBSHOP.JOBSHOP.DTOs.UserDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.companyAdministratorDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.companyFieldDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.employerDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.employerFieldDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.jobSeekerDTO;
import com.example.JOBSHOP.JOBSHOP.DTOs.postDTO;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.models.User;
import com.example.JOBSHOP.JOBSHOP.models.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.models.companyField;
import com.example.JOBSHOP.JOBSHOP.models.employerField;
import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;

public class entityToDTOMapper {

	public static applicationDTO mapApplicationToDTO(Application app)
	{
		applicationDTO dto=new applicationDTO();
		dto.setId(app.getId());
		dto.setCreatedBy(app.getCreatedBy());
		dto.setCreatedDate(app.getCreatedDate());
		dto.setLastModifiedBy(app.getLastModifiedBy());
		dto.setLastModifiedDate(app.getLastModifiedDate());
		dto.setStatuseCode(app.getStatuseCode());
		dto.setAdditionalExperience(app.getAdditionalExperience());
		dto.setAdditionalSkills(app.getAdditionalSkills());
		dto.setApprovalState(app.getApprovalState());
		dto.setJobSeeker(app.getJobSeeker());
		dto.setPost(app.getPost());
		return dto;
	}
	
	
	public static employerFieldDTO mapEmployerFieldToDTO(employerField employerField)
	{
		employerFieldDTO dto=new employerFieldDTO();
		dto.setId(employerField.getId());
		dto.setCreatedBy(employerField.getCreatedBy());
		dto.setCreatedDate(employerField.getCreatedDate());
		dto.setLastModifiedBy(employerField.getLastModifiedBy());
		dto.setLastModifiedDate(employerField.getLastModifiedDate());
		dto.setStatuseCode(employerField.getStatuseCode());
		dto.setEmployer(employerField.getEmployer());
		dto.setCompanyField(employerField.getCompanyField());
		return dto;
	}
	public static companyFieldDTO mapCompanyFieldToDTO(companyField companyField)
	{
		companyFieldDTO dto=new companyFieldDTO();
		dto.setId(companyField.getId());
		dto.setCreatedBy(companyField.getCreatedBy());
		dto.setCreatedDate(companyField.getCreatedDate());
		dto.setLastModifiedBy(companyField.getLastModifiedBy());
		dto.setLastModifiedDate(companyField.getLastModifiedDate());
		dto.setStatuseCode(companyField.getStatuseCode());
		dto.setCompanyAdmin(companyField.getCompanyAdmin());
		dto.setFieldName(companyField.getFieldName());
		dto.setRequiredQualifications(companyField.getRequiredQualifications());
		dto.setSkills(companyField.getSkills());
		return dto;
	}
	public static postDTO mapPostToDTO(Post post)
	{
		postDTO dto=new postDTO();
		dto.setId(post.getId());
		dto.setCreatedBy(post.getCreatedBy());
		dto.setCreatedDate(post.getCreatedDate());
		dto.setLastModifiedBy(post.getLastModifiedBy());
		dto.setLastModifiedDate(post.getLastModifiedDate());
		dto.setStatuseCode(post.getStatuseCode());
		dto.setTitle(post.getTitle());
		dto.setDescription(post.getDescription());
		dto.setJobRequirments(post.getJobRequirments());
		dto.setPostState(post.getPostState());
		dto.setLocation(post.getLocation());
		dto.setEmploymentType(post.getEmploymentType());
		dto.setCompanyProfile(post.getCompanyProfile());
		dto.setEmployer(post.getEmployer());
		dto.setPostFields(post.getPostFields());
		dto.setFieldCount(post.getFieldCount());
		dto.setApplications(post.getApplications());
		dto.setAdditionalSkills(post.getAdditionalSkills());
		dto.setApplicationCount(post.getApplicationCount());
		return dto;
	}
	public static UserDTO mapUserToDTO(User user)
	{
		UserDTO dto=new UserDTO();
		dto.setId(user.getId());
		dto.setCreatedBy(user.getCreatedBy());
		dto.setCreatedDate(user.getCreatedDate());
		dto.setLastModifiedBy(user.getLastModifiedBy());
		dto.setLastModifiedDate(user.getLastModifiedDate());
		dto.setStatuseCode(user.getStatuseCode());
		dto.setAddress(user.getAddress());
		dto.setContacts(user.getContacts());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setUserName(user.getUserName());
		dto.setUserType(user.getUserType());
		return dto;
	}
	
	public static employerDTO mapEmployerToDTO(Employer employer)
	{
		employerDTO dto=new employerDTO();
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
		dto.setStatuseCode(employer.getStatuseCode());
		dto.setPosts(employer.getPosts());
		dto.setPostCount(employer.getPostCount());
		dto.setUserType(employer.getUserType());
		return dto;
	}
	public static jobSeekerDTO mapJobSeekerToDTO(jobSeeker jobSeeker)
	{
		jobSeekerDTO dto=new jobSeekerDTO();
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
		dto.setStatuseCode(jobSeeker.getStatuseCode());
		dto.setUserType(jobSeeker.getUserType());
		dto.setEducation(jobSeeker.getEducation());
		dto.setApplications(jobSeeker.getApplications());
		dto.setApplicationCount(jobSeeker.getApplicationCount());
		dto.setSkills(jobSeeker.getSkills());
		dto.setEmploymentState(jobSeeker.getEmploymentState()); 
		return dto;
	}
	
	public static companyAdministratorDTO mapCompanyAdminToDTO(companyAdministrator companyAdmin)
	{
		companyAdministratorDTO dto=new companyAdministratorDTO();
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
		dto.setCompanyFields(companyAdmin.getCompanyFields());
		dto.setStatuseCode(companyAdmin.getStatuseCode());
		dto.setEmployers(companyAdmin.getEmployers());
		dto.setUserType(companyAdmin.getUserType());
		return dto;
	}
}
