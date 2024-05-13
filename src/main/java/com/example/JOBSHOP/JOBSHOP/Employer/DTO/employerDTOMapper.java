package com.example.JOBSHOP.JOBSHOP.Employer.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;

public class employerDTOMapper {

	public static Employer mapDTOToEmployer(employerDTO dto) {
		Employer emp=new Employer();
		emp.setCreatedDate(dto.getCreatedDate());
		emp.setContacts(dto.getContacts());
		if(dto.getCompanyAdmin()!=null)
		{
			emp.setCompanyAdmin(dto.getCompanyAdministrator());
		}else
		{
			companyAdministrator comp=new companyAdministrator();
			comp.setId(dto.getCompanyAdministratorId());
			emp.setCompanyAdmin(comp);
		}
		emp.setAddress(dto.getAddress());
		emp.setEmail(dto.getEmail());
		emp.setUserName(dto.getUserName());
		emp.setUserType(dto.getUserType());
		emp.setId(dto.getId());
		emp.setEnabled(dto.isEnabled());
		emp.setPostCount(dto.getPostCount());
		emp.setPosts(dto.getPosts());
		emp.setEmployerFields(dto.getEmployerFields());
		emp.setRole(dto.getRole());
		emp.setPassword(dto.getPassword());
		emp.setGender(dto.getGender());
		emp.setCoverImage(dto.getCoverImage());

		return emp;
	}
	public static employerDTO mapEmployerToDTO(Employer emp)
	{
		employerDTO dto=new employerDTO();
		List<String> fieldsString=new ArrayList<String>();
		dto.setCreatedDate(emp.getCreatedDate());
		dto.setContacts(emp.getContacts());
		dto.setCompanyAdmin(emp.getCompanyAdmin());
		dto.setAddress(emp.getAddress());
		dto.setEmail(emp.getEmail());
		dto.setUserName(emp.getUserName());
		dto.setUserType(emp.getUserType());
		dto.setId(emp.getId());
		dto.setEnabled(emp.isEnabled());
		dto.setPostCount(emp.getPostCount());
		dto.setPosts(emp.getPosts());
		dto.setEmployerFields(emp.getEmployerFields());
		dto.setRole(emp.getRole());
		dto.setPassword(emp.getPassword());
		dto.setCompanyName(emp.getCompanyAdmin().getCompanyName());
		dto.setGender(emp.getGender());
		for(employerField field: emp.getEmployerFields())
		{
			fieldsString.add(field.getCompanyField().getFieldName());
		}
		dto.setCompanyAdministratorId(emp.getCompanyAdmin().getId());
		dto.setFieldsNames(fieldsString);
		dto.setCoverImage(emp.getCoverImage());
		dto.setPicture(emp.getPicture());
		return dto;
	}
}
