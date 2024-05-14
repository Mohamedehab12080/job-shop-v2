package com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.employerProfile;

public class employerProfileMapper {

	public static employerProfile mapDTOToEmployerProfile(employerProfileDTO dto)
	{
		employerProfile emp=new employerProfile();
		emp.setEmployer(dto.getEmployer());
		emp.setFollowers(dto.getFollowers());
		return emp;
	}
	
	public static employerProfileDTO mapEmployerProfileToDTO(employerProfile emp)
	{
		employerProfileDTO dto=new employerProfileDTO();
		List<String> employerFields=new ArrayList<String>();
		dto.setCompanyName(emp.getEmployer().getCompanyAdmin().getCompanyName());
		dto.setEmployer(emp.getEmployer());
		dto.setEmployerEmail(emp.getEmployer().getEmail());
		for(employerField empField:emp.getEmployer().getEmployerFields())
		{
			employerFields.add(empField.getCompanyField().getField().getFieldName());
		}
		dto.setEmployerFields(employerFields);
		dto.setEmployerId(emp.getEmployer().getId());
		dto.setId(emp.getId());
		dto.setEmployerUserName(emp.getEmployer().getUserName());
		dto.setEmployerPicture(emp.getEmployer().getPicture());
		
		return dto;
	}
}
